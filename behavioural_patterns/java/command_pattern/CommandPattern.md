# Command Pattern - Text Editor Example

## Overview
The **Command Pattern** is a behavioral design pattern that encapsulates a request as an object, allowing users to parameterize clients with different requests, queue requests, and support undoable operations. In this project, we implement a simple **Text Editor** where users can insert and remove text/images with the ability to **undo** actions.

## Project Structure
```
behavioural_patterns/java/command_pattern/
│── CommandPattern.java 
```

## How It Works
1. **Receiver (TextEditor)**: Performs the actual text/image manipulation operations.
2. **Command Interface**: Defines `execute()` and `undo()` methods.
3. **Concrete Commands**: Implement operations for inserting/removing text and images.
4. **Invoker**: Executes and maintains a history of commands for undo functionality.
5. **Client (CommandPattern.java)**: Creates commands and interacts with the Invoker.

## Code Explanation
### Command Interface
```java
interface Command{
    void execute();
    void undo();
}
```
### Receiver (TextEditor)
```java
class TextEditor{
    public void insertText(String text){ System.out.println("[Added]: " + text); }
    public void removeText(String text){ System.out.println("[REMOVED]: " + text); }
    public void insertImage(String image){ System.out.println("[ADDED]: " + image); }
    public void removeImage(String image){ System.out.println("[REMOVED]: " + image); }
}
```
### Concrete Commands
```java
class InsertTextCommand implements Command{
    TextEditor editor; String text;
    InsertTextCommand(TextEditor editor, String text){ this.editor = editor; this.text = text; }
    public void execute() { editor.insertText(text); }
    public void undo() { editor.removeText(text); }
}
```
(Similar classes exist for `InsertImageCommand`, `RemoveTextCommand`, `RemoveImageCommand`)

### Invoker (Command Executor)
```java
class Invoker{
    private Stack<Command> commandHistory = new Stack<>();
    public void executeCommand(Command command){ command.execute(); commandHistory.add(command); }
    public void undoCommand(){
        if (!commandHistory.empty()) { commandHistory.pop().undo(); }
        else { System.out.println("No Commands for undoing..."); }
    }
}
```

## Compilation & Execution
```sh
./java.sh CommandPattern.java
```

## Expected Output
```
[Added]: Vineel Pynam
[Added]: is software engineer
[ADDED]: vineel-pynam.jpg
[REMOVED]: vineel-pynam.jpg
[REMOVED]: is software engineer
```

## Applications
- **Text Editors (Undo/Redo)**: Microsoft Word, Google Docs
- **GUI Applications**: Button clicks, menu selections
- **Remote Control Systems**: Smart home automation (e.g., Alexa, Google Home)
- **Transaction Processing Systems**: Database commits & rollbacks

## Summary
The **Command Pattern** allows decoupling between **action requests and execution**, providing flexibility, reusability, and undo support. This implementation demonstrates how the pattern can be used to manage text and image operations dynamically in a **text editor**.

---
### **Enhancements Possible**
- Implement **Redo functionality**
- Batch execution with **MacroCommand**
- Save command history for **persistent undo** across sessions

