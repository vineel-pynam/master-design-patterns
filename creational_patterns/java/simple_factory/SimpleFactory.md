# Simple Factory Pattern

## Overview
The **Simple Factory Pattern** is a creational design pattern that provides a single factory method to create instances of different classes based on input parameters. This pattern is useful when the client does not need to know which exact class it is instantiating and instead delegates the creation logic to a factory class.

## Implementation
In this implementation, we have a **Logger Factory** that produces different types of loggers (INFO, DEBUG, ERROR) based on the log type provided by the client.

### Components:
1. **LogType (Enum)** - Defines different types of loggers.
2. **ILogger (Interface)** - Provides a common interface for all logger types.
3. **Concrete Logger Classes (ErrorLogger, DebugLogger, InfoLogger)** - Implements the ILogger interface.
4. **LoggerFactory (Factory Class)** - Creates instances of different loggers.
5. **SimpleFactory (Client Class)** - Uses the LoggerFactory to get logger instances and log messages.

## Code Explanation

### 1. Defining Log Types (Enum)
```java
enum LogType{
    ERROR, DEBUG, INFO
}
```
- Represents different log types.

### 2. ILogger Interface
```java
interface ILogger{
    void log(String message);
}
```
- Defines a common method `log()` for all loggers.

### 3. Concrete Logger Classes
```java
class ErrorLogger implements ILogger{
    @Override
    public void log(String message){
        System.out.println("[ERROR]: " + message);
    }
}
```
- Implements `ILogger` and provides specific logging behavior.

### 4. LoggerFactory (Factory Class)
```java
class LoggerFactory{
    public ILogger createLogger(){
        return new InfoLogger();
    }

    public ILogger createLogger(LogType logType){
        switch (logType){
            case INFO:
                return new InfoLogger();
            case DEBUG:
                return new DebugLogger();
            case ERROR:
                return new ErrorLogger();
            default:
                return new InfoLogger();
        }
    }
}
```
- The factory provides a default logger (`InfoLogger`) if no log type is specified.
- If a `LogType` is provided, it returns the corresponding logger instance.

### 5. Client (SimpleFactory Class)
```java
class SimpleFactory{
    public static void main(String args[]){
        LoggerFactory loggerFactory = new LoggerFactory();

        // Default Logger
        ILogger logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        // Specific Loggers
        logger = loggerFactory.createLogger(LogType.DEBUG);
        logger.log("Hola..!");

        logger = loggerFactory.createLogger(LogType.ERROR);
        logger.log("Hola..!");
    }
}
```
- The client requests loggers from `LoggerFactory`.
- Logs messages using different loggers.

## Usage
- **When to Use:**
  - When object creation logic is centralized in one place.
  - When client code should not be aware of object creation details.
  - When object creation is based on a parameter (e.g., different log types).

- **Benefits:**
  - Encapsulates object creation logic.
  - Improves maintainability and scalability.
  - Reduces code duplication.

- **Limitations:**
  - Does not follow the **Open-Closed Principle (OCP)** since modifying the factory requires changing its code.

## Conclusion
The **Simple Factory Pattern** provides a straightforward way to create objects, improving code organization and maintainability. However, for more flexibility and adherence to SOLID principles, consider using **Factory Method Pattern** or **Abstract Factory Pattern** in more complex scenarios.

