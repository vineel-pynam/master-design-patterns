package creational_patterns.java.prototype;

// Chocolate Prototype Interface
interface ChocolatePrototype{
    public ChocolatePrototype clone();
    public void display();
}

// Concrete Implementations of Prototype Pattern
class FiveStarChocolate implements ChocolatePrototype{
    private String name;
    private String tagline;

    FiveStarChocolate(String name, String tagline){
        this.name = name;
        this.tagline = tagline;
    }

    @Override
    public ChocolatePrototype clone(){
        return new FiveStarChocolate(name, tagline);
    }

    @Override
    public void display(){
        System.out.println("Name: " + this.name + ", Tagline: " + tagline );
    }
}

class DairyMilkChocolate implements ChocolatePrototype{
    private String name;
    private String tagline;

    DairyMilkChocolate(String name, String tagline){
        this.name = name;
        this.tagline = tagline;
    }

    @Override
    public ChocolatePrototype clone(){
        return new DairyMilkChocolate(name, tagline);
    }

    @Override
    public void display(){
        System.out.println("Name: " + this.name + ", Tagline: " + tagline );
    }
}

// Client
class PrototypePattern {
    public static void main(String args[]){
        ChocolatePrototype fiveStar = new FiveStarChocolate("FiveStar", "Nah, I'm fine here.");
        ChocolatePrototype dairyMilk = new DairyMilkChocolate("DairyMilk", "How far will you go for love?");

        System.out.println("Original: ");
        fiveStar.display();
        dairyMilk.display();

        System.out.println();

        ChocolatePrototype fiveStarClone = fiveStar.clone();
        ChocolatePrototype dairyMilkClone = dairyMilk.clone();
        System.out.println("Cloned:");
        fiveStarClone.display();
        dairyMilkClone.display();
    }
}
