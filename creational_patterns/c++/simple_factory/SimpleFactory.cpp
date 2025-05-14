#include<iostream>
#include<string>
#include<memory>
#include<functional>
using namespace std;

class ILogger{
public:
    virtual void log(string message) = 0;
    virtual ~ILogger() {}
};

class ErrorLogger: public ILogger {
public:
    void log(string message) override {
        cout << "[ERROR]: " << message << endl;
    }
};

class DebugLogger: public ILogger {
public:
    void log(string message) override {
        cout << "[DEBUG]: " << message << endl;
    }
};

class InfoLogger: public ILogger {
public:
    void log(string message) override {
        cout << "[INFO]: " << message << endl;
    }
};

enum class LoggerType{
    ERROR, INFO, DEBUG
};

class LoggerFactory {
private:
    using Creator = function<unique_ptr<ILogger>()>;
    unordered_map<LoggerType, Creator> mp;

public:

    LoggerFactory(){
        registerLogger(LoggerType::ERROR, [](){return make_unique<ErrorLogger>(); });
        registerLogger(LoggerType::DEBUG, [](){return make_unique<DebugLogger>(); });
        registerLogger(LoggerType::INFO, [](){return make_unique<InfoLogger>(); });
    }

    void registerLogger(LoggerType loggerType, Creator creator){
        mp[loggerType] = creator;
    }

    unique_ptr<ILogger> createLogger(LoggerType type){
        auto it = mp.find(type);
        if( it != mp.end() ){
            return it->second();
        }
        return nullptr;
    }
};

int main(){
    LoggerFactory loggerFactory;
    unique_ptr<ILogger> logger = loggerFactory.createLogger(LoggerType::ERROR);
    if ( logger ){
        logger->log("Hello Vineel");
    }else{
        cout << "Failed to create logger" << endl;
    }
}