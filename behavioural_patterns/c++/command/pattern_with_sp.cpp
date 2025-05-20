#include<iostream>
#include<stack>
#include<memory>
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
    stack<unique_ptr<Command>> s;
public:

    void addCommand(unique_ptr<Command> command){
        command->execute();
        s.push(move(command));
    }

    void undo() {
        if( s.empty() ) {
            cout << "No Commands to Undo" << endl;
            return;
        }

        unique_ptr<Command> top = move(s.top());
        s.pop();
        top->undo();
    }
};


int main(){
    Invoker invoker;
    Document document;
    invoker.addCommand(make_unique<OpenCommand>(document));
    invoker.addCommand(make_unique<CloseCommand>(document));
    invoker.undo();
}