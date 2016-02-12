# **Requirements Document -- Team 54**

##1 Background

Lauren, an instructor at a local university, requires a software tool to track the average sentence length of her students’ thesis. In particular, these students tend to write very long and wordy sentences. Thus, a user-friendly software tool that calculate the average sentence length of an essay will be built to help students improving their writing style.

We, team 54, will build a high-quality command line tool named "wc" (for word count) that reports the average sentence length from a raw text file. The tool will be 1) customizable based on Lauren's request for flexibility in sentence definition and minimum word length to be counted; 2) user-friendly and error proof due to students’ diverse background in technical proficiency and operating system environments. 

## 2 User Requirements

###2.1 Software Interfaces
The tool will be developed using Java 1.7 (JDK 7) and compiled on the command line with javac with no options. No non-standard Java libraries will be used.  Target operating systems include Linux, Mac, and Windows.

###2.2 User Interfaces
- The tool will be an executable on the command line. The user will specify the file path and configuration options, if any, such as -d (for delimiter) and -l (for word length limit).
- User specifies file path as argument, for example, "wc my_essay.txt". Only local file will be considered. If the user has no read access to the file, an error will be displayed.
- User specifies additional delimiters with -d option followed by the delimiters, for example, "wc my_essay.txt -d ?!:.". This option will replace the default delimiters, not add into the default lists of delimiters. If -d option is used, but no option argument, then throw an error.
- User specifies minimum word length with -l option, followed by the word length, for example, "wc my_essay.txt -l 4". The word length limit specified here is inclusive, with this example, words that have length 4 or longer are counted in. If -l option is used, but no option argument, then throw an error.
- The -d and -l options are optional. The default ones for sentence delimiters are: ".?!:;" (not including the quotes). The default word limit is 4.
- The software tool process one file at a time, that is, interpret the file path as one file, not multiple files.
- The two options can be specified with any order, for example:
 - wc -d ?.! -l 4 my_essay.txt 
 - wc -l 4 -d ?.! my_essay.txt 
- The tool returns the average sentence length on the screen as output. 

###2.3 User Characteristics
The intended users are university students taking Lauren’s course. The average number of students are 45 per unit and 6 units per semester. Students submit their thesis via email to Lauren. The students have a broad range of proficiency in terms of their computer usage and technical skills.

##3 System Requirements
###3.1 Functional Requirements
- The software will read the content of the text file, calculate the average sentence length, and output the result in the standard output. If error occurs, output error message to the standard error.
- The software will first check the command line. If the user provided command line argument is not valid, output error message to the standard error with the following format: "ERROR: [error message]" (not including the quotes).
- The software will first check access to the file, if there is no read permission, output error to standard error in this format: "ERROR: [error message]" (not including the quotes).
- The software assumes the file content is ASCII characters only, process all characters in the file.
- The software does not check or interpret the meaning or languages of the essay, does not perform special treatment to words like: Prof., Mrs., e.g., etc. differently, does not perform special treatment to digits and special characters.
- The software define words as the characters in between standard white spaces, i.e., space, tab, carrage return, newline. A word also ends when a sentence delimiter appear. The word separators and sentence delimiters are not counted in word length and sentence length computation.
- If a sentence has no word, or contain only words with length less than the word limit, the number of words in this sentence is 0. In this case, this sentence is not used in the average word length statistics.
- If all the sentences in the essay has 0 words, or contain words with length less than the word limit, output 0.
- The output is a single line double number rounded to two decimal points. The rounding method is: 5 or higher round up and 4 or lower round down.

###3.2 Non-Functional Requirements
*Maintainability*: Technical documentation will be required in order to help develop and maintain the software tools.

*Usability*: User documentation is required to help students, especially those with no programming experience, to appropriately compile and use the software tool. For the first time user, we expect them to have no difficulty in running the program.

*Performance*: Program should approximately process 5000 characters per second on average.

*Reliability and portability*:  The software tool should avoid crashes.  Scenarios such as invalid input files should be handled gracefully.  Finally, the tool shold run on different operating systems.


