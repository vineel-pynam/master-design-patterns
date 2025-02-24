package creational_patterns.java.singleton;

// Singleton DBConnection.
// No matter how many times to initialize, object will only be created once. (In jVM)
// same object is reused through the application.

// Object is created only when getInstance() is called (Lazy Initialization).
class DBConnection{
 
    private static class ConnectToDB{
        private static final DBConnection INSTANCE = new DBConnection();
    }
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return ConnectToDB.INSTANCE;
    }
}

// Client
class BillPughInitialization {
    public static void main(String[] args) {
        // Printing Multiple times to check if object created is same
        System.out.println("[DBConnection]: " + DBConnection.getInstance());
        System.out.println("[DBConnection]: " + DBConnection.getInstance());
    }
}
