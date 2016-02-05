# **Requirements Document -- Team 54**

##1 User Requirements
###1.1 Software Interfaces

Lauren, an instructor at local university, requires a software tool to track the average sentence length of her students’ thesis. In particular, these students tend to write very long and wordy sentence. Thus, a user-friend software tool that tracks the sentence length will be built to help students perfect their writing style.

We team54 will together build a high-quality command line tool "Average Sentence Length" that reports the average sentence length from raw text file. The tool will be 1) highly customizable to reflect that Lauren want to flexible configuration on how to count sentence; 2) user-friend and error proof due to students’ diverse background in technical proficiency and operating system environmental. 

## 2 User Requirements

###2.1 Software Interfaces
The tool will be developed using Java 1.7 (or higher) and compiled on command line with javac with no options. The non-standard Java Library will be included as source code. The operating system include Linux, Mac, and Windows.

###2.2 User Interfaces
The tool will be an executable on command line. The user will specify the file path and configurable option, if any, such as -d (for delimiter) and -w (words length limit). The tool returns the average sentence length on the screen as output. 

###2.3 User Characteristics
The intended users are university students taking Lauren’s course. The average number of students are 45 per unit and 6 units per semester. Students submit their thesis via email to Lauren. The students are with broad range of proficiency in terms of their computer usage and tech skills.

###2.4 Assumptions and Dependencies
We assume that students mostly write the thesis in English words and the documents are in UTF-8 code. 

##3 System Requirements
###3.1 Functional Requirements
- User execute the software from command line in windows, linux or mac.
- User specifies file path.
- User specifies additional delimiters with -d option.
- User specifies minimum word length with -l option.
- Output average sentence length.

The software tool returns the average words per sentence as results and rounded down the number to the nearest integer. The tool will detect the error condition, process the error accordingly and send friendly error message.Since Lauren only care about longer words, the shorts words (default three letters) will not be counted towards the length of sentence.The sentence defined as end in period (.), question mark (?), and exclamation (!), and can be configured with ending with other delimiter such as comma (,), semi-comma (;), and colon (:).

###3.2 Non-Functional Requirements
####3.2.1 Software Quality Attributes
*Maintainability*: The technical documentation will be required to help developing and maintaining the software tools.

*Usability*: The user documentation is required to help students, especially one with no programming experience, to appropriately compile and use the software tool. For the first time user, we expect they have no difficulty on running the program.

*Performance*: The software tool should process the thesis in a very fast manner.  Depending on the size of input text, the response time should be within minutes.

*Reliability and portability*: The software tool should avoid of any crash even the input file was not correct and is able run on different operating system.

The reliability and portability of the software tool will be thoroughly tested. The reliability will be assured with different file input. The portability will be assured by running our tool on different operating systems. The performance of software tool will be tested using different size of raw text. 
