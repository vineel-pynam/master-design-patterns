package creational_patterns.java.singleton;

// Singleton DBConnection.
// No matter how many times to initialize, object will only be created once. (In jVM)
// same object is reused through the application.

// This is not Thread Safe
class DBConnection{
    private static DBConnection INSTANCE;

    private DBConnection(){}

    public static DBConnection getInstance(){
        if( INSTANCE == null ){
            INSTANCE = new DBConnection();
        }

        return INSTANCE;
    }
}

// Client
class LazyInitialization {
    public static void main(String[] args) {
         // Printing Multiple times to check if object created is same
         System.out.println("[DBConnection]: " + DBConnection.getInstance());
         System.out.println("[DBConnection]: " + DBConnection.getInstance());
    }
}
