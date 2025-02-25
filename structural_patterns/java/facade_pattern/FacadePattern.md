# Home Automation System using Facade Pattern

## Overview
This project demonstrates the **Facade Pattern** in Java by implementing a **Home Automation System**. The system provides a simplified interface (**Alexa**) to control various home appliances such as **Lights, Fans, Doors, Windows, AC, TV, and Air Purifier**. The user can easily activate or deactivate all essential devices using two simple commands:
- `happyHome()` - Turns on all necessary appliances.
- `sadHome()` - Turns off all appliances.

## Project Structure
```
structural_patterns/java/facade_pattern/
│── FacadePattern.java 
```

## How It Works
1. The **Facade Pattern** provides a simplified interface (**Alexa**) that interacts with multiple home automation devices.
2. The **Alexa class** initializes all the home appliances.
3. The `happyHome()` method turns on all essential appliances, while `sadHome()` turns them off.
4. The **Client (FacadePattern class)** interacts only with the `Alexa` class, without needing to handle individual appliances.

## Code Explanation
### **Subsystem Classes (Lights, Door, Windows, etc.)**
Each class represents a home appliance and provides methods to switch it ON or OFF.
```java
class Lights{
    public void On(){
        System.out.println("Switching on all the lights");
    }
    public void Off(){
        System.out.println("Switching off all the lights");
    }
}
```

### **Facade Class (Alexa)**
This class acts as a single access point to multiple appliances.
```java
class Alexa{
    private Lights lights;
    private Door door;
    private Windows windows;
    private AirConditiong ac;
    private Fans fans;
    private AirPurifier airPurifier;
    private TV tv;

    Alexa(){
        this.lights = new Lights();
        this.door = new Door();
        this.windows = new Windows();
        this.ac = new AirConditiong();
        this.fans = new Fans();
        this.airPurifier = new AirPurifier();
        this.tv = new TV();
    }
    
    public void happyHome(){
        this.door.Open();
        this.lights.On();
        this.ac.On();
        this.fans.On();
        this.windows.Open();
        this.airPurifier.On();
        this.tv.On();
    }
    
    public void sadHome(){
        this.lights.Off();
        this.ac.Off();
        this.fans.Off();
        this.windows.Close();
        this.airPurifier.Off();
        this.tv.Off();
        this.door.Close();
    }
}
```

### **Client Code (FacadePattern)**
The client interacts only with the `Alexa` class.
```java
class FacadePattern {
    public static void main(String[] args) {
        Alexa alexa = new Alexa();
        alexa.happyHome();
        System.out.println();
        alexa.sadHome();
    }
}
```

## Compilation & Execution
```sh
 ./java.sh FacadePattern.java
```

## Expected Output
```
Opening the door.
Switching on all the lights
Switching on the AC
Switching on the Fans with medium speed
Opening Windows Partially for better lighting and Air flow.
Switching on the AirPurifier
Switching on the TV and playing pleasant songs

Switching off all the lights
Switching off the AC
Switching off the fans
Closing all the windows.
Switching off the AirPurifier
Switching off the TV
Closing the door
```

## Applications
- **Smart Home Systems**: Automating multiple home appliances with a single command.
- **Automobile Controls**: Controlling car functions like AC, music, and lights with a unified interface.
- **Enterprise Software**: Simplifying complex subsystems in large applications.

## Summary
This project successfully implements the **Facade Pattern** to simplify interactions with multiple subsystems in a **Home Automation System**. The facade (**Alexa**) provides an easy-to-use interface, reducing dependencies and enhancing modularity.

