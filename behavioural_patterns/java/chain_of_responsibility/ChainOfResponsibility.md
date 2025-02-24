# Chain of Responsibility Pattern - Interview Process

## Overview
The Chain of Responsibility pattern allows an object to pass a request along a chain of handlers until one of them handles the request. This implementation models a job interview process where a candidate must pass multiple rounds (DSA, HLD, LLD, and Hiring Manager) to get selected. Each interview round is an elimination round, demonstrating how the pattern ensures sequential processing of requests.

## Project Structure
```
behavioural_patterns/java/chain_of_responsibility/
│── ChainOfResponsibility.java 
```

## How It Works
1. **Interview Process as a Chain:**
   - Each interview round processes the candidate’s result.
   - If the candidate fails in any round, they are eliminated.
   - If they pass, the next round is triggered automatically.
2. **Handler Implementation:**
   - Each interview class extends `Interview` and implements `verify()` to check the candidate’s result.
   - If passed, it invokes the next interview in the chain.
3. **Candidate Analysis:**
   - The `Candidate` class initializes the interview chain and starts the verification.
   
## Code Explanation
- **Interview.java**: Defines the base class with methods to set the next interview and check the chain.
- **Concrete Interview Classes**: Implements verification logic for each round.
- **Candidate.java**: Creates a chain of interview objects and invokes verification.
- **ChainOfResponsibility.java**: Acts as the client, creating candidates and triggering their evaluation.

## Compilation & Execution
```sh
./java.sh ChainOfResponsibility.java
```

## Expected Output
```
[CANDIDATE]: Vineel
You Passed in DSA Round, moving to next Round.
You Passed in HLD Round, moving to next Round.
You Passed in LLD Round, moving to next Round.
You Passed in HM Round
You got Selected. Congratulations...!

[CANDIDATE]: Suneel
You Passed in DSA Round, moving to next Round.
You Passed in HLD Round, moving to next Round.
Failed in LLD Interview, Better Luck Next Time.
```

## Applications
- **Customer Support Systems**: Requests escalate through different support levels until resolved.
- **Logging Mechanisms**: Messages pass through different loggers until a suitable handler processes them.
- **Middleware Processing**: Used in web frameworks to process requests through a chain of filters.

## Summary
This project demonstrates the Chain of Responsibility pattern by simulating a multi-stage interview process where each round acts as a handler in the chain. The pattern helps in decoupling sender and receiver, making it flexible and scalable.

