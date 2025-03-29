**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:           |     |
|---------------------|-----|
| Student Names:      |     |
|   Shahed Issa       |     |
|   Neha Parmar       |     |
|   Jaival Patel      |     |
|   Jaisumer Sandhu   |     |

# Introduction

This lab explores two essential aspects of software testing: Mutation Testing and GUI Testing. The objective is to gain hands-on experience with mutation testing to assess the robustness of test suites and to explore GUI testing through automation tools.

In the first part, we use a mutation testing tool, Pitest, to introduce mutants into Java classes Range.java and DataUtilities.java. By executing our test suites against these mutants, we evaluate the mutation score and enhance the effectiveness of our test cases. This process helps us understand how well our test suite can detect subtle faults and allows us to refine our test cases to increase fault detection capability.

The second part of the lab involves automated GUI testing using Selenium. We design and execute test cases for selected web applications, verifying their correctness through automated checks. Additionally, we compare Selenium with an alternative tool, Sikulix, evaluating their respective strengths and limitations

# Analysis of 10 Mutants of the Range class 

1. getLength
    1. Mutation: Replaced subtraction with addition (Killed)
    2. Explanation: Changed return this.upper - this.lower; to return this.upper + this.lower;, altering the length calculation.
    3. Why Killed: The test suite asserts the correct length calculation, and adding instead of subtracting resulted in incorrect values, causing test failures.

# Report all the statistics and the mutation score for each test class

# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

# A discussion of what could have been done to improve the mutation score of the test suites

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
