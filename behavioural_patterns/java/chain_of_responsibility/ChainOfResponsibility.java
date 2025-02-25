package behavioural_patterns.java.chain_of_responsibility;

enum Result{
    HIRE, NO_HIRE
}

// Taking Interview as example.
// Candidate need to get HIRE in all interview rounds inorder to get SELECTED.
// Every round is an elimination round.
abstract class Interview{
    protected Interview nexInterview;

    abstract void verify();

    protected void setNextInterview(Interview interview){
        this.nexInterview = interview;
    }

    protected Boolean hasNextInterview(){
        return nexInterview != null;
    }
}

// Concrete Implementations Of Interview
class DsaInterview extends Interview{
    private Result result;
    DsaInterview(Interview interview, Result result){
        super.setNextInterview(interview);
        this.result = result;
    }

    @Override
    public void verify(){
        if( result == Result.NO_HIRE ){
            System.out.println("Failed in DSA Interview, Better Luck Next Time.");
            return;
        }else{
            System.out.println("You Passed in DSA Round, moving to next Round.");
        }

        if( hasNextInterview() ){
            nexInterview.verify();
        }
    }
}

class HLDInterview extends Interview{
    private Result result;
    HLDInterview(Interview interview, Result result){
        super.setNextInterview(interview);
        this.result = result;
    }

    @Override
    public void verify(){
        if( result == Result.NO_HIRE ){
            System.out.println("Failed in HLD Interview, Better Luck Next Time.");
            return;
        }else{
            System.out.println("You Passed in HLD Round, moving to next Round.");
        }

        if( hasNextInterview() ){
            nexInterview.verify();
        }
    }
}

class LLDInterview extends Interview{
    private Result result;
    LLDInterview(Interview interview, Result result){
        super.setNextInterview(interview);
        this.result = result;
    }

    @Override
    public void verify(){
        if( result == Result.NO_HIRE ){
            System.out.println("Failed in LLD Interview, Better Luck Next Time.");
            return;
        }else{
            System.out.println("You Passed in LLD Round, moving to next Round.");
        }

        if( hasNextInterview() ){
            nexInterview.verify();
        }
    }
}

class HMInterview extends Interview{
    private Result result;
    HMInterview(Interview interview, Result result){
        super.setNextInterview(interview);
        this.result = result;
    }

    @Override
    public void verify(){
        if( result == Result.NO_HIRE ){
            System.out.println("Failed in HM(Hiring Manager) Interview, Better Luck Next Time.");
            return;
        }else{
            System.out.println("You Passed in HM Round");
            System.out.println("You got Selected. Congratulations...!");
        }

        if( hasNextInterview() ){
            nexInterview.verify();
        }
    }
}

// Candidate
class Candidate {
    private Result dsaResult;
    private Result lldResult;
    private Result hldResult;
    private Result hmResult;

    Candidate(Result dsaResult, Result hldResult, Result lldResult, Result hmResult){
        this.dsaResult = dsaResult;
        this.hldResult = hldResult;
        this.lldResult = lldResult;
        this.hmResult = hmResult;
    }

    public void analyzeResult(){
        Interview interview = 
        new DsaInterview(
            new HLDInterview(
                new LLDInterview(
                    new HMInterview(null, hmResult), 
                lldResult), 
            hldResult)
        ,dsaResult);

        interview.verify();
    }
}

// Client
class ChainOfResponsibility {
    public static void main(String[] args) {
        // Vineel Result
        System.out.println("[CANDIDATE]: Vineel");
        Candidate vineel = new Candidate(Result.HIRE, Result.HIRE, Result.HIRE, Result.HIRE);
        vineel.analyzeResult();

        System.out.println();

        // Suneel Result
        System.out.println("[CANDIDATE]: Suneel");
        Candidate suneel = new Candidate(Result.HIRE, Result.HIRE, Result.NO_HIRE, Result.HIRE);
        suneel.analyzeResult();
    }
}
