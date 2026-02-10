# Design Cron-like Job Scheduler

## Problem statement
>Design a distributed job scheduling system capable of scheduling and executing millions of jobs. The system should mimic the functionality of a unix cron utlity but operate reliably at scale across a distributed scale.

### Step 1: Requirement gathering
#### Functional Requirements :-
1. user should be able to scheduled jobs at specific time or on a recurring schedule(cron expression e.g,.* * * * *).
2. user should be able to trigger the job execution.<br>Sending an HTTP request to a target service.<br>Pushing a message to a queue(e.g. kafka/SQS).
3. user should able to track the execution tasks(succes , failure retrying) of job.
4. User should be able to list, update or delete(cancel) scheduled jobs
5. User should be able to retries fails job executions

#### Non-functional requirements :-
1. High availability : it should continue to function even if individuals nodes fails 
2. Durability : it should not lost in even in crash 
3. Scalability : can handle the high volumes(like 100 millions)
4. Accuracy/Low Latency : job should scheduled to the close as close to the schduled time

#### Scale & constraints 
1. Total store jobs  : 100 millions
>Requires a partitioned database(sharding)
2. Daily execution : 1 billion
>High write throughput execution logs
3. Peak throughput : 100K-1M QPS 
>Needs efficient polling and dispatching mechanisms
4. job frequency : Min 1 mins 
>Determine the "tick" size of the scheduler


### Step 2 : Back-to-envelope estimation
1. **Assumption(The input)**
>Total registered jobs : 100 millions<br>
> daily executions jobs : 200 millions per day(some jobs run everytime, some once a day)<br>
> Active Peak : Let's assume 1 millions jobs need to trigger within a same mins<br>
> Retention : logs for 1 years<br>


2. Storage estimations

data that need to be store **Job scheduled time** and **logs**<br>

**Job metadata**<br>
Job name,cron schdule, onwer, api endpoint<br>

```java
size =1
KB per
job
Total sotrage :100
millions job
X 1KB ~100 GB
```

100 GB can eaisly fits in RDMS DB like(postgres or mysql)<br>

**Logs**<br>
size per log : 0.5KB<br>
Daily execution : 200 million X 0.5 KB = 100 GB/day<br>
Yearly storage : 100GB X 365 days = 36.5 TB<br>

this we cann't store it in the single mysql or RDMS , we need to do a archival strategy move the logs after 7 days to s3.<br>

3 . **Throughput estimation**<br>
**A: write QPS(Job Registration)**
1. jobs are created rearly compare to execution
2. Estimate 100 new job/seconds

**B: Read/Trigger QPS**<br>
1. 1 millions jobs run at 9:00 am
2. The scheduler has 60 seconds to fire 1 millions jobs <br>
    1 millions job/60 == 16666 triggers/second 

17K QPS is high load. A single server handles 17k QPS, along with DB connections. This confirms that we need **Distributed Cluster** of workers nodes, not a single master.

4. **Bandwith Estimation**<br>
if every job triggers an HTTP call, and we push 17k request/sec<br>
Assuming payload size(0.5 KB) -> 17k X 0.5 = 8.5 MB/S





