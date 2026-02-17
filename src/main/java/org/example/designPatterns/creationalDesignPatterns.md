# Creational Design Patterns

## 1. Factory Method Pattern

### Definition
Provides an interface for creating objects but lets subclasses decide which object to create.It encapsulates object creation logic.

### When to use
1. When object creation depends on input at runtime.
2. When you want to avoid large if/else or switch creation logic.
3. When subclass decides which object to create.

### Real-world Analogy
coffee Shop -> You order a "Latte","Cappuccino", etc.<br>
The barista(factory) prepares the correct object.<br>

```java
    interface Notification {void notifyUser();}

    class SMSNotification implements Notification{
        public void notifyUser() {System.out.println("SMS Sent");}
    }
    
    class EmailNotification implements Notification{
        public void notifyUser() {System.out.println("Email Sent");
      }
      
    class NotificationFactory{
            public static Notification getNotification(String type){
                return switch (type) {
                    case "SMS" -> new SMSNotification();
                    case "EMAIL" -> new EmailNotification();
                    default -> throw new IllegalArgumentException();
                };
            }
    }  
    }
```

### Common Pitfalls
1. Too many subclasses -> consider Abstract Factory instead?
2. Factory may beome too complex.

### Typical Interview Questions
1. Difference between Factory Method & Abstract Factory?
2. Why is Factory Method better than a constructor?
3. How does Spring use Factory Method?

## 2 . Abstract Factory Pattern

### Definition 

Provides an interface for creating families of related objects without specifying their concrete classes.

### When to use

1. When you need consistency in object families.
2. Example:light them vs dark theme UI components.
3. when you need scalable product families.

### Analogy

Furniture store -> victorian sofa + Victorian chair<br>
OR<br>
Modern sofa + Modern chair<br>

```java
interface Chair {}
interface Sofa {}

class ModernChair implements chair {}
class ModernSofa implements Sofa {}

interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

class ModernFurnitureFactory implements FurnitureFactory{
    public Chair createChair() {return new ModernChair();}
    public Sofa createSofa() {return  new ModernSofa();}
}
```

### Pitfalls
1. Too many factories = more classes.
2. Best used with DDD bounced context.

### Interview Questions
1. Compare Factory Method vs Abstract Factory.
2. Real-world cases in enterprise systems?
3. How do UI libraries use Abstract Factory?

## 3. Builder Pattern

### Definition
Used to complex objects step-by-step, especially when constructors have many parameters.

### when to use
1. Object has many optional arguments.
2. Prevent telescoping constructors
3. Want immutable objects.

### Real-world Analogy
Zomato Order Builder<br>
Item->Address->Payment->Instructions<br>

```java
class User{
    private final String name;
    private final int age;
    private final String email;
    
    public static class Builder{
        private String name;
        private int age;
        private String email;
        
        public Builder setName(String name) {this.name = name; return this;}
        public Builder setAge(int age) {this.age=age; return this;}
        public Builder setEmail(String email) {this.email=email; return this;}
        
        public User build(){ return new User(this);}
        
    }
    
    private User(Builder b){
        this.name = b.name;
        this.age = b.age;
        this.email = b.email;
    }
}
```

### Interview Questions

1. How does Builder improve readability?
2. Why is Builder preferred for immutability?
3. Difference between Builder and Factory?

## 4. Prototype Pattern

### Definition

Create new objects by cloning an existing object instead of expensive initialization.

### When to use
1. object creation is costly(large configuration, DB fetch).
2. You want to copy an object with modifications.

### Real-World Analogy
Duplicate ID card -> Copy print from existing card

```java
class Employee implements Cloneable{
    int id;
    String name;
    
    public Employee clone() throws CloneNotSupportedException{
        return (Employee) super.clone();
    }
}
```

### interview Questions

1. How do you implement deep copy?
2. Pros/cons of Protoype?
3. when is Prototype better than Builder?

## 5. Singleton Pattern

### Definition
Ensures only one instance of a class exists.

### When to use
1. Config managers
2. Logging
3. Cache
4. Database connection pools

### Real-World Analogy
One Prime Minister of a country -> Only one at a time.

```java
class  Singleton{
    private static final Singleton INSTANCE = new Singleton();
    private Singleton(){}
    public static Singleton getInstance() {return INSTANCE;}
}
```

### Interview Questions
1. How to break Singleton?(Reflection, Serialization)
2. how to make Singleton thread-safe?
3. When NOT to use Singleton?


# Creational Design Pattern

>Pattern related to class and object composition.

## Adaptor Pattern

### Definition

Convert one interface to another

### Real-World example:
Charger adapter -> USB to Type-C

```java
class LegacyPrinter{
    void printOld(){System.out.println("Old Printer");}
}

interface NewPrinter {void print();}

class PrinterAdapter implements NewPrinter{
    private LegacyPrinter lp = new LeagacyPrinter();
    public void print(){lp.printOld();}
}
```