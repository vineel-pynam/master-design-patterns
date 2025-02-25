# Proxy Pattern Example

## Overview
The **Proxy Pattern** is a structural design pattern that provides an object that acts as a substitute or placeholder for another object. It controls access to the original object, allowing additional functionality such as access control, lazy initialization, logging, etc.

This project demonstrates the Proxy Pattern using an **Internet Access System**, where a proxy restricts access to certain blocked websites.

---

## Project Structure
```
proxy_pattern/
|-- structural_patterns/java/proxy_pattern/
|   |-- ProxyPattern.java  
```

---

## How It Works
1. **`Internet` (Interface):** Defines the method `connectTo(String site)`.
2. **`PublicInternet` (Real Object):** Implements `Internet` and allows unrestricted access.
3. **`ProxiedInternet` (Proxy Object):** Controls access by maintaining a list of blocked sites.
4. **`ProxyPattern` (Client):** Demonstrates the use of both `PublicInternet` and `ProxiedInternet`.

---

## Code Explanation

### **1. Internet Interface**
```java
interface Internet {
    void connectTo(String site);
}
```
- Defines the `connectTo` method, which is implemented by both real and proxy internet classes.

### **2. Public Internet (Real Object)**
```java
class PublicInternet implements Internet {
    @Override
    public void connectTo(String site) {
        System.out.println("[OK]: Connecting to " + site);
    }
}
```
- Provides unrestricted internet access.

### **3. Proxied Internet (Proxy Object)**
```java
class ProxiedInternet implements Internet {
    private static List<String> blockedSites;
    PublicInternet internet;
    
    static {
        blockedSites = new ArrayList<>();
        blockedSites.add("facebook.com");
        blockedSites.add("youtube.com");
        blockedSites.add("games.com");
    }

    ProxiedInternet() {
        internet = new PublicInternet();
    }

    public void connectTo(String site) {
        if (blockedSites.contains(site)) {
            System.out.println("[FAILED]: Access denied to " + site);
        } else {
            internet.connectTo(site);
        }
    }
}
```
- **`blockedSites`** (static list) stores blocked website names.
- The constructor initializes the `PublicInternet` object.
- The `connectTo` method checks if the site is blocked before allowing access.

### **4. Client Code (ProxyPattern.java)**
```java
class ProxyPattern {
    public static void main(String[] args) {
        Internet pi = new PublicInternet();
        pi.connectTo("games.com");  // Allowed

        Internet proxy = new ProxiedInternet();
        proxy.connectTo("games.com");  // Blocked
    }
}
```
- Connects directly using `PublicInternet` (no restriction).
- Connects using `ProxiedInternet`, which **blocks access** to certain sites.

---

## Compilation & Execution
### **1. Compile the Java Files:**
```sh
./java.sh ProxyPattern.java
```
---

## Expected Output
```
[OK]: Connecting to games.com
[FAILED]: Access denied to games.com
```
- The first connection using `PublicInternet` is **successful**.
- The second connection using `ProxiedInternet` **fails** due to site blocking.

---

## Applications of Proxy Pattern
✅ **Access Control** → Restrict access to certain resources (e.g., parental control, firewall).
✅ **Lazy Initialization** → Load objects only when needed (e.g., virtual proxy for image loading).
✅ **Logging & Monitoring** → Log actions before executing them.
✅ **Caching** → Cache frequently used objects or database queries.
✅ **Security Proxies** → Prevent unauthorized operations on sensitive objects.

---

## Summary
- The **Proxy Pattern** acts as a middleman to **control access** to another object.
- This implementation **restricts access** to blocked sites while allowing other connections.
- It is useful for **access control, caching, logging, and security enforcement**.