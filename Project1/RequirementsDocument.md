# **Requirements Document -- Team 54**

##1 User Requirements
###1.1 Software Interfaces

Lauren, an instructor at a local university, requires a software tool to track the average sentence length of her students’ thesis. In particular, these students tend to write very long and wordy sentences. Thus, a user-friend software tool that tracks the sentence length will be built to help students perfect their writing style.

We, team 54, will build a high-quality command line tool "Average Sentence Length" that reports the average sentence length from a raw text file. The tool will be 1) highly customizable based on Lauren's request for flexibility in counting sentences; 2) user-friendly and error proof due to students’ diverse background in technical proficiency and operating system environments. 

## 2 User Requirements

###2.1 Software Interfaces
The tool will be developed using Java 1.7 (JDK 7) and compiled on the command line with javac with no options. No non-standard Java libraries will be used.  Target operating systems include Linux, Mac, and Windows.

###2.2 User Interfaces
The tool will be an executable on the command line. The user will specify the file path and configuration options, if any, such as -d (for delimiter) and -w (word length limit). The tool returns the average sentence length on the screen as output. 

###2.3 User Characteristics
The intended users are university students taking Lauren’s course. The average number of students are 45 per unit and 6 units per semester. Students submit their thesis via email to Lauren. The students have a broad range of proficiency in terms of their computer usage and technical skills.

###2.4 Assumptions and Dependencies
We assume that students mostly write the thesis in English words and the documents are in UTF-8 code. 

##3 System Requirements
###3.1 Functional Requirements
- User execute the software from command line in windows, linux or mac.
- User specifies file path.
- User specifies additional delimiters with -d option.
- User specifies minimum word length with -l option.
- Output average sentence length.

The software tool returns the average words per sentence as results and rounded down the number to the nearest integer. The tool will detect the error condition, process the error accordingly and return friendly error message.  Lauren does not want the shorts words (default three letters) to be counted towards the length of a sentence.  The end of a sentenace is defined as a period (.), question mark (?), or exclamation point (!).  However, this can be changed via configuration to include other delimiters such as comma (,), semi-comma (;), and colon (:).

###3.2 Non-Functional Requirements
####3.2.1 Software Quality Attributes
*Maintainability*: Technical documentation will be required in order to help develope and maintain the software tools.

*Usability*: User documentation is required to help students, especially those with no programming experience, to appropriately compile and use the software tool. For the first time user, we expect them to have no difficulty in running the program.

*Performance*: The software tool should process the thesis in a very fast manner.  Depending on the size of input text, the response time should be within minutes.

*Reliability and portability*:  The software tool should avoid crashes.  Scenarios such as invalid input files should be handled gracefully.  Finally, the tool shold run on different operating systems.

The reliability and portability of the software tool will be thoroughly tested. The reliability will be assured by testing various input files. The portability will be assured by running our tool on different operating systems. The performance of software tool will be tested using different sizes of raw text.


