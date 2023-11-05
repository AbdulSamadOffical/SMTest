# Welcome to our coding test!

Your solution to this coding test will be evaluated based on its:
 * Adherence to best coding practices
 * Correctness
 * Efficiency

Take your time to fully understand the problem and formulate a plan before starting to code, and don't hesitate to ask any questions if you have doubts.

# Objective

Since we are a money transfer company this test will revolve around a (very) simplified transaction model. Our aim is to implement the methods listed in `com.smallworld.TransactionDataFetcher`, a component that allows us to get some insight into the transactions our system has.

A battery of test transactions is stored in `transactions.json` that is going to be used as a datasource for all our data mapping needs.

Each entry in `transactions.json` consists of:
 * mtn: unique identifier of the transaction
 * amount
 * senderFullName, senderAge: sender information
 * beneficiaryFullName, beneficiaryAge: beneficiary information
 * issueId, issueSolved, issueMessage: issue information. Transactions can:
   * Contain no issues: in this case, issueId = null.
   * Contain a list of issues: in this case, the transaction information will be repeated in different entries in `transactions.json` changing the issue related information.

Each method to be implemented includes a brief description of what's expected of it.

The parameters and return types of each method can be modified to fit the model that contains the transaction information

Have fun!


# Solution Strategy 
![Low Level Design](https://github.com/AbdulSamadOffical/SMTest/assets/96175494/77b0e060-434f-4f94-9831-8027fa157072)

In our Low-Level Design, we've thoughtfully applied the Repository pattern to enhance the modularity and maintainability of our solution. The primary motivation behind this design choice is to promote    substitutability in software, fostering a loose coupling between components.

With the Repository pattern in place, our service layer interacts with data repositories through well-defined interfaces (e.g., ITransactionRepository). We can also replace the underlying implementation of the BaseRepository without affecting the service layer. Consequently, we have the flexibility to switch between different data sources, adapt to evolving requirements, or even experiment with alternative database technologies.

Moreover, the Repository pattern significantly enhances the testability of our code. We've harnessed this by crafting unit tests for our Transaction service. These tests are empowered by mock repositories, which enable us to isolate the service layer during testing. As a result, we can comprehensively validate the service's behavior without the need for actual data source interactions.

In summary, our design embraces the Repository pattern to ensure loose coupling, encourage substitutability, and facilitate thorough testing. 
