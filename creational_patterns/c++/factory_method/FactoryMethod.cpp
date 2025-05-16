#include<iostream>
#include<string>
#include<memory>
using namespace std;

class ILogger{
public:
    virtual void log(string message) = 0;
    virtual ~ILogger() {};
};

class ErrorLogger : public ILogger {
public:
    void log(const string message) override {
        cout << "[ERROR]: " << message << endl;
    }
};

class InfoLogger : public ILogger {
public:
    void log(const string message) override {
        cout << "[INFO]: " << message << endl;
    }
};

class DebugLogger : public ILogger {
public:
    void log(const string message) override {
        cout << "[DEBUG]: " << message << endl;
    }
};


class ILoggerFactory {
public:
    virtual unique_ptr<ILogger> create() = 0;
    virtual ~ILoggerFactory(){};
};

class DebugLoggerFactory : public ILoggerFactory {
public:
    unique_ptr<ILogger> create() override {
        return make_unique<DebugLogger>();
    }
};

class ErrorLoggerFactory : public ILoggerFactory {
public:
    unique_ptr<ILogger> create() override {
        return make_unique<ErrorLogger>();
    }
};

class InfoLoggerFactory : public ILoggerFactory {
public:
    unique_ptr<ILogger> create() override {
        return make_unique<InfoLogger>();
    }
};

int main(){
    unique_ptr<ILoggerFactory> lf = make_unique<ErrorLoggerFactory>();
    unique_ptr<ILogger> logger = lf->create();
    logger->log("Vineel");

    lf = make_unique<DebugLoggerFactory>();
    logger = lf->create();
    logger->log("Vineel");
}