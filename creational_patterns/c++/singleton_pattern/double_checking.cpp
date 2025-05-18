#include<iostream>
#include<mutex>
using namespace std;

class Singleton{
private:
    Singleton() = default;
    static inline Singleton* instance = nullptr;
    static inline mutex mtx;
public:
    Singleton(const Singleton&) = delete;
    Singleton(Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(Singleton&&) = delete;

    static Singleton* getInstance(){
        if( !instance ){
            lock_guard<mutex> lock(mtx);
            if( !instance ){
                instance = new Singleton();
            }
        }
        return instance;
    }
};

// if < c++17
// Singleton* Singleton::instance = nullptr;
// mutex Singleton::mtx;

int main(){
    Singleton* s1 = Singleton::getInstance();
    Singleton* s2 = Singleton::getInstance();
    cout << s1 << " " << s2 << endl;
}