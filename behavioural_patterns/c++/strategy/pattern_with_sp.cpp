#include<iostream>
#include<memory>
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
    unique_ptr<IPayment> payment;
public:

    void setPaymentStrategy(unique_ptr<IPayment> payment){
        this->payment = move(payment);
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
    paymentProcessor.setPaymentStrategy(make_unique<CreditCardPayment>());
    paymentProcessor.processPayment();

    paymentProcessor.setPaymentStrategy(make_unique<NetBankingPayment>());
    paymentProcessor.processPayment();
}