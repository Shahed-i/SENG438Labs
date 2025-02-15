
# ENSF 438 Lab 1 - Introduction to Testing and Defect (Bug) Tracking

**Group Number: 6 - Neha Parmar, Shahed Issa, Jaisumer Sandhu, Jaival Patel**  
**Date: January 29, 2025**  

## Introduction
Before this lab, we had some familiarity with exploratory and manual functional testing. From the lectures, we knew that exploratory testing involves designing tests and executing them without predefined scripts, making it useful for discovering unexpected defects. On the other hand, manual testing follows predefined test cases to verify specific functionalities and ensure they behave as expected. In terms of hands-on experience, we have had practice using unit tests and JUnit testing in previous classes, but no actual reporting or peer reviewing of bugs. Our prior experience was mostly theoretical with limited hands-on exposure. In this lab, we used a combination of exploratory and manual function testing to test an ATM simulator, as well as identify and report any bugs we find.

## High-level Description of the Exploratory Testing Plan
For our exploratory testing, we aimed to examine key ATM functionalities like user authentication through card number and PIN entry, withdrawals, deposits, transfers, and balance inquiries. Additionally, we focused on ATM cash handling, specifically the availability of cash bills, error handling in cases of invalid inputs, insufficient funds, and system failures. UI responsiveness and system spelling of messages were also considered.

### Approach for Exploratory Testing:
1. **Start with the most common user actions:**
   - Successful transactions  
   - Correct PIN entry  

2. **Move to exceptional paths:**
   - Invalid PIN attempts  
   - Overdraft attempts  

3. **Trying edge cases that are uncommon for users to try, but still possible:**
   - Depositing a negative amount  
   - Withdrawing more than the ATM balance  

4. **Overall system stability tests:**
   - Multiple consecutive transactions  
   - Abrupt shutdown during transactions  

This method ensured that we first verified core functionality before moving to more complex and uncommon scenarios.

## Exploratory Test Cases

| **Test Case**                                   | **Steps**                                                                                                                                                     | **Expected Result**                                                    | **Actual Result**                                                                                                                                       |
|-------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Successful Withdrawal Transaction**            | 1. Start ATM. 2. Enter valid card (1) and PIN (42). 3. Choose Withdraw (Checking). 4. Enter $40. 5. Confirm transaction. 6. Verify balance deduction.         | Transaction completes successfully. Balance reflects withdrawal.       | Transaction completes successfully. Withdrawing $20 withdraws $40. Options are one off from the menu.                                                    |
| **Successful Deposit Transaction**               | 1. Start ATM. 2. Enter valid card and PIN. 3. Select Deposit (Checking). 4. Enter $100. 5. Simulate inserting envelope. 6. Verify deposit.                      | Deposit is accepted and balance updates correctly.                     | Deposit is accepted, but balance updates incorrectly ($190 instead of $200).                                                                               |
| **Correct PIN Entry**                            | 1. Start ATM. 2. Enter valid card (1) and PIN (42). 3. Verify access.                                                                                         | System accepts correct PIN and allows transactions.                    | Matches expected result.                                                                                                                                |
| **Invalid PIN Entry**                            | 1. Start ATM. 2. Enter valid card. 3. Enter incorrect PIN (9999). 4. Retry three times.                                                                        | "Invalid PIN" error. Account locks after three incorrect attempts.     | Matches expected result.                                                                                                                                |
| **Withdrawal Exceeding Account Balance**         | 1. Start ATM. 2. Enter valid card and PIN. 3. Select Withdraw (Checking). 4. Enter $200 (when balance is $100). 5. Confirm.                                    | "Insufficient Funds" error.                                           | Matches expected result.                                                                                                                                |
| **Depositing Zero**                              | 1. Start ATM. 2. Enter valid card and PIN. 3. Select Deposit. 4. Enter $0. 5. Attempt confirmation.                                                           | System rejects input and displays error.                               | System allows deposit.                                                                                                                                   |
| **Withdrawing More Than ATM Balance**            | 1. Start ATM with $100 available. 2. Enter valid card and PIN. 3. Select Withdraw (Checking). 4. Enter $200. 5. Confirm.                                       | "Insufficient ATM Funds" message.                                      | Matches expected result.                                                                                                                                |
| **Multiple Consecutive Transactions**            | 1. Start ATM. 2. Perform deposit, withdrawal, transfer, and balance inquiry.                                                                                  | System handles transactions without errors or crashes.                 | Matches expected result.                                                                                                                                |
| **Abrupt Shutdown During Transaction**           | 1. Start ATM. 2. Begin withdrawal transaction (enter amount but do not confirm). 3. Click System Power Off. 4. Restart ATM. 5. Check transaction status.        | System discards incomplete transaction or notifies user upon restart. | Did not allow shutdown. Processed withdrawal without confirmation.                                                                                      |
| **Checking Balance for Savings Account**         | 1. Start ATM. 2. Insert valid card (1, PIN: 42). 3. Select balance inquiry. 4. Choose Savings.                                                                  | Displays Savings balance.                                             | No option to check Savings.                                                                                                                              |
| **Checking Balance for Checking Account**        | 1. Start ATM. 2. Insert valid card (1, PIN: 42). 3. Select balance inquiry. 4. Choose Checking.                                                                | Displays correct balance.                                             | Matches expected result.                                                                                                                                |
| **Checking Balance for Money Market Account**    | 1. Start ATM. 2. Insert valid card (1, PIN: 42). 3. Select balance inquiry. 4. Choose Money Market.                                                            | Displays Money Market balance.                                        | Displays error, random value, then Savings inquiry.                                                                                                      |
| **Successful Transfer Transaction**              | 1. Start ATM. 2. Enter valid card and PIN. 3. Select Transfer. 4. Enter transfer details. 5. Verify transaction.                                                | Correct amounts are transferred.                                      | Receipt flips sender/receiver. Transfer deducts $0.50.                                                                                                 |
| **Cancelling Withdrawal Transaction Before Selecting Amount** | 1. Start ATM with $100 available. 2. Enter valid card and PIN. 3. Select Withdraw (Checking). 4. Press Cancel.                                                | Cancellation of transaction.                                          | Cancellation was correct, but there was a spelling error → “wood” rather than “would”.                                                                   |
| **Cancelling Transfer Transaction Before Dollar Amount Entry** | 1. Start ATM with $100 available. 2. Enter valid card and PIN. 3. Select Transfer. 4. Select either the checking or savings account to send money to and from. 5. Press Cancel. | Cancellation of transaction.                                          | Cancellation was correct, but there was a spelling error → “wood” rather than “would”.                                                                   |


## Comparison of Exploratory and Manual Functional Testing

| Aspect | Exploratory Testing | Manual Scripted Testing |
|--------|---------------------|-------------------------|
| **Approach** | Unscripted, dynamic testing | Predefined test cases and scripts |
| **Flexibility** | High; allows testers to adapt on the go | Low; testers must follow predefined steps |
| **Efficiency** | Faster in uncovering unexpected issues | Ensures thorough coverage but can be slower |
| **Effectiveness** | Good for discovering unknown defects | Good for validating known requirements |
| **Use Case** | Best for initial discovery and edge cases | Best for regression and compliance testing |
| **Trade-offs** | May lack structured coverage | May miss unexpected defects |

## How the Pair Testing Was Managed and Teamwork/Effort Was Divided
Since our team is made up of four members, we took a pair programming approach. For exploratory testing, we conducted a joint 30 minute session where all team members tested aspects of the system together to familiarize ourselves with its behaviour and learn the steps of using Jira for bug reporting, since none of us had used it before. This collaborative session allowed us to quickly identify potential defects and understand the system’s flow. It also allowed us to see the strengths and weaknesses of our team so that we could split up our responsibilities in the most effective way. From there, we each played with the system on our own devices to become as familiar with it as possible before conducting the next testing method. 

For manual scripted testing, we split into pairs, with each pair taking responsibility for executing and documenting different test cases out of the list of 40 test cases that were provided. We also reviewed each other’s results to ensure accuracy and completeness. The same approach was taken for regression testing, where we divided the tasks and cross reviewed our findings.


## Difficulties Encountered, Challenges Overcome, and Lessons Learned

### **Difficulties Encountered:**
- Some system behaviours in edge cases were unclear. For example, when depositing money, the value selected did not match the value deposited, however, the value varied based on which one was selected, and it was difficult to find out the pattern. 
- Certain defects were not immediately obvious and required further investigation. For example, at first we did not realize that the transaction proceeds without confirmation, but after slowing down we noticed it begins before the user can even click the enter button.

### **Challenges Overcome:**
- Improved defect documentation by adding detailed steps to reproduce issues on Jira.
- Conducted systematic re-testing to confirm defects and their impact (regression testing).
- Discussed findings collaboratively to validate observations and ensure consistency.

### **Lessons Learned:**
- Both exploratory and manual scripted testing have their own benefits and should be used together rather than one over the other. Exploratory testing is effective for identifying unexpected issues, whereas manual scripted testing has more consistency and thorough validation.
- Peer reviews significantly enhance the quality and clarity of defect reports.

## Jira Bug Reporting Format Used
- **Testing Type**
- **The function being tested (e.g. login)**
- **Version**
- **The initial state of the system (e.g. the system is on and idle)**
- **Detailed steps to reproduce the defect/bug (e.g. insert a card, enter the correct card number and PIN)**
- **The expected outcome (e.g. the system should successfully accept the customer and display the banking menu)**
- **The actual outcome (e.g. the system crashes or displays an error message)**

## Comments/Feedback on the Lab and Lab Document Itself
Overall, this lab provided a valuable hands-on introduction to software testing. It was useful to have both versions to test to understand how to keep track of defect reports and document their history. The instructions were comprehensible, however it would be helpful if they were more concise and easy to follow. It would be helpful to include additional guidance on writing effective defect reports and using Jira or Azure.