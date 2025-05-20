#include<iostream>
using namespace std;

class Interview {
protected:
    Interview* nextInterview;
public:

    void setNextInterview(Interview* nextInterview){
        this->nextInterview = nextInterview;
    }

    bool hashNextInterview(){
        return this->nextInterview != nullptr;
    }
    
    virtual void verify() = 0;
    virtual ~Interview() = default;
};

class TechnicalInterview : public Interview {
private:
    int score = 0;
public:
    TechnicalInterview(Interview* interview, int score) {
        Interview::setNextInterview(interview);
        this->score = score;
    }

    void verify() {
        if( score < 85 ){
            cout << "[TECHNICAL_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[TECHNICAL_INTERVIEW]: Passed" << endl; 
        }

        if( this->hashNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class LLDInterview : public Interview {
private:
    int score = 0;
public:
    LLDInterview(Interview* interview, int score) {
        Interview::setNextInterview(interview);
        this->score = score;
    }

    void verify() {
        if( score < 85 ){
            cout << "[LLD_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[LLD_INTERVIEW]: Passed" << endl; 
        }

        if( this->hashNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class HLDInterview : public Interview {
private:
    int score = 0;
public:
    HLDInterview(Interview* interview, int score) {
        Interview::setNextInterview(interview);
        this->score = score;
    }

    void verify() {
        if( score < 85 ){
            cout << "[HLD_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[HLD_INTERVIEW]: Passed" << endl; 
        }

        if( this->hashNextInterview() ){
            this->nextInterview->verify();
        }
    }
};

class HMInterview : public Interview {
private:
    int score = 0;
public:
    HMInterview(Interview* interview, int score) {
        Interview::setNextInterview(interview);
        this->score = score;
    }

    void verify() {
        if( score < 85 ){
            cout << "[HM_INTERVIEW]: Failed" << endl; 
            return;
        }else{
            cout << "[HM_INTERVIEW]: Passed" << endl; 
            cout << "[RESULT]: Selected" << endl;
        }

        if( this->hashNextInterview() ){
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
        Interview* interviewChain = new TechnicalInterview(
            new LLDInterview(
                new HLDInterview(
                    new HMInterview(nullptr, hmScore), 
                    hldScore),
                lldScore), 
            tScore);
        
        interviewChain->verify();
    }
};

int main(){
    cout << "Candidate-1:" << endl;
    Candidate* candidate1 = new Candidate(90, 90, 87, 80);
    candidate1->analyzeResult();

    cout << endl << "Candidate-2:" << endl;
    Candidate* candidate2 = new Candidate(90, 90, 87, 90);
    candidate2->analyzeResult();
}