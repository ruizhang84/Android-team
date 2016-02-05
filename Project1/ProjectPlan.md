# **Project Plan Document -- Team 54**

##1 Introduction
This project "Average Sentence Length" aims to create a tool that helps students to keep track of the average number of words per sentence in their essays, which also helps them to follow a perfect writing style.

The software that we are going to build to solve this purpose is a command line Java based tool that can be used by all students who have no technical background to students who have self-sufficient programming knowledge. A documentation will also be provided on how to use this tool so that everyone who uses it can understand how it works. There are some situations which may lead to error conditions which may be indicated to the students by producing some friendly error messages if something goes wrong.

##2 Process Description
###2.1 Project Life cycle
We adopt Waterfall model in our software development process. The life cycles of this project include Requirement Gathering and Analysis, System Design, Implementation, Testing and Maintenance.

###2.2 Process Activities 
####2.2.1 *Requirement Gathering and Analysis*:
In this phase all possible requirements of the system to be developed are base lined and documented in a requirement Specification document. 

##### *Activities Performed:*
- The software development team requests for meeting with Lauren, to understand the requirements.
- Lauren explains the software tool that she wants the team to develop.
- Project plan is created used to guide both project execution and project control.

##### *Entry Criteria:*
- Understand the requirements thoroughly by doing brainstorming and walk-throughs.
- Perform requirements feasibility analysis to ensure that the requirements are testable or not.

##### *Exit Criteria:*
- Requirements Specification Document
- Project Plan

####2.2.2 *System Design*
In this phase the architecture of the system to be developed is designed as per the requirements specified and is documented.
Define a series of components such as screens/interfaces/modules or other systems and how they interact with each other.
Hardware and Software requirements of the system are specified for each of the screens.

##### *Activities Performed:*
The System being developed should have the following standards:

1) System is to be built with Vanilla java 1.7/1.8  – must not make use of non-standard java libraries
2) Program should be an application ie., it should have a main method and should be executable from the command line using the java command
3) User should specify a file path they wish to be analyzed as a command line argument
4) User should be able to specify which delimiters count as sentence separators, using the flag –d, defaulting to period, question mark or exclamation mark may be even the comma or semi-colon
5) User should be able to specify a lower limit for word length using the flag –l defaulting to Lauren’s guess at what might be good. (words more than 3 letters)
6) Program’s output should be the average sentence length rounded to the nearest integer

##### *Entry Criteria:*
- Requirements Specification Document

##### *Exit Criteria:*
- HLD (High Level Design document)
- LLD (Low Level Design document)

####2.2.3 Implementation
Actual code is implemented during this phase as per the design specified. Unit testing is performed to test individual pieces of code and verify whether individual modules are doing what they are intended to do.

##### *Entry Criteria:*

- HLD (High Level Design document)
- LLD (Low Level Design document)

##### *Exit Criteria:*
- Programs, Unit test cases and results

####2.2.4 System Testing

Perform Integration testing by putting together all the modules that have been developed and tested individually to make sure if it works as expected. Perform functional and non-functional testing to make sure that the system meets the requirements. Report issues in case of anomalies Log the defect and track it to closure. Progress on testing can be tracked via tools like traceability metrics, ALM.


##### *Entry Criteria:*
- Unit tested code and test cases

##### *Exit Criteria:*
- Test cases, Test reports, Defect reports and Updated matrices.

####2.2.5 *System Deployment*
During this phase make sure that the exit criteria are met and there are no open Severity 1 issues. Ensure that the environment is up and running. Deploy the application in the respective environment; perform a sanity check in the environment after the application is deployed to ensure the application does not break.

##### *Entry Criteria:*
- Completely built and tested code with no outstanding issues

##### *Exit Criteria:*
- User Manual and environment definition / specification

####2.2.6 *System Maintenance*
Make sure that the application is up and running in the respective environment. If the user encounters any defect, make sure to note and fix the issues faced. In case any issue is fixed; the updated code is deployed in the environment and regression testing is performed to ensure the existing functionality is not changed. 

##### *Entry Criteria:*
- User Manual

##### *Exit Criteria:*
- List of production tickets
- List of new features implemented.

##3 Team
#####*Team Members:*
- Paul Cassell
- Rui Zhang
- Roopa Haribabu
- Fujia Wu

#####*Roles:*
1) Document lead – Responsible for all the documentation works that need to be carried out in the Project.

2)Project Manager - Maintains co-ordination within the team ans is responsible for end to end delivery right from Requirements gathering to Software delivery.

3) Development Lead - Responsible for leading the code development activities

4) Q/A manager - Responsible for interacting with the Client and report testing progress and makes sure that the software is developed as per the requirements and ensures quality software is being delivered.

5) Developer – Responsible for developing the code

6)Tester – Ensures good quality software is being delivered by performing rigorous testing of the software being developed


| Team Members| 	Roles taken| 
| ------------|:-------------:| 
| Paul Cassell| Document lead and Developer |
| Rui Zhang   | Project Manager and Developer|
| Roopa Haribabu| Development Lead and Tester|
| Fujia Wu      | Q/A manager and Developer|


##4 Estimates

Provide estimates for the following metrics:

- Effort hours: 14 hrs per week
- Lines of code: 50 -1000 LOC

##5 Project Schedule - Gantt Chart
![enter image description here](https://lh3.googleusercontent.com/kel9uHojlSfUZZdoqWmMrDhryyl0QWuBGrgPEJXOZ3CDiSZaaYsRYLs1vdEVLyn7bBM=s0 "Gantt Chart_Project1.png")
