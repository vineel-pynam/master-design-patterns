#include<iostream>
#include<functional>
#include<unordered_map>
using namespace std;

class Button{
public:
    virtual void createButton() = 0;
    virtual ~Button() = default;
};

class WindowsButton: public Button {
public:
    void createButton() override{
        cout << "Creating Windows Button" << endl;
    }
};

class MacButton: public Button {
public:
    void createButton() override{
        cout << "Creating Mac Button" << endl;
    }
};


class TextBox{
public:
    virtual void createTextBox() = 0;
    virtual ~TextBox() = default;
};

class WindowsTextBox: public TextBox {
public:
    void createTextBox() override{
        cout << "Creating Windows TextBox" << endl;
    }
};

class MacTextBox: public TextBox {
public:
    void createTextBox() override{
        cout << "Creating Mac TextBox" << endl;
    }
};


class OS{
public:
    virtual void createButton() = 0;
    virtual void createTextBox() = 0;
    virtual ~OS() = default;
};

class Windows : public OS{
private:
    Button* button = new WindowsButton();
    TextBox* textBox = new WindowsTextBox();
public:
    void createButton() override{
        button->createButton();
    }

    void createTextBox() override {
        textBox->createTextBox();
    }
};

class Mac : public OS{
private:
    Button* button = new MacButton();
    TextBox* textBox = new MacTextBox();
public:
    void createButton() override{
        button->createButton();
    }

    void createTextBox() override {
        textBox->createTextBox();
    }
};

enum class OsType{
    WINDOWS, MAC
};

class OsFactory {
private:
    using Creator = function<OS*()>;
    unordered_map<OsType, Creator> mp;
public:
    OsFactory(){
        registerOS(OsType::WINDOWS, [](){ return new Windows();});
        registerOS(OsType::MAC, [](){ return new Mac();});
    }

    void registerOS(OsType osType, Creator creator){
        mp[osType] = creator;
    }

    OS* getOS(OsType osType){
        auto it = mp.find(osType);
        if( it != mp.end() ){
            return it->second();
        }
        return nullptr;
    }
};


int main(){
    OsFactory of;
    OS* os = of.getOS(OsType::WINDOWS);
    os->createButton();
    os->createTextBox();

    cout << endl;
    
    os = of.getOS(OsType::MAC);
    os->createButton();
    os->createTextBox();
}