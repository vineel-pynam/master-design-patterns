#include<iostream>
#include<string>
using namespace std;

class ProductPrototype{
public:
    virtual ProductPrototype* clone() = 0;
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

    ProductPrototype* clone() override{
        Product* newProduct = new Product();
        newProduct->setName(this->name);
        newProduct->setDescription(this->description);
        newProduct->setPrice(this->price);
        newProduct->setQuantity(this->quantity);
        return newProduct;
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
    Product* product = new Product();
    product->setName("FiveStar");
    product->setDescription("FiveStar Chocolate");
    product->setPrice(10);
    product->setQuantity(5);
    product->display();

    ProductPrototype* cloned = product->clone();
    cloned->display();

    Product* clonedProduct = dynamic_cast<Product*>(cloned);
    clonedProduct->setName("DairyMilk");
    clonedProduct->display();
}