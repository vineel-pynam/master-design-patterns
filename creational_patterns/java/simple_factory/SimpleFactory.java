package creational_patterns.java.simple_factory;

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
class LoggerFactory{

    public ILogger createLogger(){
        return new InfoLogger();
    }

    public ILogger createLogger(LogType logType){
        switch (logType){
            case INFO:
                return new InfoLogger();
            case DEBUG:
                return new DebugLogger();
            case ERROR:
                return new ErrorLogger();
            default:
                return new InfoLogger();
        }
    }
}

// Client
class SimpleFactory{
    public static void main(String args[]){
        LoggerFactory loggerFactory = new LoggerFactory();

        // InfoLogger as default
        ILogger logger = loggerFactory.createLogger();
        logger.log("Hola..!");

        // Specific Loggers
        logger = loggerFactory.createLogger(LogType.DEBUG);
        logger.log("Hola..!");

        logger = loggerFactory.createLogger(LogType.ERROR);
        logger.log("Hola..!");
    }
}