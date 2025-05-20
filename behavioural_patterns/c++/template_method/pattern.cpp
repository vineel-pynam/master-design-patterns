#include<iostream>
using namespace std;

class DataParser{
public:
    void parseData() {
        readData();
        processData();
        saveData();
    }

    virtual void readData() = 0;
    virtual void processData() = 0;
    virtual void saveData() = 0;
    virtual ~DataParser() = default;
}; 

class CSVParser : public DataParser {
public:
    void readData() override {
        cout << "Reading CSV data" << endl;
    }

    void processData() override {
        cout << "Processing CSV data" << endl;
    }

     void saveData() override {
        cout << "Saving CSV data" << endl;
    }
};

class XMLParser : public DataParser {
public:
    void readData() override {
        cout << "Reading XML data" << endl;
    }

    void processData() override {
        cout << "Processing XML data" << endl;
    }

     void saveData() override {
        cout << "Saving XML data" << endl;
    }
};

int main(){
    DataParser* parser = new CSVParser();
    parser->parseData();
    delete parser;
}