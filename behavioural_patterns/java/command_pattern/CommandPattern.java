package behavioural_patterns.java.command_pattern;
import java.util.*;

// Text Editor as Example
// Reciever
class TextEditor{
    
    public void insertText(String text){
        System.out.println("[Added]: " + text);
    }

    public void removeText(String text){
        System.out.println("[REMOVED]: " + text);
    }

    public void insertImage(String image){
        System.out.println("[ADDED]: " + image);
    }

    public void removeImage(String image){
        System.out.println("[REMOVED]: " + image);
    }
}

// Command Interface
interface Command{
    void execute();
    void undo();
}

// Concrete Implementations of Command Interface
class InsertTextCommand implements Command{
    
    TextEditor editor;
    String text;
    InsertTextCommand(TextEditor editor, String text){
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.insertText(text);
    }

    @Override
    public void undo() {
       editor.removeText(text);
    }

}

class InsertImageCommand implements Command{
    
    TextEditor editor;
    String image;
    InsertImageCommand(TextEditor editor, String image){
        this.editor = editor;
        this.image = image;
    }

    @Override
    public void execute() {
        editor.insertText(image);
    }

    @Override
    public void undo() {
       editor.removeText(image);
    }

}

class RemoveTextCommand implements Command{
    
    TextEditor editor;
    String text;
    RemoveTextCommand(TextEditor editor, String text){
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.removeText(text);
    }

    @Override
    public void undo() {
       editor.insertText(text);
    }

}

class RemoveImageCommand implements Command{
    
    TextEditor editor;
    String image;
    RemoveImageCommand(TextEditor editor, String image){
        this.editor = editor;
        this.image = image;
    }

    @Override
    public void execute() {
        editor.removeImage(image);
    }

    @Override
    public void undo() {
       editor.insertImage(image);
    }

}

// Responsible for executing commands
class Invoker{
    private Stack<Command> st = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        st.add(command);
    }

    public void undoCommand(){
        if( !st.empty() ){
            Command command = st.peek();
            command.undo();
            st.pop();
        }else{
            System.out.println("No Commands for undoing...");
        }
    }
}

// Client
class CommandPattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        Invoker invoker = new Invoker();

        // inserting
        invoker.executeCommand(new InsertTextCommand(editor, "Vineel Pynam"));
        invoker.executeCommand(new InsertTextCommand(editor, "is software engineer"));
        invoker.executeCommand(new InsertImageCommand(editor, "vineel-pynam.jpg"));

        // undoing..
        invoker.undoCommand();
        invoker.undoCommand();
    }
}
