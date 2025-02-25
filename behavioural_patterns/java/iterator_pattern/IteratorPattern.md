# Iterator Pattern

## Overview
The **Iterator Pattern** is a behavioral design pattern that provides a way to access elements of a collection sequentially **without exposing the underlying representation**. This pattern is useful when dealing with different data structures such as Lists, Stacks, Trees, and more, allowing a common way to iterate over them.

In this implementation, we demonstrate the **Iterator Pattern** using **Custom List** and **Custom Stack** classes, each providing their own iterators.

---

## Project Structure
```
behavioural_patterns/java/iterator_pattern/
│── IteratorPattern.java 
```

---

## How It Works
1. The **Iterator** interface defines `hasNext()` and `next()` methods for iteration.
2. The **ListIterator** and **StackIterator** classes implement this interface to traverse their respective collections.
3. The **CustomList** and **CustomStack** classes act as collections that provide iterators to access elements sequentially.
4. The **Client (IteratorPattern.java)** uses these iterators to retrieve and print elements from both data structures.

---

## Code Explanation

### **1. Iterator Interface**
Defines standard methods for iterating over a collection:
```java
interface Iterator{
    Boolean hasNext();
    Object next();
}
```

### **2. Concrete Iterators**
Each data structure has a dedicated iterator:
- **ListIterator** traverses a list in normal order.
- **StackIterator** traverses a stack in LIFO (Last-In-First-Out) order.

```java
class ListIterator implements Iterator{
    List<Object> list;
    int index = 0;

    ListIterator(List<Object> list){
        this.list = list;
    }

    @Override
    public Boolean hasNext() {
        return index < list.size();
    }

    @Override
    public Object next() {
        return list.get(index++);
    }
}
```

```java
class StackIterator implements Iterator{
    Stack<Object> stack;

    StackIterator(Stack<Object> stack){
        this.stack = stack;
    }

    @Override
    public Boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public Object next() {
        return stack.pop();
    }
}
```

### **3. Collection Classes**
**CustomList** and **CustomStack** store data and provide iterator instances.

```java
class CustomList{
    List<Object> list = new ArrayList<>();
    
    public void add(Object obj){
        list.add(obj);
    }

    public Iterator getIterator(){
        return new ListIterator(list);
    }
}
```

```java
class CustomStack{
    Stack<Object> stack = new Stack<>();
    
    public void add(Object obj){
        stack.push(obj);
    }

    public Iterator getIterator(){
        return new StackIterator(stack);
    }
}
```

### **4. Client Code**
Demonstrates the iterator pattern with both List and Stack.
```java
class IteratorPattern {
    public static void main(String[] args) {
        System.out.println("List Iterator Example: ");
        CustomList list = new CustomList();
        list.add("Vineel");
        list.add("Suneel");
        list.add("Sundar");
        list.add("Elon");
        Iterator listIterator = list.getIterator();
        while( listIterator.hasNext() ){
            System.out.println(listIterator.next());
        }

        System.out.println();

        System.out.println("Stack Iterator Example: ");
        CustomStack stack = new CustomStack();
        stack.add("Vineel");
        stack.add("Suneel");
        stack.add("Sundar");
        stack.add("Elon");
        Iterator stackIterator = stack.getIterator();
        while( stackIterator.hasNext() ){
            System.out.println(stackIterator.next());
        }
    }
}
```

---

## Compilation & Execution
```sh
./java.sh IteratorPattern.java
```
---

## Expected Output
```
List Iterator Example:
Vineel
Suneel
Sundar
Elon

Stack Iterator Example:
Elon
Sundar
Suneel
Vineel
```

---

## Applications
- **Java Collections Framework** (e.g., `Iterator<T>` for `ArrayList`, `HashSet`)
- **Database Record Iteration** (e.g., iterating over `ResultSet` in JDBC)
- **Tree Traversal** (e.g., Preorder, Inorder, Postorder Iterators)
- **File System Traversal**

---

## Summary
The **Iterator Pattern** allows sequential access to a collection without exposing its internal representation. This example demonstrates iterators for **List and Stack**, showing how different data structures can be traversed using a common interface. This pattern enhances **encapsulation, code maintainability, and reusability**.

