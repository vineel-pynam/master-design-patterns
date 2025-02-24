package creational_patterns.java.singleton;

// Singleton DBConnection.
// No matter how many times to initialize, object will only be created once. (In jVM)
// same object is reused through the application.

// Instance is created at class loading time.
class DBConnection{
    private static final DBConnection INSTANCE = new DBConnection();
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return INSTANCE;
    }
}

// Client
class EagerInitialization {
    public static void main(String[] args) {
        // Printing Multiple times to check if object created is same
        System.out.println("[DBConnection]: " + DBConnection.getInstance());
        System.out.println("[DBConnection]: " + DBConnection.getInstance());
    }
}
