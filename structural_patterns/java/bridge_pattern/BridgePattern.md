# Bridge Pattern Example - Cache and Services

## Overview
This project demonstrates the **Bridge Design Pattern** using **Caches** and **Services**. The Bridge Pattern is used to **decouple abstraction (Service)** from **implementation (Cache)** so that both can vary independently. This approach makes the system more **flexible** and **extensible**.

- **Abstraction**: `Service` (MediaService, MemeService)
- **Implementor (Bridge Interface)**: `Cache` (Redis, Memcached, CouchBase)
- **Concrete Implementations**: `Redis`, `Memcached`, `CouchBase`
- **Refined Abstraction**: `MediaService`, `MemeService`

## Project Structure
```
structural_patterns/java/bridge_pattern/
│── BridgePattern.java   
```

## How It Works
1. **Bridge Interface (`Cache`)**: Defines methods `put()` and `get()` for storing and retrieving values.
2. **Concrete Implementations (`Redis`, `Memcached`, `CouchBase`)**: Implements the `Cache` interface with specific behaviors.
3. **Abstraction (`Service`)**: Holds a reference to a `Cache` instance and delegates calls to it.
4. **Refined Abstractions (`MediaService`, `MemeService`)**: Extend `Service` and provide specialized implementations.
5. **Client (`BridgePattern`)**: Creates instances of services and caches, demonstrating the bridge pattern in action.

## Code Explanation
### **1. Cache Interface (Bridge)**
```java
interface Cache {
    void put();
    void get();
}
```
- Defines common methods for all caching mechanisms.

### **2. Concrete Implementations of Cache**
```java
class Redis implements Cache {
    public void put() { System.out.println("[Redis]: adding value"); }
    public void get() { System.out.println("[REDIS]: getting value."); }
}
```
- Implements `Cache` for **Redis**.
- Similar implementations exist for `Memcached` and `CouchBase`.

### **3. Abstract Service Class (Abstraction)**
```java
abstract class Service {
    protected Cache cache;
    Service(Cache cache) { this.cache = cache; }
    abstract public void put();
    abstract public void get();
}
```
- Holds a reference to `Cache`, which acts as a **bridge**.

### **4. Refined Abstractions (Services using Different Caches)**
```java
class MediaService extends Service {
    MediaService(Cache cache) { super(cache); }
    public void put() { cache.put(); }
    public void get() { cache.get(); }
}
```
- `MediaService` and `MemeService` extend `Service` and interact with the cache.

### **5. Client Code (BridgePattern.java)**
```java
public class BridgePattern {
    public static void main(String[] args) {
        Service mediaService = new MediaService(new Redis());
        Service memeService = new MemeService(new CouchBase());

        mediaService.put();
        memeService.put();
        mediaService.get();
        memeService.get();
    }
}
```
- Creates services with different cache implementations.
- Demonstrates how services interact with caches through the bridge.

## Compilation & Execution
```sh
 ./java.sh BridgePattern.java
```

## Expected Output
```
[Redis]: adding value
[COUCH_BASE]: adding value
[REDIS]: getting value.
[COUCH_BASE]: getting value.
```

## Applications
✅ **Cloud Computing**: Services using different storage solutions.
✅ **Microservices Architecture**: Services interacting with multiple databases.
✅ **Web Caching**: Flexible caching strategies for different applications.
✅ **Database Abstraction**: Decoupling services from database implementations.

## Summary
- The **Bridge Pattern** allows abstraction (`Service`) and implementation (`Cache`) to **evolve independently**.
- We can **add new caches without modifying services** and vice versa.
- This enhances **scalability, flexibility, and maintainability** in real-world applications.

Would you like any modifications or additional explanations? 🚀

