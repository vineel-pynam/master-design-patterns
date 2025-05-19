#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<memory>
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
    virtual void registerObserver(shared_ptr<IObserver> observer) = 0;
    virtual void unRegisterObserver(shared_ptr<IObserver> observer) = 0;
    virtual void update(WeatherData data)  = 0;
    virtual void notify() = 0;
    virtual string getName() = 0;
    virtual ~IWeatherStation() = default;
};

class BaseWeatherStation: public IWeatherStation {
private:
    string name = "Base Station";
    WeatherData data;
    vector<shared_ptr<IObserver>> observers;
public:
    BaseWeatherStation(string name){
        this->name = name;
    }

    void registerObserver(shared_ptr<IObserver> observer) override{
        observers.push_back(observer);
    }

    void unRegisterObserver(shared_ptr<IObserver> observer) override{
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
    shared_ptr<IWeatherStation> hydStation = make_shared<BaseWeatherStation>("Hyderabad Station");
    shared_ptr<IWeatherStation> chennaiStation = make_shared<BaseWeatherStation>("Chennai Station");

    cout << hydStation->getName() << endl;
    hydStation->registerObserver(make_shared<TempratureObserver>());
    hydStation->registerObserver(make_shared<HumidityObserver>());
    hydStation->update(weatherData);
    cout << endl;
    weatherData.temperature = 20;

    cout << chennaiStation->getName() << endl;
    chennaiStation->registerObserver(make_shared<TempratureObserver>());
    chennaiStation->registerObserver(make_shared<HumidityObserver>());
    chennaiStation->update(weatherData);
}