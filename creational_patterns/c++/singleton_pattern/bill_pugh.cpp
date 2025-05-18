#include <iostream>
using namespace std;

class Singleton {
private:

    // Private nested helper class
    class Helper {
    public:
        static Singleton instance;
    };

    Singleton() = default;


public:
    // Delete copy/move constructors and assignment operators
    Singleton(const Singleton&) = delete;
    Singleton(Singleton&&) = delete;
    Singleton& operator=(const Singleton&) = delete;
    Singleton& operator=(Singleton&&) = delete;

    static Singleton& getInstance() {
        return Helper::instance;
    }
};

Singleton Singleton::Helper::instance;

int main() {
    Singleton& s1 = Singleton::getInstance();
    Singleton& s2 = Singleton::getInstance();
    cout << &s1 << " " << &s2 << endl;
}
