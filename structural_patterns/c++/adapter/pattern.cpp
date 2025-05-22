#include<iostream>
#include<string>
using namespace std;

class Parser{
public:
    virtual void parse(string& data)  = 0;
    virtual ~Parser() = default;
};

class JsonParser : public Parser {
public:
    void parse(string& data) override {
        cout << "[Parsed]: " << data << endl;
    }
};

class JsonAdapter: public Parser {
private:
    JsonParser jsonParser;
public:
    void parse(string& data) override {
        if( data.find("json") == string::npos ){
            data += " json: from adapter";
        }
        jsonParser.parse(data);
    }

};

int main(){
    string data1 = "Vineel json";
    string data2 = "Vineel";
    Parser* parser = new JsonAdapter();  
    parser->parse(data1);
    parser->parse(data2);

    delete parser;
}