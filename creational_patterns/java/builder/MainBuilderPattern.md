# Builder Pattern in Java - Car Construction

## Overview
The **Builder Pattern** is a creational design pattern that allows the step-by-step construction of complex objects. It provides a flexible and clear approach for object creation, especially when dealing with multiple configurations of the same object.

In this implementation, we demonstrate the **Builder Pattern** by creating cars with different features but following a common construction process.

---

## Implementation Details

### 1. **Car Class**
The `Car` class represents the product that we want to build. It has various attributes:
- `name`
- `body`
- `tyres`
- `steering`
- `electricals`

The class also includes setter methods to assign values and a `display()` method to print the details of the car.

### 2. **CarBuilder (Abstract Builder Class)**
The `CarBuilder` is an abstract class that provides a structure for building different car models. It includes the following abstract methods:
- `makeBody()`
- `addName()`
- `addSteering()`
- `addTypres()`
- `addElectricals()`
- `build()`

Each method is designed to return an instance of `CarBuilder`, enabling method chaining.

### 3. **Concrete Builders (SkodaCar & VWCar)**
- `SkodaCar` and `VWCar` are concrete implementations of `CarBuilder`.
- Each class implements the abstract methods to provide car-specific attributes such as branding, materials, and components.

### 4. **CarDirector (Director Class)**
The `CarDirector` is responsible for directing the car-building process. It ensures that the construction steps are executed in a defined sequence.
```java
class CarDirector{
    public static Car makeCar(CarBuilder carBuilder){
        return carBuilder.makeBody()
                    .addName()
                    .addElectricals()
                    .addTypres()
                    .addSteering()
                    .build();
    }
}
```

### 5. **Client Code (MainBuilderPattern Class)**
The `MainBuilderPattern` class serves as the client that uses the builder pattern to construct `Car` objects.
```java
class MainBuilderPattern {
    public static void main(String[] args) {
        // Skoda Car
        CarBuilder skoda = new SkodaCar();
        Car car = CarDirector.makeCar(skoda);
        car.display();

        // VW Car
        CarBuilder vw = new VWCar();
        car = CarDirector.makeCar(vw);
        car.display();
    }
}
```

---

## Output of the Program
```
[Body]: Making body High Quality Steel
[Name]: Skoda
[Tyres]: Adding Alloy Tyres with Skoda branding
[Steering]: Adding Steering with Skoda Branding
[Electricals]: Adding all required electricals from Bosch

[Body]: Making body High Quality Steel
[Name]: VW
[Tyres]: Adding Alloy Tyres with VW branding
[Steering]: Adding Steering with VW Branding
[Electricals]: Adding all required electricals from Bosch
```

---

## Benefits of the Builder Pattern
- **Encapsulates Construction Logic**: Separates object construction from the object itself.
- **Provides Flexibility**: Enables building objects with different configurations.
- **Ensures a Consistent Build Process**: The `CarDirector` ensures a standardized construction sequence.
- **Improves Readability and Maintainability**: Reduces the complexity of object instantiation.

---

## Conclusion
The **Builder Pattern** is useful for constructing objects with multiple configurations while ensuring a structured and readable approach. This pattern is widely used in scenarios where object creation involves multiple steps and optional parameters.