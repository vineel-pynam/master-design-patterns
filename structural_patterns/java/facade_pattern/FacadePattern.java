package structural_patterns.java.facade_pattern;

// Home Automation
// Simple command HappyHome starts all essentials things that we daily.
// Simple command SadHome stops that are running.

class Lights{
    public void On(){
        System.out.println("Switching on all the lights");
    }

    public void Off(){
        System.out.println("Switching off all the lights");
    }
}

class Windows{
    public void Open(){
        System.out.println("Opening Windows Partially for better lighting and Air flow.");
    }

    public void Close(){
        System.out.println("Closing all the windows.");
    }
}

class Door{
    public void Open(){
        System.out.println("Opening the door.");
    }

    public void Close(){
        System.out.println("Closing the door");
    }
}

class Fans{
    public void On(){
        System.out.println("Switching on the Fans with medium speed");
    }

    public void Off(){
        System.out.println("Switching off the fans");
    }
}

class AirPurifier{
    public void On(){
        System.out.println("Switching on the AirPurifier");
    }

    public void Off(){
        System.out.println("Switching off the AirPurifier");
    }
}

class AirConditiong{
    public void On(){
        System.out.println("Switching on the AC");
    }

    public void Off(){
        System.out.println("Switching off the AC");
    }
}

class TV{
    public void On(){
        System.out.println("Switching on the TV and playing pleasant songs");
    }

    public void Off(){
        System.out.println("Switching off the TV");
    }
}

class Alexa{
    private Lights lights;
    private Door door;
    private Windows windows;
    private AirConditiong ac;
    private Fans fans = new Fans();
    private AirPurifier airPurifier;
    private TV tv;

    Alexa(){
        this.lights = new Lights();
        this.door = new Door();
        this.windows = new Windows();
        this.ac = new AirConditiong();
        this.fans = new Fans();
        this.airPurifier = new AirPurifier();
        this.tv = new TV();
    }

    public void happyHome(){
        this.door.Open();
        this.lights.On();
        this.ac.On();
        this.fans.On();
        this.windows.Open();
        this.airPurifier.On();
        this.tv.On();
    }

    public void sadHome(){
        this.lights.Off();
        this.ac.Off();
        this.fans.Off();
        this.windows.Close();
        this.airPurifier.Off();
        this.tv.Off();
        this.door.Close();
    }
}

// Client
class FacadePattern {
    public static void main(String[] args) {
        Alexa alexa = new Alexa();
        alexa.happyHome();
        System.out.println();
        alexa.sadHome();
    }
}
