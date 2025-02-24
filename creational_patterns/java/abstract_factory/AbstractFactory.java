package creational_patterns.java.abstract_factory;
enum OS_TYPE{
    WINDOWS, MAC
}

// Button Interface
interface IButton{
    public void pressButton();
}

// Concrete Implementations of Button Interface
class WindowsButton implements IButton{
    @Override
    public void pressButton(){
        System.out.println("Pressed Windows Button");
    }
}

class MacButton implements IButton{
    @Override
    public void pressButton(){
        System.out.println("Pressed Mac Button");
    }
}

// TextBox Interface
interface ITextBox{
    public void enterText();
}

// Concrete Implementations of TextBox Interface
class WindowsTextBox implements ITextBox{
    @Override
    public void enterText(){
        System.out.println("Entered Text in Windows TextBox");
    }
}

class MacTextBox implements ITextBox{
    @Override
    public void enterText(){
        System.out.println("Entered Text in Mac TextBox");
    }
}

// Operating System Interface
interface IOperatingSystem{
    public void enterText();
    public void pressButton(); 
}

// Concrete Classes implementing Operating System Interface
class WindowsOS implements IOperatingSystem{
    @Override
    public void pressButton(){
        IButton button = new WindowsButton();
        button.pressButton();
    }

    @Override
    public void enterText(){
        ITextBox textBox = new WindowsTextBox();
        textBox.enterText();
    }
}

class MacOS implements IOperatingSystem{
    @Override
    public void pressButton(){
        IButton button = new MacButton();
        button.pressButton();
    }

    @Override
    public void enterText(){
        ITextBox textBox = new MacTextBox();
        textBox.enterText();
    }
}

class OSFactory{
    public IOperatingSystem getOS(OS_TYPE os){
        if( os == OS_TYPE.WINDOWS ){
            return new WindowsOS();
        }else if( os == OS_TYPE.MAC ){
            return new MacOS();
        }

        return new WindowsOS();
    }
}


// Client
class AbstractFactory {
    public static void main(String args[]){
        OSFactory osFactory = new OSFactory();
        
        // Mac Example
        IOperatingSystem os = osFactory.getOS(OS_TYPE.MAC);
        os.pressButton();
        os.enterText();

        // Windows Example
        os = osFactory.getOS(OS_TYPE.WINDOWS);
        os.pressButton();
        os.enterText();
    }
}
