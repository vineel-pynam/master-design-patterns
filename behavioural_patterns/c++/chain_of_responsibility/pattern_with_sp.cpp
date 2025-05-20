#include<iostream>
#include<memory>
using namespace std;

class Interview {
protected:
    unique_ptr<Interview> nextInterview;
public:

    void setNextInterview(unique_ptr<Interview> nextInterview){
        this->nextInterview = move(nextInterview);
    }

    bool hasNextInterview(){
        return this->nextInterview != nullptr;
    }
    
    virtual void verify() = 0;
    virtual ~Interview() = default;
};

class TechnicalInterview : public Interview {
private:
    int score = 0;
public:
    TechnicalInterview(unique_ptr<Interview> interview, int score) {
        Interview::setNextInterview(move(interview));
        this->score = score;
    }

    void verify() override {
        if( score < 85 ){
            cout << "[TECHNICAL_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[TECHNICAL_INTERVIEW]: Passed" << endl; 
        }

        if( this->hasNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class LLDInterview : public Interview {
private:
    int score = 0;
public:
    LLDInterview(unique_ptr<Interview> interview, int score) {
        Interview::setNextInterview(move(interview));
        this->score = score;
    }

    void verify() override {
        if( score < 85 ){
            cout << "[LLD_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[LLD_INTERVIEW]: Passed" << endl; 
        }

        if( this->hasNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class HLDInterview : public Interview {
private:
    int score = 0;
public:
    HLDInterview(unique_ptr<Interview> interview, int score) {
        Interview::setNextInterview(move(interview));
        this->score = score;
    }

    void verify() override {
        if( score < 85 ){
            cout << "[HLD_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[HLD_INTERVIEW]: Passed" << endl; 
        }

        if( this->hasNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class HMInterview : public Interview {
private:
    int score = 0;
public:
    HMInterview(unique_ptr<Interview> interview, int score) {
        Interview::setNextInterview(move(interview));
        this->score = score;
    }

    void verify() override {
        if( score < 85 ){
            cout << "[HM_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[HM_INTERVIEW]: Passed" << endl; 
            cout << "[RESULT]: Selected" << endl;
        }

        if( this->hasNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class Candidate {
private:    
    int tScore = 0; 
    int lldScore = 0; 
    int hldScore = 0; 
    int hmScore = 0; 
public:
    Candidate(int tScore, int lldScore, int hldScore, int hmScore) 
    : tScore(tScore), lldScore(lldScore), hldScore(hldScore), hmScore(hmScore) {}

    void analyzeResult(){
        unique_ptr<Interview> interviewChain = make_unique<TechnicalInterview>(
            make_unique<LLDInterview>(
                make_unique<HLDInterview>(
                    make_unique<HMInterview>(nullptr, hmScore), 
                    hldScore),
                lldScore), 
            tScore);
        
        interviewChain->verify();
    }
};

int main(){
    cout << "Candidate-1:" << endl;
    unique_ptr<Candidate> candidate1 = make_unique<Candidate>(90, 90, 87, 80);
    candidate1->analyzeResult();

    cout << endl << "Candidate-2:" << endl;
    unique_ptr<Candidate> candidate2 = make_unique<Candidate>(90, 90, 87, 90);
    candidate2->analyzeResult();
}