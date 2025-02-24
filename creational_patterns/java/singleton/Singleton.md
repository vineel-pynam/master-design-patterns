# Singleton Design Pattern in Java

The **Singleton Pattern** ensures that a class has only **one instance** and provides a global point of access to that instance. Below are different implementations of the Singleton pattern in Java.

---

## **1. Eager Initialization Singleton**

### **Code Overview:**
- The instance of `DBConnection` is created **at the time of class loading**.
- This is the simplest and safest way to create a Singleton instance.
- However, the object is **always created**, even if it is never used, which can waste resources.

### **Implementation:**
```java
class DBConnection{
    private static final DBConnection INSTANCE = new DBConnection();
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return INSTANCE;
    }
}
```

### **Pros & Cons:**
✔ Thread-safe without synchronization.
✔ Simple to implement.
❌ Instance is created even if it is not required (No Lazy Initialization).

---

## **2. Static Block Initialization Singleton**

### **Code Overview:**
- Similar to **Eager Initialization**, but allows **exception handling** in the static block.
- The instance is created **when the class is loaded**.

### **Implementation:**
```java
class DBConnection{
    private static DBConnection INSTANCE;

    static {
        try {
            INSTANCE = new DBConnection();
        } catch (Exception e) {
            throw new RuntimeException("UNABLE_TO_INITIALIZE_CLASS");
        }
    }
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return INSTANCE;
    }
}
```

### **Pros & Cons:**
✔ Thread-safe without synchronization.
✔ Allows exception handling.
❌ No Lazy Initialization (Instance is created at class loading).

---

## **3. Lazy Initialization Singleton (Not Thread-Safe)**

### **Code Overview:**
- The instance is **created only when needed** (Lazy Initialization).
- **Not thread-safe**, which means multiple threads can create multiple instances.

### **Implementation:**
```java
class DBConnection{
    private static DBConnection INSTANCE;

    private DBConnection(){}

    public static DBConnection getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DBConnection();
        }
        return INSTANCE;
    }
}
```

### **Pros & Cons:**
✔ Lazy Initialization (Instance is created only when needed).
❌ **Not thread-safe** (Multiple threads may create multiple instances).

---

## **4. Bill Pugh Singleton (Best Lazy Initialization Approach)**

### **Code Overview:**
- Uses a **static nested class** to hold the Singleton instance.
- The instance is **created only when `getInstance()` is called**.
- Thread-safe **without synchronization**.

### **Implementation:**
```java
class DBConnection{
    private static class ConnectToDB{
        private static final DBConnection INSTANCE = new DBConnection();
    }
    
    private DBConnection(){}

    public static DBConnection getInstance(){
        return ConnectToDB.INSTANCE;
    }
}
```

### **Pros & Cons:**
✔ Thread-safe.
✔ Lazy Initialization (Created only when needed).
✔ No synchronization overhead.

---

## **5. Double-Checked Locking Singleton (Thread-Safe)**

### **Code Overview:**
- Uses **`volatile`** to ensure visibility across threads.
- Uses **double-checking** inside a `synchronized` block to improve performance.
- Best suited when Singleton is accessed by multiple threads frequently.

### **Implementation:**
```java
class DBConnection {
    private static volatile DBConnection INSTANCE;

    private DBConnection() {}

    public static DBConnection getInstance() {
        if (INSTANCE == null) {
            synchronized (DBConnection.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBConnection();
                }
            }
        }
        return INSTANCE;
    }
}
```

### **Pros & Cons:**
✔ Thread-safe.
✔ Lazy Initialization.
✔ Efficient (Avoids unnecessary synchronization).

---

# **Comparison of Singleton Approaches**

| Implementation Type                | Thread-Safe | Lazy Initialization | Performance |
|-----------------------------------|------------|------------------|-------------|
| **Eager Initialization**          | ✅ Yes      | ❌ No             | ✅ Fast      |
| **Static Block Initialization**   | ✅ Yes      | ❌ No             | ✅ Fast      |
| **Lazy Initialization**           | ❌ No       | ✅ Yes            | ❌ Slow (Multiple instances in multithreading) |
| **Bill Pugh Singleton**           | ✅ Yes      | ✅ Yes            | ✅ Best Performance |
| **Double-Checked Locking**        | ✅ Yes      | ✅ Yes            | ✅ Efficient |

---

## **Conclusion**
- **Use Eager Initialization** if **memory is not a concern** and the instance will always be needed.
- **Use Bill Pugh Singleton** for the **best performance and lazy initialization**.
- **Use Double-Checked Locking** if the Singleton will be accessed frequently by multiple threads.
- **Avoid Lazy Initialization (Not Thread-Safe)** in multithreaded applications.

Each of these implementations has its own advantages and disadvantages. Choose the one that best fits your use case! 🚀