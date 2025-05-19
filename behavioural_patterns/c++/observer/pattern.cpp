#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;

class WeatherData{
public:
    int temperature = 10;
    int humidity = 5;

};

class IObserver{
public: 
    virtual void update(WeatherData data) = 0;
    virtual ~IObserver() = default;
};

class TempratureObserver: public IObserver{
public:
    void update(WeatherData data) override{
        cout << "[TEMPRATURE]: " << data.temperature << endl;
    }
};

class HumidityObserver: public IObserver{
public:
    void update(WeatherData data) override{
        cout << "[HUMIDITY]: " << data.humidity << endl;
    }
};

class IWeatherStation {
public:
    virtual void registerObserver(IObserver* observer) = 0;
    virtual void unRegisterObserver(IObserver* observer) = 0;
    virtual void update(WeatherData data)  = 0;
    virtual void notify() = 0;
    virtual string getName() = 0;
    virtual ~IWeatherStation() = default;
};

class BaseWeatherStation: public IWeatherStation {
private:
    string name = "Base Station";
    WeatherData data;
    vector<IObserver*> observers;
public:
    BaseWeatherStation(string name){
        this->name = name;
    }

    void registerObserver(IObserver* observer) override{
        observers.push_back(observer);
    }

    void unRegisterObserver(IObserver* observer) override{
        observers.erase(remove(observers.begin(), observers.end(), observer), observers.end());
    }

    void update(WeatherData data) override{
        this->data = data;
        notify();
    }

    void notify() override{
        for( auto it: observers ){
            it->update(data);
        }
    }

    string getName(){
        return this->name;
    }
};

// if need add more functionality for concrete implementation.
// we inherit baseWeatherStation and have extra functionality for that concrete class.

int main(){
    WeatherData weatherData;
    IWeatherStation* hydStation = new BaseWeatherStation("Hyderabad Station");
    IWeatherStation* chennaiStation = new BaseWeatherStation("Chennai Station");

    cout << hydStation->getName() << endl;
    hydStation->registerObserver(new TempratureObserver());
    hydStation->registerObserver(new HumidityObserver());
    hydStation->update(weatherData);
    cout << endl;
    weatherData.temperature = 20;

    cout << chennaiStation->getName() << endl;
    chennaiStation->registerObserver(new TempratureObserver());
    chennaiStation->registerObserver(new HumidityObserver());
    chennaiStation->update(weatherData);
}