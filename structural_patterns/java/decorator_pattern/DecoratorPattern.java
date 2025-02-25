package structural_patterns.java.decorator_pattern;

// Coffee Interface
interface Coffee{
    String getDescription();
    Double getCost();
}

// Concrete Implementations of Coffee
class FilterCoffee implements Coffee{

    @Override
    public String getDescription(){
        return "Filter Coffee";
    }

    @Override
    public Double getCost(){
        return 20.00;
    }
}

class BlackCoffee implements Coffee{

    @Override
    public String getDescription(){
        return "Black Coffee";
    }

    @Override
    public Double getCost(){
        return 30.00;
    }
}

// Coffee Decorators
abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;
    CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator{
    MilkDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + " With Added Milk";
    }

    @Override
    public Double getCost(){
        return coffee.getCost() + 10.00;
    }
}

class SugarDecorator extends CoffeeDecorator{
    SugarDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + " With Added Sugar";
    }

    @Override
    public Double getCost(){
        return coffee.getCost() + 5.00;
    }
}

class LemonDecorator extends CoffeeDecorator{
    LemonDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + " With Added Lemon";
    }

    @Override
    public Double getCost(){
        return coffee.getCost() + 7.00;
    }
}

// Client
class DecoratorPattern {
    public static void main(String[] args) {

        // Filter Coffee
        Coffee filterCoffee = new FilterCoffee();
        filterCoffee = new MilkDecorator(filterCoffee);
        filterCoffee = new SugarDecorator(filterCoffee);
        
        // Black Coffee
        Coffee blackCoffee = new BlackCoffee();
        blackCoffee = new LemonDecorator(blackCoffee);
        blackCoffee = new SugarDecorator(blackCoffee);

        System.out.println("[ITEM]: " + filterCoffee.getDescription());
        System.out.println("[COST]: "+ filterCoffee.getCost());

        System.out.println();

        System.out.println("[ITEM]: " + blackCoffee.getDescription());
        System.out.println("[COST]: "+ blackCoffee.getCost());
    }
}
