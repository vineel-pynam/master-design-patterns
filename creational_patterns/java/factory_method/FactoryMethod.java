package creational_patterns.java.factory_method;

// Log types as enums
enum LogType{
    ERROR, DEBUG, INFO
}

// ILogger interface for abstaction
interface ILogger{
    void log(String message);
}

// Concrete logger classes
class ErrorLogger implements ILogger{
    @Override
    public void log(String message){
        System.out.println("[ERROR]: " + message);
    }
}

class DebugLogger implements ILogger{
    @Override
    public void log(String message){
        System.out.println("[DEBUG]: " + message);
    }
}

class InfoLogger implements ILogger{
    @Override
    public void log(String message){
        System.out.println("[INFO]: " + message);
    }
}

// Logger factory - creates a logger object based on log type.
// In simple-factory pattern, Factory class used to take care of creation of ILogger Objects.
// Will be creating ILoggerFactory interface/abstract-class and concrete class that implement the interface, will take care of creation process.
// Will be delegating creation process to specific factories inorder to achieve OCP.
interface ILoggerFactory{
    public ILogger createLogger();
}

class InfoLoggerFactory implements ILoggerFactory {
    @Override
    public ILogger createLogger(){
        return new InfoLogger();
    }
}

class DebugLoggerFactory implements ILoggerFactory {
    @Override
    public ILogger createLogger(){
        return new DebugLogger();
    }
}

class ErrorLoggerFactory implements ILoggerFactory {
    @Override
    public ILogger createLogger(){
        return new ErrorLogger();
    }
}

// Client
class FactoryMethod{
    public static void main(String args[]){
        // Specific Loggers
        ILoggerFactory loggerFactory = new InfoLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        
        loggerFactory = new DebugLoggerFactory();
        logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        loggerFactory = new ErrorLoggerFactory();
        logger = loggerFactory.createLogger();
        logger.log("Hola..!");
    }
}