package behavioural_patterns.java.observer_pattern;

import java.util.*;

// WeatherData Pojo
class WeatherData{
    private Integer temparature;
    private Integer humidity;

    WeatherData(int temperature, int humidity){
        this.temparature = temperature;
        this.humidity = humidity;
    }

    public Integer getTemparature(){
        return this.temparature;
    }

    public Integer getHumidity(){
        return this.humidity;
    }
}

// Observer interface
interface IObserver{
    String getName();
    void update(WeatherData weatherData);
}

// Concrete Classes Implementing IObserver
class WeatherDisplayer implements IObserver{
    private String name = "Weather Displayer";
    private Integer temparature;

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void update(WeatherData weatherData){
        this.temparature = weatherData.getTemparature();
        display();
    }

    private void display(){
        System.out.println("[Temparature]: " + this.temparature + " Dc");
    }
}

class HumidityDisplayer implements IObserver{
    private String name = "Humidity Displayer";
    private Integer humidity;

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void update(WeatherData weatherData){
        this.humidity = weatherData.getHumidity();
        display();
    }

    private void display(){
        System.out.println("[Humidity]: " + this.humidity + " Dc");
    }
}

// IWeatherStation
interface IWeatherStation {
    public void register(IObserver observer);
    public void unRegister(IObserver observer);
    public void produce(WeatherData weatherData);
}

// Concrete weather stations (Subjects) Implementing Weather Station
class HyderabadWeatherStation implements IWeatherStation{
    List<IObserver> observers = new ArrayList<>();
    private WeatherData weatherData;

    HyderabadWeatherStation(){
        System.out.println("Hyderabad Weather Station: ");
    }

    @Override
    public void produce(WeatherData weatherData){
        this.weatherData = weatherData;
        this.updateAll();
    }

    @Override
    public void register(IObserver observer) {
        observers.add(observer);
        System.out.println("[ADDED]: " + observer.getName());
    }

    @Override
    public void unRegister(IObserver observer) {
        observers.remove(observer);
        System.out.println("[REMOVED]: " + observer.getName());
    }

    private void updateAll() {
        for (IObserver observer : observers) {
            observer.update(weatherData);
        }
    }
}

class BangloreWeatherStation implements IWeatherStation{
    List<IObserver> observers = new ArrayList<>();
    private WeatherData weatherData;

    BangloreWeatherStation(){
        System.out.println("Banglore Weather Station: ");
    }

    @Override
    public void produce(WeatherData weatherData){
        this.weatherData = weatherData;
        this.updateAll();
    }

    @Override
    public void register(IObserver observer) {
        observers.add(observer);
        System.out.println("[ADDED]: " + observer.getName());
    }

    @Override
    public void unRegister(IObserver observer) {
        observers.remove(observer);
        System.out.println("[REMOVED]: " + observer.getName());
    }

    private void updateAll() {
        for (IObserver observer : observers) {
            observer.update(weatherData);
        }
    }
}

class ObserverPattern {
    public static void main(String[] args) {
        // Hyderabad Locaton
        WeatherData weatherData1 = new WeatherData(40, 20);
        IWeatherStation hyd = new HyderabadWeatherStation();
        hyd.register(new WeatherDisplayer());
        hyd.register(new HumidityDisplayer());
        hyd.produce(weatherData1);

        System.out.println();
        
        // Banglore Locaton
        WeatherData weatherData2 = new WeatherData(22, 29);
        IWeatherStation bng = new BangloreWeatherStation();
        IObserver weatherDislayer = new WeatherDisplayer();
        IObserver humidityDisplayer = new HumidityDisplayer();
        bng.register(weatherDislayer);
        bng.register(humidityDisplayer);
        bng.unRegister(humidityDisplayer);
        bng.produce(weatherData2);
    }
}
