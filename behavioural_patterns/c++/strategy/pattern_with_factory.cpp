#include<iostream>
#include<memory>
#include<functional>
#include<unordered_map>
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

enum class PaymentType{
    CREDIT_CARD,
    DEBIT_CARD,
    NET_BANKING
};

class PaymentFactory {
private: 
    using Creator = function<IPayment*()>;
    unordered_map<PaymentType, Creator> mp;
public:
    PaymentFactory() {
        registerPayment(PaymentType::CREDIT_CARD, [](){return new CreditCardPayment();});
        registerPayment(PaymentType::DEBIT_CARD, [](){return new DebitCardPayment();});
        registerPayment(PaymentType::NET_BANKING, [](){return new NetBankingPayment();});
    }

    void registerPayment(PaymentType paymentType, Creator creator){
        mp[paymentType] = creator;
    }

    IPayment* getPaymentMethod(PaymentType paymentType){
        auto it = mp.find(paymentType);
        if( it == mp.end() ){
            throw runtime_error("No Payment Type Present");
        }
        return it->second();
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
    PaymentFactory paymentFactory;

    PaymentProcessor paymentProcessor;
    paymentProcessor.setPaymentStrategy(paymentFactory.getPaymentMethod(PaymentType::CREDIT_CARD));
    paymentProcessor.processPayment();

    paymentProcessor.setPaymentStrategy(paymentFactory.getPaymentMethod(PaymentType::DEBIT_CARD));
    paymentProcessor.processPayment();
}