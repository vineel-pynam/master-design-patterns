package behavioural_patterns.java.strategy_pattern;

// Supported Payment Types
enum PaymentType{
    CREDIT_CARD,
    UPI,
    DEBIT_CARD,
    NET_BANKING
}

// Payment Interface
interface IPayment{
    void pay();
}

// Concrete Classes Implementing IPayment
class CreditCardPayment implements IPayment{
    @Override
    public void pay(){
        System.out.println("[PAYMENT_MODE]: Credit Card");
    }
}

class DebitCardPayment implements IPayment{
    @Override
    public void pay(){
        System.out.println("[PAYMENT_MODE]: Debit Card");
    }
}

class UpiPayment implements IPayment{
    @Override
    public void pay(){
        System.out.println("[PAYMENT_MODE]: UPI");
    }
}

class NetBankingPayment implements IPayment{
    @Override
    public void pay(){
        System.out.println("[PAYMENT_MODE]: Net Banking");
    }
}

// Payment Strategy
class PaymentStrategy{
    private IPayment payment;

    PaymentStrategy(){
        this.payment = null;
    }

    public void setPaymentStrategy(PaymentType paymentType){
        if( paymentType == PaymentType.CREDIT_CARD ){
            payment = new CreditCardPayment();
        }else if( paymentType == PaymentType.UPI ){
            payment = new UpiPayment();
        }else if( paymentType == PaymentType.DEBIT_CARD ){
            payment = new DebitCardPayment();
        }else if( paymentType == PaymentType.NET_BANKING ){
            payment = new NetBankingPayment();
        }
    }

    public void pay(Double amount){
        if( payment == null ){
            throw new RuntimeException("Payment Strategy Is Not Set");
        }

        payment.pay();
        System.out.println("[AMOUNT_PAID]: "+ amount);
        System.out.println();

        // Resetting payment to null
        payment = null;
    }
}

// Client
class StrategyPattern {
    public static void main(String[] args) {
        // Selecting Credit Card Payment
        PaymentStrategy paymentStrategy = new PaymentStrategy();
        paymentStrategy.setPaymentStrategy(PaymentType.CREDIT_CARD);
        paymentStrategy.pay(500.50);

        // Selecting UPI Payment
        paymentStrategy.setPaymentStrategy(PaymentType.UPI);
        paymentStrategy.pay(20000.00);
    }    
}
