/**
 * 
 */
package edu.gatech.seclass.project1;

import java.io.File;

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
	
	public double getAverageSentenceLength() {
		//loop through this.file
		//for every word that is > this.wordLengthLimit, this.wordCount++
		//every time we get to a sentence delimiter, sentenceCount++
		//return average
		return 0;
	}

}