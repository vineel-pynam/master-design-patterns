package behavioural_patterns.java.iterator_pattern;

import java.util.*;

// Iterator interface
interface Iterator{
    Boolean hasNext();
    Object next();
}

class ListIterator implements Iterator{
    List<Object> list = new ArrayList<>();
    Integer index = 0;

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

class StackIterator implements Iterator{
    Stack<Object> stack = new Stack<>();

    StackIterator(Stack<Object> stack){
        this.stack = stack;
    }

    @Override
    public Boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public Object next() {
        Object val = stack.peek();
        stack.pop();
        return val;
    }

}

// Inventory
class CustomList{
    List<Object> list = new ArrayList<>();

    public void add(Object obj){
        list.add(obj);
    }

    public Iterator getIterator(){
        return new ListIterator(list);
    }
}

class CustomStack{
    Stack<Object> stack = new Stack<>();

    public void add(Object obj){
        stack.push(obj);
    }

    public Iterator getIterator(){
        return new StackIterator(stack);
    }
}

// Client
class IteratorPattern {
    public static void main(String[] args) {
        // List Example
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

        // Stack Example
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
