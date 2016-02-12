# **User Manual -- Team 54**

##1 General Information
The command line tool "wc" provides user the report of the average sentence length, given any raw text file. 
The tool "wc" is a cross-platform software that can be run on most common operating systems such as windows, Mac, and Linux.

##2 Using the Program 
###2.1 Getting Started
A step by step instruction as follows
- The tool can be excuted on the command line by typing wc and the file path as argument. 
 - For example: wc my_essay.txt
 - a single file should be specified at one time
 
- To take into consideration of different text environments, additional configuration can be specified with options
  including -d (for delimiter) and -l (for word length limit).
  - **with -d option** followed by the delimiters, for example, "wc my_essay.txt -d ?!:.". 
  This option will replace the default delimiters, not add into the default lists of delimiters.
  - **with -l option**, followed by the word length, for example, "wc my_essay.txt -l 4". 
  The word length limit specified here is inclusive, with this example, words that have length 4 or longer are counted in. 
  - There are no specified order for configuration options and only one option can be specified. 
  - The default ones for sentence delimiters are: ".?!:;" (not including the quotes). The default word limit is 4.
  
- After pressing return on keyboard, the tool will return the average sentence length on the screen. 
Then the tool "wc" can be run for the next file.
 
###2.2 Troubleshooting for Errors
- *ERROR: The file either does not exist or it is not a file*:
  - check if file name or file path is given correctly. It does not matter if the extension of text file is ".pdf" or so.
- *ERROR: Arguments invalid*:
  - check if the file path and configuration option is specified as in step-by-step instructions. 
  - check if extra arugment is specified. 
  - check if other undefined option such as '-c' was used.
- *ERROR: No file name given*:
  - check if file name is given after "wc".
- *ERROR: -l option invalid*:
  - check if -d option is used, but no option argument
- *ERROR: -d option invalid*:
  - check if -d option is used, but no option argument.

