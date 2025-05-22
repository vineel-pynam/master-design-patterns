#include<iostream>
#include<string>
#include<unordered_set>
using namespace std;

class Internet {
public:
    virtual void connect(const string& url) = 0;
    virtual ~Internet() = default;
};

class PublicInternet : public Internet {
public:
    void connect(const string& url) override {
        cout << "[CONNECTED]: " << url << endl;
    }
};

class ProxyInternet : public Internet {
private:
    PublicInternet pi;
    static inline unordered_set<string> blocked= {
        "http://www.google.com", 
        "http://www.facebook.com",
        "http://www.twitter.com"
    };

public:
    void connect(const string& url){
        if( blocked.find(url) != blocked.end() ){
            cout << "[BLOCKED]: " << url << endl;
            return;
        }
    
        
        cout << "Connecting via proxy" << endl;
        pi.connect(url);
    }
};

int main(){
    Internet* internet = new ProxyInternet();
    internet->connect("http://www.facebook.com");
    internet->connect("http://www.youtube.com");
}