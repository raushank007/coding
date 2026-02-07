package org.example.systemDesign.scheduling.DeleayedTaskSchedular;

import java.util.*;
import java.util.concurrent.locks.*;
import java.text.SimpleDateFormat;

// 1. The Task Wrapper (Comparable for PriorityQueue)
class Task implements Comparable<Task> {
    private final Runnable command;
    private final long executionTime;
    private final String name; // For debugging

    public Task(String name, Runnable command, long delayMs) {
        this.name = name;
        this.command = command;
        this.executionTime = System.currentTimeMillis() + delayMs;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void execute() {
        command.run();
    }

    @Override
    public int compareTo(Task other) {
        // Sort by time: Earlier tasks come first
        return Long.compare(this.executionTime, other.executionTime);
    }

    @Override
    public String toString() {
        return "Task[" + name + "]";
    }
}

// 2. The Scheduler (The Core Logic)
class SimpleScheduler {
    private final PriorityQueue<Task> pq = new PriorityQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Condition available = lock.newCondition();
    private volatile boolean isRunning = true;
    private final Thread workerThread;

    public SimpleScheduler() {
        // Start the background worker immediately
        this.workerThread = new Thread(this::runWorker);
        this.workerThread.start();
    }

    // Producer: Submit a task
    public void submit(String name, Runnable command, long delayMs) {
        lock.lock();
        try {
            if (!isRunning) {
                System.out.println("❌ Scheduler is shutting down. Rejected: " + name);
                return;
            }

            Task newTask = new Task(name, command, delayMs);
            pq.offer(newTask);
            System.out.println("📥 Submitted: " + name + " (Delay: " + delayMs + "ms)");

            // If this new task is the SOONEST one, wake up the worker to reschedule wait time
            if (pq.peek() == newTask) {
                available.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    // Consumer: The Worker Loop
    private void runWorker() {
        while (isRunning) {
            Task task = null;
            lock.lock();
            try {
                // WAIT LOOP: Handle spurious wakeups & timing
                while (isRunning) {
                    if (pq.isEmpty()) {
                        // Queue is empty: Wait indefinitely for work
                        available.await();
                    } else {
                        // Check time remaining for the head task
                        long delay = pq.peek().getExecutionTime() - System.currentTimeMillis();

                        if (delay <= 0) {
                            // Task is due! Break the loop to poll it
                            break;
                        }

                        // Wait specifically for the remaining time
                        // method awaitNanos releases the lock automatically
                        available.awaitNanos(delay * 1_000_000);
                    }
                }

                // POLL: If we are here, either a task is due OR isRunning is false
                if (isRunning && !pq.isEmpty()) {
                    long delay = pq.peek().getExecutionTime() - System.currentTimeMillis();
                    if (delay <= 0) {
                        task = pq.poll();
                    }
                }
            } catch (InterruptedException e) {
                // If interrupted during shutdown, exit loop
                if (!isRunning) break;
            } finally {
                lock.unlock(); // CRITICAL: Unlock before running the task!
            }

            // EXECUTE: Run outside the lock to allow concurrent submissions
            if (task != null) {
                try {
                    System.out.println("🚀 Executing: " + task);
                    task.execute();
                } catch (Throwable t) {
                    // RESILIENCE: Catch user exceptions so the worker doesn't die
                    System.err.println("🔥 Task Failed: " + task + " | Error: " + t.getMessage());
                }
            }
        }
        System.out.println("💀 Worker Thread Stopped.");
    }

    // Graceful Shutdown
    public List<Task> shutdown() {
        System.out.println("\n🛑 Shutting down scheduler...");
        lock.lock();
        List<Task> cancelledTasks = new ArrayList<>();
        try {
            isRunning = false;
            available.signalAll(); // Wake up worker so it can exit

            // Drain the remaining tasks in priority order
            while (!pq.isEmpty()) {
                cancelledTasks.add(pq.poll());
            }
        } finally {
            lock.unlock();
        }
        return cancelledTasks;
    }
}

// 3. The Test Harness (Main Method)
public class DelayedTaskSchedular {
    private static void log(String msg) {
        String time = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
        System.out.println("[" + time + "] " + msg);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleScheduler scheduler = new SimpleScheduler();

        log("--- STARTING TESTS ---");

        // Test 1: Immediate Task
        scheduler.submit("Task A (Immediate)", () -> log("Hello from Task A!"), 0);

        // Test 2: Delayed Task (2 seconds)
        scheduler.submit("Task B (2s Delay)", () -> log("Hello from Task B!"), 2000);

        // Test 3: Resilience (Task that throws Exception)
        scheduler.submit("Task C (Bad Task)", () -> {
            throw new RuntimeException("Oops! I crashed!");
        }, 500);

        // Test 4: Ordering (Submit 1s task AFTER 2s task)
        scheduler.submit("Task D (1s Delay)", () -> log("Hello from Task D!"), 1000);

        // Wait to let tasks run
        Thread.sleep(3000);

        // Test 5: Shutdown with Pending Tasks
        log("--- TESTING SHUTDOWN ---");
        scheduler.submit("Task E (10s Delay)", () -> log("Should not run"), 10000);
        scheduler.submit("Task F (5s Delay)", () -> log("Should not run"), 5000);

        List<Task> cancelled = scheduler.shutdown();

        log("Cancelled Tasks Count: " + cancelled.size());
        for (Task t : cancelled) {
            System.out.println(" - Cancelled: " + t);
        }

        log("--- DONE ---");
    }
}