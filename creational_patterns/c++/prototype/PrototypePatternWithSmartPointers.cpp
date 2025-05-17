#include<iostream>
#include<string>
#include<memory>
using namespace std;

class ProductPrototype{
public:
    virtual unique_ptr<ProductPrototype> clone() = 0;
    virtual void display() = 0;
    virtual ~ProductPrototype() = default;
};

class Product: public ProductPrototype {
private:
    string name;
    string description;
    int price;
    int quantity;
public:
    Product() = default;

    void setName(const string& name){
        this->name = name;
    }

    string getName(){
        return this->name;
    }

    void setDescription(const string& description){
        this->description = description;
    }

    string getDescription(){
        return this->description;
    }

    void setPrice(const int& price){
        this->price = price;
    }

    int getPrice(){
        return this->price;
    }

    void setQuantity(const int& quantity){
        this->quantity = quantity;
    }

    int getQuantity(){
        return this->quantity;
    }

    unique_ptr<ProductPrototype> clone() override{
        return make_unique<Product>(*this);
    }

    void display() override {
        cout << "[NAME]: " << name << endl;
        cout << "[DESCRIPTION]: " << description << endl;
        cout << "[PRICE]: " << price << endl;
        cout << "[QUANTITY]: " << quantity << endl;
        cout << endl;
    }

};

int main(){
    unique_ptr<Product> product = make_unique<Product>();
    product->setName("FiveStar");
    product->setDescription("FiveStar Chocolate");
    product->setPrice(10);
    product->setQuantity(5);
    product->display();

    unique_ptr<ProductPrototype> cloned = product->clone();
    cloned->display();

    Product* clonedProduct = dynamic_cast<Product*>(cloned.get());
    clonedProduct->setName("DairyMilk");
    clonedProduct->display();
}