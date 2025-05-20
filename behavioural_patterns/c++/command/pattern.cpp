#include<iostream>
#include<stack>
using namespace std;

class Document {
public:
    void open () {
        cout << "[DOCUMENT]: Opened" << endl;
    }

     void close () {
        cout << "[DOCUMENT]: Closed" << endl;
    }
};

class Command {
public:
    virtual void execute() = 0;
    virtual void undo() = 0;
    virtual ~Command() = default;
};

class OpenCommand: public Command {
private:
    Document& document;
public:

    OpenCommand(Document& document) : document(document){}

    void execute() override {
        this->document.open();
    }
    
    void undo() override {
        this->document.close();
    }

};

class CloseCommand: public Command {
private:
    Document& document;
public:

    CloseCommand(Document& document): document(document){}

    void execute() override {
        this->document.close();
    }
    
    void undo() override {
        this->document.open();
    }

};

class Invoker {
private:
    stack<Command*> s;
public:

    void addCommand(Command* command){
        command->execute();
        s.push(command);
    }

    void undo() {
        if( s.empty() ) {
            cout << "No Commands to Undo" << endl;
            return;
        }

        Command* top = s.top();
        s.pop();
        top->undo();
    }
};


int main(){
    Invoker invoker;
    Document document;
    invoker.addCommand(new OpenCommand(document));
    invoker.addCommand(new CloseCommand(document));
    invoker.undo();
}