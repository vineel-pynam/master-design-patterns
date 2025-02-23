# Factory Method Pattern - Java Implementation

## Overview
The **Factory Method Pattern** is a **creational design pattern** that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. This promotes loose coupling and adheres to the **Open/Closed Principle (OCP)**.

This project implements a **Factory Method Pattern** for logging messages using different log levels (**INFO, DEBUG, ERROR**).

---

## Project Structure
```
creational_patterns/
│── java/
│   ├── factory_method/
│   │   ├── FactoryMethod.java
```

---

## How It Works

### 1. **ILogger (Interface)**
- Defines a common logging behavior for different log types.
- Any logger class implementing this interface must define a `log()` method.

### 2. **Concrete Logger Classes**
- Implements `ILogger` and provides specific logging behavior.
- `ErrorLogger` → Logs error messages.
- `DebugLogger` → Logs debug messages.
- `InfoLogger` → Logs informational messages.

### 3. **ILoggerFactory (Factory Interface)**
- Defines the method `createLogger()` which is implemented by concrete factories.
- This ensures that different logger instances can be created dynamically.

### 4. **Concrete Factories**
- `ErrorLoggerFactory` → Creates `ErrorLogger`.
- `DebugLoggerFactory` → Creates `DebugLogger`.
- `InfoLoggerFactory` → Creates `InfoLogger`.

### 5. **Client Code (`FactoryMethod` Class)**
- Calls specific factory implementations to create logger objects dynamically.
- Uses the `ILoggerFactory` interface to instantiate the required logger.

---

## Code Explanation

### 1. **Logger Interface**
```java
interface ILogger {
    void log(String message);
}
```
Defines a common interface for all logger types.

### 2. **Concrete Logger Classes**
```java
class ErrorLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println("[ERROR]: " + message);
    }
}
```
Similar implementations exist for `DebugLogger` and `InfoLogger`.

### 3. **Factory Interface**
```java
interface ILoggerFactory {
    public ILogger createLogger();
}
```
Ensures that any factory implementing it must define the `createLogger()` method.

### 4. **Concrete Factory Classes**
```java
class ErrorLoggerFactory implements ILoggerFactory {
    @Override
    public ILogger createLogger() {
        return new ErrorLogger();
    }
}
```
Similar factory classes exist for `DebugLoggerFactory` and `InfoLoggerFactory`.

### 5. **Client Code (Main Method)**
```java
class FactoryMethod {
    public static void main(String args[]) {
        ILoggerFactory loggerFactory = new InfoLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        loggerFactory = new DebugLoggerFactory();
        logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        loggerFactory = new ErrorLoggerFactory();
        logger = loggerFactory.createLogger();
        logger.log("Hola..!");
    }
}
```

### **Key Features**
✅ **Encapsulation** - Object creation logic is encapsulated in factory classes.
✅ **Loose Coupling** - Client code does not need to know about specific loggers.
✅ **Scalability** - New logger types can be added without modifying existing code.

---

## **Usage**
### **Compilation & Execution**
```bash
javac creational_patterns/java/factory_method/FactoryMethod.java
java creational_patterns.java.factory_method.FactoryMethod
```

### **Expected Output**
```
[INFO]: Hola..!
[DEBUG]: Hola..!
[ERROR]: Hola..!
```

---

## **Advantages of Factory Method Pattern**
✅ **Follows SOLID Principles** - Particularly the Open/Closed Principle (OCP).
✅ **Easier Maintainability** - New loggers can be added without modifying existing code.
✅ **Improved Testability** - Easier to mock objects for testing.

---

## **Conclusion**
The **Factory Method Pattern** helps in creating objects dynamically while ensuring loose coupling and flexibility. This example demonstrates how to implement the pattern effectively for a logging system in Java.

