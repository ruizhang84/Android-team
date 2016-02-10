/**
 * 
 */
package edu.gatech.seclass.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Team 54
 *
 */
public class Core {
	private static final String defaultDelimeters = ".?!:;";
	private String delimeters = Core.defaultDelimeters;
	private int wordLengthLimit = 3;
	private String file;
	private double wordCount = 0;
	private double sentenceCount = 0;
	
	public Core() {
		
	}

	public void parseArgs(String[] args) throws IllegalArgumentException {
		//expect command line argument to look like this
		//java wc [-ld] [file]
		//-l or -d are optional, require a number/char after them
		//can be in any order
		
		if (args.length == 0)
			throw new IllegalArgumentException("No arguments given");
		
		String[][] argTable = new String[][] {{"-l","^[0-9]+$", this.wordLengthLimit + ""}, 
											{"-d", "^.+$", this.delimeters}};		
		
		for (int i = 0; i < argTable.length; i++) {
			for (int j = 0; j < args.length; j++) {
				if (args[j] == null) continue;
				if (args[j].equals(argTable[i][0])) {
					//matched flag -l or -d in command line args
					if (j+1 < args.length && args[j+1] != null && args[j+1].matches(argTable[i][1])) {
						//let's make sure the argument following the flag
						//exists and that it's not null
						//lets see if our regex matches
						//if so, save in argTable slot 3
						argTable[i][2] = args[j+1];
						//set these 2 args to null since we are done with them
						args[j] = null;
						args[j+1] = null;
						j++;
					} else {
						throw new IllegalArgumentException(argTable[i][0] + " option invalid");
					}
				break;
				}
			}
		}
		
		this.wordLengthLimit = Integer.parseInt(argTable[0][2]);
		this.delimeters = argTable[1][2];
		
		//loop through args, if any part is not null, we had extra stuff, invalid
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i] != null)
				throw new IllegalArgumentException();
		}
					
		//last argument should be a file, verify
		String a = args[args.length - 1];
		if (a == null || a.equals(""))
			throw new IllegalArgumentException("No file name given");
		File f = new File(a);
		if (!f.exists() || !f.isFile())
			throw new IllegalArgumentException("The file either does not exist or it is not a file");
		this.file = a;
		args[args.length-1] = null;
		
	}
	
	public String getDelimeters() {
		return this.delimeters;
	}
	
	public static final String getDefaultDelimeters() {
		return Core.defaultDelimeters;
	}
	
	public String getFileName() {
		return this.file;
	}
	
	public int getWordLengthLimit() {
		return this.wordLengthLimit;
	}
	
	
	
	// a function to calculate average sentence length 
	// given the delimiter, minimum character length in a word,
	// and filename
	public double getAverageSentenceLength (){
		double ave_len = 0;   //default ave_len = 0;
		wordCount = 0;
		sentenceCount = 0;
		
		//get filename, delimiter, and minimum characters
		String filename = this.getFileName();
		String delimiter = this.getDelimeters();
		int wordLengthLimit = this.getWordLengthLimit();

		int charLen = 0;  //current character length
		
		//read in file and throws exceptions
        try {
            // FileReader reads plain text
            FileReader fileReader = new FileReader(filename);

            // to improve performance wrap FileReader in BufferedReader
            BufferedReader file = new BufferedReader(fileReader);

    		//***************
            //loop through this.file
    		//for every word that is >= this.wordLengthLimit, inclusive limits, this.wordCount++
    		//every time we get to a sentence delimiter, sentenceCount++
    		//return average
            String line = null;

            boolean charCount = true; //whether to keep track the characters number
            
            while((line = file.readLine()) != null) {
            	//iteration through string line
            	for (int i = 0; i < line.length(); i++) {
            		
            		if (line.charAt(i) == delimiter.charAt(0) ){ //delimiter case
            			
            			boolean delimiterMatch = true;
            			
            			if (charLen == 0 && charCount == false ){ //new word
            				charCount = true;
            			}
            			
            			//check if match with delimiter
            			// if match sentence Count ++
            			// else counts as characters
            			for (int j = 0; j < delimiter.length(); j++){
            				charLen ++;
            				
            				if (i+j >= line.length()								//partial matched delimiter before end line
            					|| 	line.charAt(i+j) != delimiter.charAt(j) ){	//not matched
            						 	
            					delimiterMatch = false;
            					i += j+1;                   	//add up matched and passed characters
            					
            					if (charCount && charLen >= wordLengthLimit){
                    				wordCount++;
                    				charCount = false;          //no need to track word length
                				}
            					
            					break;
            				}
            			}
            			
            			//all match
            			if (delimiterMatch){
            				charLen = 0;
            				charCount = false;
            				sentenceCount++;
            			}
                        

            		}else{
            			//with in a sentence
            			if ( line.charAt(i) == ' '){// space
            				charLen = 0;
            				charCount = false;
            			}else if (charLen == 0 && charCount == false ){ //new word
            				charCount = true;
            				charLen ++;
            			}else{
            				charLen ++;            				
            			}
            			
            			if (charCount && charLen >= wordLengthLimit){
            				wordCount++;
            				charCount = false; //no need to track word length
        				}
            		}
            		
            	}
            	
            
            }   
            
            // round up double in two decimal place
            if (sentenceCount != 0){
            	ave_len = Math.round(100.0*((double) wordCount/sentenceCount))/100.0 ;
            }

            // close the plain text file
            file.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println( "ERROR: Unable to open file '" + filename + "'");                
        }
        catch(IOException ex) {
            System.out.println("ERROR: reading file '" + filename + "'");                  
        }
		
		
		return  (double) ave_len;
	}

}