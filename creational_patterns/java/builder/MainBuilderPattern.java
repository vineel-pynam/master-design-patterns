package creational_patterns.java.builder;

// This pattern is used when construction process is same but complex.
// Let's say we are building a car.
// Companies build their cars with different features(based on the price).
// But in the core, construction process will be almost same with different features.
// Here we are extacting out the complex construction process.

// Car class
class Car{
    private String name;
    private String body;
    private String tyres;
    private String steering;
    private String electricals;
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBody(){
        return this.body;
    }

    public void setTyres(String tyres){
        this.tyres = tyres;
    }

    public String getTyres(){
        return this.tyres;
    }

    public void setElectricals(String electricals){
        this.electricals = electricals;
    }

    public String getElectricals(){
        return this.electricals;
    }

    public void setSteering(String steering){
        this.steering = steering;
    }

    public String getSteering(){
        return this.steering;
    }

    public void display(){
        System.out.println(
            "[Body]: " + this.body + "\n" +
            "[Name]: " + this.name + "\n" +
            "[Tyres]: " + this.tyres + "\n" +
            "[Steering]: " + this.steering + "\n" +
            "[Electricals]: " + this.electricals + "\n"
        );
        System.out.println();
    }

}

// Car Builder Abstract Class
abstract class CarBuilder{
    protected Car car = new Car();

    public abstract CarBuilder makeBody();
    public abstract CarBuilder addName();
    public abstract CarBuilder addSteering();
    public abstract CarBuilder addTypres();
    public abstract CarBuilder addElectricals();
    public abstract Car build();
}

// Concrete classes extending Car Builder
class SkodaCar extends CarBuilder{

    @Override
    public CarBuilder makeBody() {
        car.setBody("Making body High Quality Steel");
        return this;
    }

    @Override
    public CarBuilder addName() {
        car.setName("Skoda");
        return this;
    }

    @Override
    public CarBuilder addSteering() {
        car.setSteering("Adding Steering with Skoda Branding");
        return this;
    }

    @Override
    public CarBuilder addTypres() {
        car.setTyres("Adding Alloy Tyres with Skoda branding");
        return this;
    }

    @Override
    public CarBuilder addElectricals() {
        car.setElectricals("Adding all required electricals from Bosch");
        return this;
    }

    @Override
    public Car build(){
        return car;
    }
}

// Concrete classes extending Car Builder
class VWCar extends CarBuilder{

    @Override
    public CarBuilder makeBody() {
        car.setBody("Making body High Quality Steel");
        return this;
    }

    @Override
    public CarBuilder addName() {
        car.setName("VW");
        return this;
    }

    @Override
    public CarBuilder addSteering() {
        car.setSteering("Adding Steering with VW Branding");
        return this;
    }

    @Override
    public CarBuilder addTypres() {
        car.setTyres("Adding Alloy Tyres with VW branding");
        return this;
    }

    @Override
    public CarBuilder addElectricals() {
        car.setElectricals("Adding all required electricals from Bosch");
        return this;
    }

    @Override
    public Car build(){
        return car;
    }
}

// Car Director - takes care of building process.
class CarDirector{
    public static Car makeCar(CarBuilder carBuilder){
        return carBuilder.makeBody()
                    .addName()
                    .addElectricals()
                    .addTypres()
                    .addSteering()
                    .build();
    }
}

// Client
class MainBuilderPattern {
    public static void main(String[] args) {
        // Skoda Car
        CarBuilder skoda = new SkodaCar();
        Car car = CarDirector.makeCar(skoda);
        car.display();

        // VW Car
        CarBuilder vw = new SkodaCar();
        car = CarDirector.makeCar(vw);
        car.display();
    }
}
