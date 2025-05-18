#include<iostream>
#include<mutex>
using namespace std;

class Singleton{
private:
    static inline Singleton* instance = nullptr;
    static inline once_flag init_flag;

    Singleton() = default;
public:
    Singleton(const Singleton&) = delete;
    Singleton(Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(Singleton&&) = delete;

    static Singleton* getInstance(){
        call_once(init_flag, [](){
            instance = new Singleton();
        });

        return instance;
    }
};

int main(){
    Singleton* s1 = Singleton::getInstance();
    Singleton* s2 = Singleton::getInstance();
    cout << s1 << " " << s2 << endl;
}