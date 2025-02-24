# Observer Pattern - Weather Station Implementation

## Overview
The **Observer Pattern** is a behavioral design pattern where an object (called the subject) maintains a list of dependent objects (observers) that are notified of any changes in its state. This is useful for implementing distributed event-handling systems.

This project implements the **Observer Pattern** by simulating weather stations that notify weather observers (displays) whenever weather conditions change.

## Project Structure
```
behavioural_patterns/java/observer_pattern/
│-- WeatherData.java        // POJO class holding weather information
│-- IObserver.java          // Observer interface
│-- WeatherDisplayer.java   // Concrete Observer for temperature display
│-- HumidityDisplayer.java  // Concrete Observer for humidity display
│-- IWeatherStation.java    // Subject (Weather Station) interface
│-- HyderabadWeatherStation.java // Concrete Subject (Hyderabad)
│-- BangloreWeatherStation.java  // Concrete Subject (Bangalore)
│-- ObserverPattern.java    // Client class (Main)
```

## How It Works
1. The **WeatherData** class represents weather conditions such as temperature and humidity.
2. The **IObserver** interface defines the contract for observers that must implement an `update(WeatherData weatherData)` method.
3. **WeatherDisplayer** and **HumidityDisplayer** are concrete observers that display temperature and humidity, respectively.
4. The **IWeatherStation** interface represents the weather station (subject) with methods to:
   - `register(IObserver observer)`: Add an observer.
   - `unRegister(IObserver observer)`: Remove an observer.
   - `produce(WeatherData weatherData)`: Notify observers with new weather data.
5. **HyderabadWeatherStation** and **BangloreWeatherStation** are concrete subjects that maintain observer lists and notify them of weather changes.
6. The **ObserverPattern (Main class)** demonstrates how observers subscribe to stations and get updates when weather data changes.

## Code Explanation

### 1. **WeatherData (POJO)**
This class stores temperature and humidity values:
```java
class WeatherData {
    private Integer temparature;
    private Integer humidity;
    
    public WeatherData(int temperature, int humidity) {
        this.temparature = temperature;
        this.humidity = humidity;
    }
    
    public Integer getTemparature() {
        return this.temparature;
    }
    
    public Integer getHumidity() {
        return this.humidity;
    }
}
```

### 2. **Observer Interface**
Defines the `update()` method to be implemented by concrete observers:
```java
interface IObserver {
    String getName();
    void update(WeatherData weatherData);
}
```

### 3. **Concrete Observers**
- `WeatherDisplayer` displays temperature updates.
- `HumidityDisplayer` displays humidity updates.
```java
class WeatherDisplayer implements IObserver {
    private String name = "Weather Displayer";
    private Integer temparature;

    public void update(WeatherData weatherData) {
        this.temparature = weatherData.getTemparature();
        display();
    }
    private void display() {
        System.out.println("[Temperature]: " + this.temparature + "°C");
    }
}
```

### 4. **Subject Interface (Weather Station)**
The interface that allows observers to register and unregister:
```java
interface IWeatherStation {
    void register(IObserver observer);
    void unRegister(IObserver observer);
    void produce(WeatherData weatherData);
}
```

### 5. **Concrete Weather Stations (Subjects)**
`HyderabadWeatherStation` and `BangloreWeatherStation` manage observers and notify them:
```java
class HyderabadWeatherStation implements IWeatherStation {
    List<IObserver> observers = new ArrayList<>();
    public void register(IObserver observer) {
        observers.add(observer);
    }
    public void unRegister(IObserver observer) {
        observers.remove(observer);
    }
    public void produce(WeatherData weatherData) {
        for (IObserver observer : observers) {
            observer.update(weatherData);
        }
    }
}
```

### 6. **Client Code (Main Class)**
Registers observers and produces weather updates:
```java
public class ObserverPattern {
    public static void main(String[] args) {
        IWeatherStation hyd = new HyderabadWeatherStation();
        hyd.register(new WeatherDisplayer());
        hyd.register(new HumidityDisplayer());
        hyd.produce(new WeatherData(40, 20));
    }
}
```

## Compilation & Execution
```sh
./java.sh ObserverPattern.java
```
## Expected Output
```
Hyderabad Weather Station:
[ADDED]: Weather Displayer
[ADDED]: Humidity Displayer
[Temperature]: 40°C
[Humidity]: 20°C

Banglore Weather Station:
[ADDED]: Weather Displayer
[ADDED]: Humidity Displayer
[REMOVED]: Humidity Displayer
[Temperature]: 22°C
```

## Applications
✅ **Real-time Monitoring Systems:** Used in weather updates, stock prices, and news feeds.
✅ **Event-Driven Systems:** GUI components, message queues, and notifications.
✅ **Distributed Systems:** Push notifications, microservices, and logging frameworks.

## Summary
- **Observer Pattern** helps in designing loosely coupled systems where multiple observers react to changes in a subject.
- **Weather Station Simulation** showcases how observers receive updates when weather data changes.
- This pattern is widely used in real-world event-driven architectures like GUI applications, stock monitoring systems, and more.