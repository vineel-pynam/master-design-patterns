#include<iostream>
using namespace std;

class IPayment{
public:
    virtual void pay() = 0;
    virtual ~IPayment() = default;
};

class CreditCardPayment : public IPayment {
public:
    void pay() override {
        cout << "Paid via Credit Card" << endl;
    }
};

class DebitCardPayment : public IPayment {
public:
    void pay() override {
        cout << "Paid via Debit Card" << endl;
    }
};

class NetBankingPayment : public IPayment {
public:
    void pay() override {
        cout << "Paid via Net Banking" << endl;
    }
};

class PaymentProcessor {
private:
    IPayment* payment;
public:

    void setPaymentStrategy(IPayment* payment){
        this->payment = payment;
    }

    void processPayment(){
        if( payment ){
            payment->pay();
        }else{
            cout << "Please select payment Method" << endl;
        }
    }

};

int main(){
    PaymentProcessor paymentProcessor;
    paymentProcessor.setPaymentStrategy(new CreditCardPayment());
    paymentProcessor.processPayment();
}