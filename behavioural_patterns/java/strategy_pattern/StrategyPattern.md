# Strategy Pattern - Payment System

## Overview
The **Strategy Pattern** is a behavioral design pattern that allows selecting a specific algorithm (or strategy) at runtime. This project demonstrates the **Strategy Pattern** by implementing a payment system where users can select different payment modes (Credit Card, Debit Card, UPI, Net Banking).

## Project Structure
```
behavioural_patterns/java/strategy_pattern/
│── StrategyPattern.java
```

## How It Works
1. The `PaymentType` enum defines different payment methods.
2. The `IPayment` interface declares the `pay()` method.
3. Concrete payment classes (`CreditCardPayment`, `DebitCardPayment`, `UpiPayment`, `NetBankingPayment`) implement the `IPayment` interface.
4. `PaymentStrategy` class dynamically sets the payment method and processes the payment.
5. The `StrategyPattern` (client class) demonstrates payment selection and execution.

## Code Explanation
### **1. PaymentType Enum**
Defines different payment options:
```java
enum PaymentType{
    CREDIT_CARD,
    UPI,
    DEBIT_CARD,
    NET_BANKING
}
```

### **2. Payment Interface (`IPayment`)**
Declares the `pay()` method:
```java
interface IPayment{
    void pay();
}
```

### **3. Concrete Payment Classes**
Each class implements `IPayment` and provides a payment-specific implementation:
```java
class CreditCardPayment implements IPayment{
    @Override
    public void pay(){
        System.out.println("[PAYMENT_MODE]: Credit Card");
    }
}
```
(Similar implementations exist for `DebitCardPayment`, `UpiPayment`, and `NetBankingPayment`.)

### **4. Payment Strategy Class**
- Stores the selected payment strategy.
- Allows setting a strategy at runtime.
- Executes the selected payment method.
```java
class PaymentStrategy{
    private IPayment payment;
    
    public void setPaymentStrategy(PaymentType paymentType){
        if(paymentType == PaymentType.CREDIT_CARD){
            payment = new CreditCardPayment();
        }else if(paymentType == PaymentType.UPI){
            payment = new UpiPayment();
        }else if(paymentType == PaymentType.DEBIT_CARD){
            payment = new DebitCardPayment();
        }else if(paymentType == PaymentType.NET_BANKING){
            payment = new NetBankingPayment();
        }
    }

    public void pay(Double amount){
        if(payment == null){
            throw new RuntimeException("Payment Strategy Is Not Set");
        }
        payment.pay();
        System.out.println("[AMOUNT_PAID]: " + amount);
        payment = null; // Resetting payment
    }
}
```

### **5. Client Code (`StrategyPattern`)**
Demonstrates selecting and executing payment strategies:
```java
class StrategyPattern {
    public static void main(String[] args) {
        PaymentStrategy paymentStrategy = new PaymentStrategy();
        
        paymentStrategy.setPaymentStrategy(PaymentType.CREDIT_CARD);
        paymentStrategy.pay(500.50);
        
        paymentStrategy.setPaymentStrategy(PaymentType.UPI);
        paymentStrategy.pay(20000.00);
    }
}
```

## Compilation & Execution
```sh
./java.sh StrategyPattern.java
```

## Expected Output
```
[PAYMENT_MODE]: Credit Card
[AMOUNT_PAID]: 500.5

[PAYMENT_MODE]: UPI
[AMOUNT_PAID]: 20000.0
```

## Applications
- Payment gateways to select between different payment modes.
- Sorting algorithms that allow choosing different sorting strategies.
- Logging frameworks to dynamically change log formats.
- Compression tools to switch between different compression algorithms.

## Summary
- The **Strategy Pattern** allows dynamically selecting an algorithm at runtime.
- We implemented different payment modes using **separate strategy classes**.
- The `PaymentStrategy` class acts as a **context**, managing different strategies.
- This design improves **flexibility** and **maintainability** by **separating behaviors** into different classes.

This approach ensures **scalability** as new payment methods can be added without modifying existing code.

