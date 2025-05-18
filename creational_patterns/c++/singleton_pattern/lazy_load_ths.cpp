#include<iostream>
using namespace std;

class Singleton{
private:
    Singleton() = default;
public:
    Singleton(const Singleton&) = delete;
    Singleton(const Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(const Singleton&&) = delete;

    static Singleton& getInstance(){
        static Singleton instance;
        return instance;
    }
};

int main(){
    Singleton& s1 = Singleton::getInstance();
    Singleton& s2 = Singleton::getInstance();

    cout << &s1 << " " << &s2 << endl;
}