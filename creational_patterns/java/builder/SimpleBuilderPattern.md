# Builder Pattern in Java

## Overview
The **Builder Pattern** is a creational design pattern used to construct complex objects step by step. It allows the creation of an object with only the required fields, making the code more readable and manageable.

In this implementation, we have a `Person` class with multiple attributes. Instead of having a constructor with many parameters, we use a **nested static Builder class** to construct `Person` objects in a flexible way.

---

## Implementation Details

### 1. **Person Class**
The `Person` class has four fields:
- `name` (String)
- `age` (Integer)
- `email` (String)
- `description` (String)

The constructor of `Person` is private and is accessed through the `Builder` class.

### 2. **Builder Class**
The `Builder` class is a static inner class of `Person` and provides **setter-like methods** for assigning values to attributes. Each method returns the builder instance itself, enabling method chaining.

The `build()` method constructs and returns a `Person` object using the values set in the builder.

### 3. **Client Code (SimpleBuilderPattern Class)**
The `main` method demonstrates the use of the **Builder Pattern** by creating two `Person` objects (`vineel` and `dhoni`) and displaying their attributes.

---

## Code Explanation
```java
// Creating Person 1
Person vineel = new Person.Builder()
                .setName("Vineel Pynam")
                .setAge(25)
                .setDescription("A software Engineer")
                .setEmail("vineel@gmail.com")
                .build();

vineel.display();

// Creating Person 2
Person dhoni = new Person.Builder()
                .setName("MS Dhoni")
                .setAge(44)
                .setDescription("Indian Cricketer")
                .setEmail("dhoni@gmail.com")
                .build();

dhoni.display();
```

---

## Benefits of Using the Builder Pattern
- **Improves Readability**: No need to pass multiple arguments to the constructor.
- **Flexible Object Construction**: Optional parameters can be skipped easily.
- **Immutability**: Once the object is built, it cannot be modified.
- **Method Chaining**: Makes object creation clean and concise.

---

## Output of the Program
```
[Name]: Vineel Pynam
[Age]: 25
[Email]: vineel@gmail.com
[Description]: A software Engineer

[Name]: MS Dhoni
[Age]: 44
[Email]: dhoni@gmail.com
[Description]: Indian Cricketer
```

---

## Conclusion
The **Builder Pattern** is a powerful design pattern for creating objects in a structured and readable way. It is particularly useful when dealing with objects that have multiple attributes and optional parameters.
