#include<iostream>
#include<memory>
#include<string>
using namespace std;

class Car{
private:
    string name;
    string body;
    string tyres;
    string steering;
    string electricals;
public:
    Car(const string& name){
        this->name = name;
    }

    void setBody(const string& body){
        this->body = body;
    }

    void setTyres(const string& tyres){
        this->tyres = tyres;
    }

    void setSteering(const string& steering){
        this->steering = steering;
    }

    void setElectricals(const string& electricals){
        this->electricals = electricals;
    }

    void display(){
        cout << "[NAME]: " << name << endl;
        cout << "[BODY]: " << body << endl;
        cout << "[STEERING]: " << steering << endl;
        cout << "[TYRES]: " << tyres << endl;
        cout << "[ELECTRICALS]: " << electricals << endl;
    }
};

class CarBuilder{
public:
    virtual CarBuilder& buildBody() = 0;
    virtual CarBuilder& buildTyres() = 0;
    virtual CarBuilder& buildSteering() = 0;
    virtual CarBuilder& buildElectricals() = 0;
    virtual shared_ptr<Car> build() = 0;
    virtual ~CarBuilder() = default;
};

class Skoda: public CarBuilder{
private:
    shared_ptr<Car> car = make_shared<Car>("Skoda");
public:
    CarBuilder& buildBody() override{
        car->setBody("Building Skoda Body");
        return *this;
    }

    CarBuilder& buildSteering() override{
        car->setSteering("Building Skoda Steering");
        return *this;
    }

    CarBuilder& buildTyres() override{
        car->setTyres("Building Skoda Tyres");
        return *this;
    }

    CarBuilder& buildElectricals() override{
        car->setElectricals("Building Skoda Electricals");
        return *this;
    }

    shared_ptr<Car> build() override{
        return car;
    }
};

class Maruti: public CarBuilder{
private:
    shared_ptr<Car> car = make_shared<Car>("Maruti");
public:
    CarBuilder& buildBody() override{
        car->setBody("Building Maruti Body");
        return *this;
    }

    CarBuilder& buildSteering() override{
        car->setSteering("Building Maruti Steering");
        return *this;
    }

    CarBuilder& buildTyres() override{
        car->setTyres("Building Maruti Tyres");
        return *this;
    }

    CarBuilder& buildElectricals() override{
        car->setElectricals("Building Maruti Electricals");
        return *this;
    }

    shared_ptr<Car> build() override{
        return car;
    }
};

class CarDirector {
public:
    static shared_ptr<Car> makeCar(CarBuilder& carBuilder){
        return carBuilder.buildBody().buildSteering().buildTyres().buildElectricals().build();
    }
};

int main(){
   Skoda skoda;
   shared_ptr<Car> builtCar = CarDirector::makeCar(skoda);
   shared_ptr<Car> builtCar2 = CarDirector::makeCar(skoda);
   builtCar->display();

   cout << endl << "Only works if shared_ptr" << endl;
   builtCar2->display();

   cout << endl;

   Maruti maruti;
   builtCar = CarDirector::makeCar(maruti);
   builtCar->display();
}