/**
 * 
 */
package edu.gatech.seclass.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Team 54
 *
 */
public class Core {
	private static final String defaultDelimeters = ".?!:;";
	private String delimeters = Core.defaultDelimeters;
	private int wordLengthLimit = 4;
	private String file;
	private double wordCount = 0;
	private double sentenceCount = 0;
	private boolean debug = false;
	
	public Core() {
		
	}

	public void parseArgs(String[] args) throws IllegalArgumentException {
		//expect command line argument to look like this
		//java WC [-ld] [file]
		//-l or -d are optional, require a number/char after them
		//can be in any order
		
		if (args.length == 0)
			throw new IllegalArgumentException("ERROR: No arguments given");
		
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
						throw new IllegalArgumentException("ERROR: " + argTable[i][0] + " option invalid");
					}
				break;
				}
			}
		}
		
		this.wordLengthLimit = Integer.parseInt(argTable[0][2]);
		this.delimeters = argTable[1][2];
		
		//loop through args, if any part is not null, we had extra stuff, invalid
		//*we can move this after the file check, remove the -1 on args.length
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i] != null)
				throw new IllegalArgumentException("ERROR: Arguments invalid");
		}
		
		//instead of last, scan for any non null left, test if file
					
		//last argument should be a file, verify
		String a = args[args.length - 1];
		if (a == null || a.equals(""))
			throw new IllegalArgumentException("ERROR: No file name given");
		File f = new File(a);
		if (!f.exists() || !f.isFile())
			throw new IllegalArgumentException("ERROR: The file either does not exist or it is not a file");
		this.file = a;
		args[args.length-1] = null;
		
	}
	
	public double getAverageSentenceLength() throws FileNotFoundException {
		this.wordCount = 0;
		this.sentenceCount = 0; 
		
		String s;
		double charsInWord = 0;
		//Don't add words in "current" sentence until we get to delimiter
		//we need to discard all words without an ending delimiter
		double wordCountButNoDelimiterYet = 0;
		boolean atLeastOneWordInASentence = false;
		//some regex characters (like '?' and '.') need escape characters
		String delimiterRegex = "^[" + Pattern.quote(this.getDelimeters()) + "]+$";
		
		//i want to use a scanner class to have flexibility 
		//right now i'll read character by character
		Scanner scan = new Scanner(new File(this.getFileName()));
	    scan.useDelimiter("");
	    while (scan.hasNext()) {
	        //going through characters individually
	    	s = scan.next();
	    	this.printDebug(s);
	    	if (s.matches("^[\\s]+$") || s.matches(delimiterRegex) || !scan.hasNext()) {
	    		//regex for whitespace or regex for all our delimiters
	    		//or EOF
	    		//end of old word and start of new word
	    		//we need to treat a delimiter like the end of a word too
	    		if (charsInWord >= this.getWordLengthLimit()) {
	    			wordCountButNoDelimiterYet++;
	    			this.printDebug("(w)");
	    			atLeastOneWordInASentence = true;
	    		}
	    		//reset number of characters in word counter
	    		charsInWord = 0;
	    		if (s.matches(delimiterRegex) || !scan.hasNext()) {
	    			//regex for all our delimiters
	    			//or EOF
	    			//end of old sentence
		    		//start of new sentence
		    		if (atLeastOneWordInASentence) {
			    		this.sentenceCount++;
			    		this.printDebug("(s)");
		    			this.wordCount += wordCountButNoDelimiterYet;
		    			wordCountButNoDelimiterYet = 0;
		    		}
		    		atLeastOneWordInASentence = false;
	    		}
	    	} else {
	    		//anything else should be a character
	    		charsInWord++;
	    	}
	    }
	    scan.close();
	    this.printDebug("\n");
	    this.printDebug("wordCount:");
	    this.printDebug(this.wordCount);
	    this.printDebug(", sentenceCount:");
	    this.printDebug(this.sentenceCount);
	    this.printDebug("\n");
	    if (this.sentenceCount == 0) return 0;
	    //lots of ways to round to nearest .00
	    DecimalFormat df = new DecimalFormat("#.##");
	    return Double.valueOf(df.format(this.wordCount / this.sentenceCount));
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
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void printDebug(String s) {
		if (this.debug)
			System.out.print(s);
	}
	
	public void printDebug(double i) {
		if (this.debug)
			System.out.print(i);
	}

}