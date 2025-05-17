#include<iostream>
using namespace std;

class UserBuilder;

class User{
private:   
    string name;
    int age;
    string email;
    string description;
    User(string name, int age, string email, string description) 
        : name(move(name)), age(age), email(move(email)), description(move(description)){}
public:
    void display(){
        cout << "[NAME]: " << name << endl;
        cout << "[EMAIL]: " << email << endl;
        cout << "[AGE]: " << age << endl;
        cout << "[DESCRIPTION]: " << description << endl;
    }

    friend class UserBuilder;
};

class UserBuilder{
private: 
    string name;
    int age;
    string email;
    string description;
public:

    UserBuilder(){}

    UserBuilder& setName(const string& name){
        this->name = name;
        return *this;
    }

    UserBuilder& setAge(const int& age){
        this->age = age;
        return *this;
    }

    UserBuilder& setDescription(const string& description){
        this->description = description;
        return *this;
    }

    UserBuilder& setEmail(const string& email){
        this->email = email;
        return *this;
    }

    User build(){
        return User(name, age, email, description);
    }
};

int main(){
   User user = UserBuilder().setAge(24).setName("Vineel").setEmail("vineel@gmail.com").setDescription("Hello, Vineel").build();
   user.display();
}