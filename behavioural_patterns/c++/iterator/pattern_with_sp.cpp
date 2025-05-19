#include<iostream>
#include<vector>
#include<memory>
using namespace std;

template<typename T>
class Iterator{
public:
    virtual bool hasNext() = 0;
    virtual T next() = 0;
    virtual ~Iterator() = default;
};

template<typename T>
class CustomList: public Iterator<T>{
private:
    vector<T> arr;
    int size = 0;
    int index = 0;
public:
    CustomList(const vector<T>& arr){
        this->arr = arr;
        this->size = arr.size();
    }

    T next() override {
        if( !hasNext() ){
            throw out_of_range("No more elements");
        }
        return arr[index++];
    }

    bool hasNext() override {
        return index < size;
    }
};

int main(){

    vector<int> intArr = {1,2,3,4,5};
    vector<string> stringArr = {"a", "b", "c", "d", "e", "f"};

    unique_ptr<Iterator<int>> intIt = make_unique<CustomList<int>>(intArr);
    while( intIt->hasNext() ){
        cout << intIt->next() << " ";
    }
    cout << endl;
    unique_ptr<Iterator<string>> stringIt = make_unique<CustomList<string>>(stringArr);
    while( stringIt->hasNext() ){
        cout << stringIt->next() << " ";
    }

    cout << endl;

}