# Template Method Design Pattern

## Overview
The **Template Method** pattern is a **behavioral design pattern** that defines the *skeleton* of an algorithm in a base class but lets subclasses provide specific implementations for some steps. This ensures a consistent process while allowing flexibility for subclasses to define details.

This example demonstrates a **data parsing system** where different file formats (CSV and XML) share a common parsing structure but implement their specific logic.

## Project Structure
```
behavioural_patterns/java/template_method/
│── TemplateMethod.java   
```

## How It Works
1. The `DataParser` abstract class defines the **template method** `parseData()`, which includes:
   - `readData()`: Read data from a file.
   - `processData()`: Process the data.
   - `saveData()`: Save the processed data.
   - The execution sequence is **fixed**.
2. Subclasses (`CSVParser`, `XMLParser`) override the abstract methods to provide **format-specific** implementations.
3. The client (`TemplateMethod.java`) creates objects for `CSVParser` and `XMLParser` and calls `parseData()`, which ensures execution of all steps in a **defined order**.

## Code Explanation
### **Abstract Class (Template Method)**
```java
abstract class DataParser{
    public final void parseData(){
        readData();
        processData();
        saveData();
        System.out.println();
    }

    abstract void readData();
    abstract void processData();
    abstract void saveData();
}
```
- Defines the **skeleton** of the algorithm using `parseData()`.
- Methods `readData()`, `processData()`, and `saveData()` are **abstract** and must be implemented by subclasses.

### **Concrete Implementations**
#### **CSVParser**
```java
class CSVParser extends DataParser{
    @Override
    public void readData() { System.out.println("Reading CSV Data From File"); }
    @Override
    public void processData() { System.out.println("Processing CSV Data..."); }
    @Override
    public void saveData(){ System.out.println("Saving Processed CSV Data"); }
}
```
- Implements the template steps **specific to CSV files**.

#### **XMLParser**
```java
class XMLParser extends DataParser{
    @Override
    public void readData() { System.out.println("Reading XML Data From File"); }
    @Override
    public void processData() { System.out.println("Processing XML Data..."); }
    @Override
    public void saveData(){ System.out.println("Saving Processed XML Data"); }
}
```
- Implements the template steps **specific to XML files**.

### **Client (Main Method Execution)**
```java
class TemplateMethod {
    public static void main(String[] args) {
        DataParser csvParser = new CSVParser();
        DataParser xmlParser = new XMLParser();

        csvParser.parseData();
        xmlParser.parseData();
    }
}
```
- Creates instances of `CSVParser` and `XMLParser`.
- Calls `parseData()`, which triggers the **template method** and executes all required steps.

## Compilation & Execution
```sh
./java.sh TemplateMethod.java
```
## Expected Output
```
Reading CSV Data From File
Processing CSV Data...
Saving Processed CSV Data

Reading XML Data From File
Processing XML Data...
Saving Processed XML Data
```

## Applications
- **Data Processing Pipelines**: Ensuring a structured data transformation process.
- **Game Development**: Defining common game logic while allowing specific mechanics per game type.
- **UI Frameworks**: Defining a standard UI layout but allowing customization of individual elements.
- **Code Generation**: Standardizing template-based code generation.

## Summary
The **Template Method Pattern** is useful when multiple classes share a common algorithm structure but differ in specific implementations. It enforces a **consistent workflow** while allowing customization for individual steps. This example showcases how CSV and XML parsers follow a structured parsing process but define their own behaviors.

