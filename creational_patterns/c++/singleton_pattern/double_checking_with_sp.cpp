#include<iostream>
#include<memory>
#include<mutex>
using namespace std;

class Singleton{
private:
    Singleton() = default;
    static inline unique_ptr<Singleton> instance;
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
                instance.reset(new Singleton());
            }
        }
        return instance.get();
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