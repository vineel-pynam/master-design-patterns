# Abstract Factory Pattern - Java Implementation

## Overview
The **Abstract Factory Pattern** is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. This pattern is particularly useful when a system needs to support multiple types of related objects that should be created in a consistent manner.

In this implementation, we demonstrate an **Abstract Factory Pattern** for creating UI components (buttons and text boxes) for different operating systems (**Windows** and **Mac**).

---

## Folder Structure
```
creational_patterns/java/abstract_factory/
    |-- AbstractFactory.java
```

---

## Components of the Implementation

### 1. Enum for OS Type
```java
enum OS_TYPE {
    WINDOWS, MAC;
}
```
This enum represents the different operating systems supported.

### 2. Button Interface and Implementations
```java
interface IButton {
    void pressButton();
}
```
This interface defines the behavior of a button. It has two implementations:

- **WindowsButton**: Implements button for Windows OS.
- **MacButton**: Implements button for Mac OS.

### 3. TextBox Interface and Implementations
```java
interface ITextBox {
    void enterText();
}
```
This interface defines the behavior of a text box. It has two implementations:

- **WindowsTextBox**: Implements text box for Windows OS.
- **MacTextBox**: Implements text box for Mac OS.

### 4. Operating System Interface and Implementations
```java
interface IOperatingSystem {
    void pressButton();
    void enterText();
}
```
This interface defines how an operating system interacts with UI elements.
Each OS has its own implementation:

- **WindowsOS**: Uses Windows UI components.
- **MacOS**: Uses Mac UI components.

### 5. Abstract Factory - OSFactory
```java
class OSFactory {
    public IOperatingSystem getOS(OS_TYPE os) {
        if (os == OS_TYPE.WINDOWS) {
            return new WindowsOS();
        } else if (os == OS_TYPE.MAC) {
            return new MacOS();
        }
        return new WindowsOS();
    }
}
```
The **OSFactory** class is responsible for creating objects based on the given OS type.

### 6. Client Code - AbstractFactory.java
```java
class AbstractFactory {
    public static void main(String args[]) {
        OSFactory osFactory = new OSFactory();
        
        // Mac Example
        IOperatingSystem os = osFactory.getOS(OS_TYPE.MAC);
        os.pressButton();
        os.enterText();

        // Windows Example
        os = osFactory.getOS(OS_TYPE.WINDOWS);
        os.pressButton();
        os.enterText();
    }
}
```
The **Client (AbstractFactory.java)** requests an OS instance from `OSFactory`, which then provides the appropriate UI elements.

---

## How to Run the Code
1. Navigate to the project directory:

2. Compile & Run the code:
   ```sh
   ./java.sh AbstractFactory.java
   ```

---

## Expected Output
```
Pressed Mac Button
Entered Text in Mac TextBox
Pressed Windows Button
Entered Text in Windows TextBox
```
This output confirms that the correct UI elements are created for each OS type.

---

## Summary
- The **Abstract Factory Pattern** is used to create families of related objects.
- Here, we used it to create UI components (**Buttons & TextBoxes**) for **Windows** and **Mac**.
- **OSFactory** acts as the abstract factory that provides the appropriate OS UI elements.
- The client code (`AbstractFactory.java`) demonstrates how to use the factory to create objects dynamically.

This pattern ensures **scalability** and **maintainability**, allowing us to add new OS types easily without modifying existing code.

