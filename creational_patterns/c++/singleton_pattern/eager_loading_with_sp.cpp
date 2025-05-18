#include<iostream>
#include<memory>
using namespace std;

class Singleton {
private:
    Singleton() = default;

    static shared_ptr<Singleton> createInstance(){
        return shared_ptr<Singleton>(new Singleton());
    }

public:
    Singleton(const Singleton&) = delete;
    Singleton(const Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(const Singleton&&) = delete;

    static shared_ptr<Singleton> getInstance(){
        return instance;
    }
    
    static shared_ptr<Singleton> instance;   
};

shared_ptr<Singleton> Singleton::instance = Singleton::createInstance();

int main(){
    shared_ptr<Singleton> s1 = Singleton::getInstance();
    shared_ptr<Singleton> s2 = Singleton::getInstance();
    cout << s1.get() << " " << s2.get() << endl;
}