# Composite Pattern Example - File System

## Overview
This project demonstrates the **Composite Design Pattern** using a **File System** structure. The Composite Pattern is used to treat **individual objects** (files) and **composite objects** (directories containing files or other directories) **uniformly**. This allows us to build a hierarchical structure where operations (e.g., listing files, counting files) can be performed on both individual elements and composite structures.

- **Leaf Nodes**: `File` (Individual files)
- **Composite Nodes**: `Directory` (Folders containing files or subfolders)

## Project Structure
```
structural_patterns/java/composite_pattern/
│── CompositePattern.java 
```

## How It Works
1. **Component Interface (`FileSystem`)**: Defines common methods for files and directories (`listFiles()`, `countFiles()`).
2. **Leaf Node (`File`)**: Implements `FileSystem` but does not contain sub-elements.
3. **Composite Node (`Directory`)**: Implements `FileSystem` and contains a list of `FileSystem` elements (both `File` and `Directory`).
4. **Client (`CompositePattern`)**: Demonstrates file and directory operations.

## Code Explanation
### **1. FileSystem Interface (Component)**
```java
interface FileSystem {
    void listFiles();
    Integer countFiles();
}
```
- Defines a **common interface** for both `File` and `Directory`.

### **2. File Class (Leaf Node)**
```java
class File implements FileSystem {
    String name;
    File(String name) {
        this.name = name;
    }
    
    @Override
    public void listFiles() {
        System.out.println("[File]: " + this.name);
    }
    
    @Override
    public Integer countFiles() {
        return 1;
    }
}
```
- Represents **individual files** that implement `FileSystem`.
- Always returns `1` in `countFiles()` since a single file is counted as one.

### **3. Directory Class (Composite Node)**
```java
class Directory implements FileSystem {
    String name;
    List<FileSystem> directory;
    
    Directory(String name) {
        this.name = name;
        this.directory = new ArrayList<>();
    }
    
    public void addFileOrFolder(FileSystem fileOrFolder) {
        directory.add(fileOrFolder);
    }
    
    @Override
    public void listFiles() {
        System.out.println("[Directory]: " + this.name);
        for (FileSystem file : directory) {
            file.listFiles();
        }
    }
    
    @Override
    public Integer countFiles() {
        int total = 0;
        for (FileSystem file : directory) {
            total += file.countFiles();
        }
        return total;
    }
}
```
- Represents **directories** that can contain both files and other directories.
- Calls `listFiles()` recursively to display all elements.
- Calls `countFiles()` recursively to count total files in the structure.

### **4. Client Code (CompositePattern.java)**
```java
public class CompositePattern {
    public static void main(String[] args) {
        Directory movies = new Directory("Movies");
        Directory songs = new Directory("Songs");
        Directory games = new Directory("Games");

        movies.addFileOrFolder(new File("KGF-1"));
        movies.addFileOrFolder(new File("KGF-2"));
        movies.addFileOrFolder(new File("PUSHPA"));

        songs.addFileOrFolder(new File("KGF-SONG-1.mp3"));
        songs.addFileOrFolder(new File("KGF-SONG-2.mp3"));
        songs.addFileOrFolder(new File("PUSHPA-SONG.mp3"));

        games.addFileOrFolder(new File("GTA-V"));
        games.addFileOrFolder(new File("TOMB RAIDER"));
        games.addFileOrFolder(new File("COUNTER STRIKE"));

        Directory entertainment = new Directory("Entertainment");
        entertainment.addFileOrFolder(movies);
        entertainment.addFileOrFolder(songs);
        entertainment.addFileOrFolder(games);
        entertainment.addFileOrFolder(new File("VLOG-1"));
        entertainment.addFileOrFolder(new File("VLOG-2"));
        entertainment.addFileOrFolder(new File("VLOG-3"));
        entertainment.addFileOrFolder(new File("VLOG-4"));

        System.out.println("Printing all files in entertainment directory: ");
        entertainment.listFiles();
        System.out.println();
        System.out.println("Total Files Count: " + entertainment.countFiles());
    }
}
```
- **Creates directories and files**.
- **Adds files to directories**.
- **Lists all files and directories recursively**.
- **Counts total files** in the system.

## Compilation & Execution
```sh
 ./java.sh CompositePattern.java
```

## Expected Output
```
Printing all files in entertainment directory:
[Directory]: Movies
[File]: KGF-1
[File]: KGF-2
[File]: PUSHPA
[Directory]: Songs
[File]: KGF-SONG-1.mp3
[File]: KGF-SONG-2.mp3
[File]: PUSHPA-SONG.mp3
[Directory]: Games
[File]: GTA-V
[File]: TOMB RAIDER
[File]: COUNTER STRIKE
[File]: VLOG-1
[File]: VLOG-2
[File]: VLOG-3
[File]: VLOG-4

Total Files Count: 13
```

## Applications
✅ **File System Hierarchies**: Directories and files in an OS.
✅ **Company Structures**: Employees, teams, and departments.
✅ **UI Components**: Nested components like buttons inside panels.
✅ **Game Engines**: Objects in a game scene (e.g., characters, enemies, environment).
✅ **Web Structures**: Nested elements in an HTML page (divs inside divs).

## Summary
- The **Composite Pattern** allows treating **individual objects** and **composite objects** uniformly.
- The `FileSystem` interface provides a common structure for `File` and `Directory`.
- `Directory` recursively calls `listFiles()` and `countFiles()`.
- This approach makes the system **flexible, scalable, and easy to maintain**.
