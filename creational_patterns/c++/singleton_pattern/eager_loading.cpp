#include<iostream>
using namespace std;

class Singleton {
private:
    Singleton() = default;

public:
    Singleton(const Singleton&) = delete;
    Singleton(const Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(const Singleton&&) = delete;

    static Singleton& getInstance(){
        return instance;
    }
    
    static Singleton instance;   
};

Singleton Singleton::instance;

int main(){
    Singleton& s1 = Singleton::getInstance();
    Singleton& s2 = Singleton::getInstance();
    cout << &s1 << " " << &s2 << endl;
}