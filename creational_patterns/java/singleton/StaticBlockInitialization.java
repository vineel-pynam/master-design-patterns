package creational_patterns.java.singleton;

// Singleton DBConnection.
// No matter how many times to initialize, object will only be created once. (In jVM)
// same object is reused through the application.

// Instance is created at class loading time.
class DBConnection{
    private static DBConnection INSTANCE;

    static{
        try{
            INSTANCE = new DBConnection();
        }catch(Exception e){
            throw new RuntimeException("UNABLE_TO_INITIALIZE_CLASS");
        }
    }
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return INSTANCE;
    }
}

// Client
public class StaticBlockInitialization {
    public static void main(String[] args) {
         // Printing Multiple times to check if object created is same
         System.out.println("[DBConnection]: " + DBConnection.getInstance());
         System.out.println("[DBConnection]: " + DBConnection.getInstance());
    }
}
