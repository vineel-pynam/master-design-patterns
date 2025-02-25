# Adapter Pattern - Media Player Example

## Overview
The **Adapter Pattern** is a structural design pattern that allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by converting one interface into another that the client expects.

This project demonstrates the **Adapter Pattern** in Java using a **Media Player** example. The scenario involves an **MP4 Player** that can play only MP4 files. However, an adapter class (**HVECAdapter**) is introduced to allow HVEC files to be played by converting them into an MP4-compatible format.

---

## Project Structure
```
structural_patterns/java/adapter_pattern/
│── AdapterPattern.java   
```

---

## How It Works
1. **IMediaPlayer Interface**: Defines a `play()` method for media players.
2. **MP4Player Class**: Implements `IMediaPlayer` and plays only MP4 files.
3. **HVECAdapter Class**:
   - Implements `IMediaPlayer`.
   - Converts HVEC files into MP4 format by modifying the filename.
   - Uses an instance of `MP4Player` to play the converted file.
4. **Client (AdapterPattern.java)**:
   - Creates an `MP4Player` instance and plays MP4 files.
   - Attempts to play an HVEC file directly (fails).
   - Uses `HVECAdapter` to convert and play an HVEC file successfully.

---

## Code Explanation

### **1. IMediaPlayer Interface**
```java
interface IMediaPlayer{
    void play();
}
```
Defines a common interface for media players.

### **2. MP4Player Class**
```java
class MP4Player implements IMediaPlayer{
    String file;
    MP4Player(String file){
        this.file = file;
    }
    @Override
    public void play(){
        if(file.contains("mp4")){
            System.out.println("Playing Mp4 Video");
        } else {
            System.out.println("Unable to play video.");
        }
    }
}
```
- Checks if the file format is MP4 before playing.
- Prints "Playing Mp4 Video" if the file format is MP4, otherwise fails.

### **3. HVECAdapter Class**
```java
class HVECAdapater implements IMediaPlayer{
    MP4Player mp4Player;
    HVECAdapater(String file){
        file += "mp4"; // Converts file to MP4 format
        mp4Player = new MP4Player(file);
    }
    @Override
    public void play(){
        mp4Player.play();
    }
}
```
- Converts HVEC files to MP4-compatible format.
- Internally uses `MP4Player` to play the adapted file.

### **4. Client Code (AdapterPattern.java)**
```java
class AdapterPattern {
    public static void main(String[] args) {
        // MP4 example
        IMediaPlayer mp4 = new MP4Player("mp4");
        mp4.play();

        // Will Not Play HVEC file.
        IMediaPlayer hvec = new MP4Player("hvec");
        hvec.play();

        // Converts HVEC to MP4 and file plays.
        hvec = new HVECAdapater("hvec");
        hvec.play();
    }
}
```
- Demonstrates:
  1. Playing an MP4 file (works).
  2. Trying to play an HVEC file (fails).
  3. Using `HVECAdapter` to convert and play an HVEC file (works).

---

## Compilation & Execution
```sh
./java.sh AdapterPattern.java
```


---

## Expected Output
```sh
Playing Mp4 Video
Unable to play video.
Playing Mp4 Video
```
- First, the MP4 file plays successfully.
- Then, the direct attempt to play an HVEC file fails.
- Finally, the **HVECAdapter** converts the HVEC file into an MP4-compatible format, allowing it to play.

---

## Applications of the Adapter Pattern
✅ **Legacy Code Integration** – Allows old and new systems to work together.  
✅ **Database Wrappers** – Enables different databases to use a common API.  
✅ **Hardware & Software Compatibility** – E.g., connecting an old printer with a new system.  
✅ **Media Players** – Like VLC supporting multiple formats via adapters.  

---

## Summary
The **Adapter Pattern** is useful when integrating incompatible interfaces. In this example, the **HVECAdapter** enables playing HVEC files using an MP4 player. This pattern ensures code reusability, flexibility, and better system compatibility.

---
🎯 **Key Takeaways:**
- Helps in converting incompatible interfaces.
- Increases code reusability and maintainability.
- Commonly used in software integrations and third-party API compatibility.\