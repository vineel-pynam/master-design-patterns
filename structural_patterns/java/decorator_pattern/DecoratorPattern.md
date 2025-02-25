# Decorator Pattern - Coffee Customization System

## Overview
The **Decorator Pattern** is a structural design pattern used to dynamically add behavior to objects without modifying their structure. This project demonstrates the **Decorator Pattern** by implementing a coffee customization system, where different coffee types can be decorated with additional ingredients such as **Milk, Sugar, and Lemon**.

## Project Structure
```
structural_patterns/java/decorator_pattern/
│── DecoratorPattern.java
```

## How It Works
1. **Base Interface (`Coffee`)** - Defines the structure for coffee types.
2. **Concrete Implementations (`FilterCoffee`, `BlackCoffee`)** - Basic coffee types.
3. **Abstract Decorator (`CoffeeDecorator`)** - Holds a reference to a `Coffee` object and extends its functionality.
4. **Concrete Decorators (`MilkDecorator`, `SugarDecorator`, `LemonDecorator`)** - Modify the behavior of a `Coffee` instance.
5. **Client (`DecoratorPattern.java`)** - Demonstrates decorating `Coffee` objects dynamically.

## Code Explanation
### Coffee Interface
```java
interface Coffee{
    String getDescription();
    Double getCost();
}
```
Defines the basic contract for coffee types.

### Concrete Coffee Implementations
```java
class FilterCoffee implements Coffee{
    public String getDescription(){ return "Filter Coffee"; }
    public Double getCost(){ return 20.00; }
}

class BlackCoffee implements Coffee{
    public String getDescription(){ return "Black Coffee"; }
    public Double getCost(){ return 30.00; }
}
```
These represent different types of coffee with a base cost.

### Abstract Decorator
```java
abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;
    CoffeeDecorator(Coffee coffee){ this.coffee = coffee; }
}
```
Allows decorators to extend coffee functionality without modifying the original coffee classes.

### Concrete Decorators
```java
class MilkDecorator extends CoffeeDecorator{
    public String getDescription(){ return coffee.getDescription() + " With Added Milk"; }
    public Double getCost(){ return coffee.getCost() + 10.00; }
}

class SugarDecorator extends CoffeeDecorator{
    public String getDescription(){ return coffee.getDescription() + " With Added Sugar"; }
    public Double getCost(){ return coffee.getCost() + 5.00; }
}

class LemonDecorator extends CoffeeDecorator{
    public String getDescription(){ return coffee.getDescription() + " With Added Lemon"; }
    public Double getCost(){ return coffee.getCost() + 7.00; }
}
```
Each decorator modifies the coffee’s behavior by adding an ingredient and increasing its cost.

### Client Code
```java
class DecoratorPattern {
    public static void main(String[] args) {
        Coffee filterCoffee = new FilterCoffee();
        filterCoffee = new MilkDecorator(filterCoffee);
        filterCoffee = new SugarDecorator(filterCoffee);
        
        Coffee blackCoffee = new BlackCoffee();
        blackCoffee = new LemonDecorator(blackCoffee);
        blackCoffee = new SugarDecorator(blackCoffee);

        System.out.println("[ITEM]: " + filterCoffee.getDescription());
        System.out.println("[COST]: "+ filterCoffee.getCost());
        
        System.out.println();
        
        System.out.println("[ITEM]: " + blackCoffee.getDescription());
        System.out.println("[COST]: "+ blackCoffee.getCost());
    }
}
```
This dynamically decorates the coffee objects with extra ingredients.

## Compilation & Execution
```sh
./java.sh DecoratorPattern.java
```

## Expected Output
```
[ITEM]: Filter Coffee With Added Milk With Added Sugar
[COST]: 35.0

[ITEM]: Black Coffee With Added Lemon With Added Sugar
[COST]: 42.0
```
This shows how each decorator modifies the coffee’s description and cost dynamically.

## Applications
- **Extending UI Components** (e.g., adding scrollbars, borders dynamically)
- **Enhancing logging mechanisms** (e.g., adding timestamps, different log levels)
- **File I/O Streams** in Java (e.g., `BufferedReader` wrapping `FileReader`)

## Summary
- The **Decorator Pattern** provides a flexible approach to extending object behavior without modifying existing code.
- It promotes **composition over inheritance**, making the system **scalable** and **extensible**.
- In this project, coffee customization is achieved dynamically using decorators.

This approach ensures that new ingredients can be added **without modifying** existing coffee types, following the **Open-Closed Principle**.

