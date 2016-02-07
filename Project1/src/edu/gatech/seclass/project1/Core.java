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
	private static final char[] defaultDelimeters = {'.', '?', '!', ':', ';'};
	private char[] delimeters = Core.defaultDelimeters;
	private int wordLengthLimit = 3;
	private String file;
	
	public Core() {
		
	}

	public void parseArgs(String[] args) throws IllegalArgumentException {
		//expect command line argument to look like this
		//java wc [-ld] [file]
		//-l or -d are optional, require a number/char after them
		//can be in any order
		
		if (args.length == 0)
			throw new IllegalArgumentException("No arguments given.");
		
		for (int i = 0; i < args.length; i++) {
			//last argument?  if so it should be a file, verify
			if (i == args.length - 1) {
				if (args[i].equals(""))
					throw new IllegalArgumentException("No file name given.");
				File f = new File(args[i]);
				if (!f.exists() || !f.isFile())
					throw new IllegalArgumentException("The file either does not exist or it is not a file");
				this.file = args[args.length - 1];
			} else if (args[i].equals("-l")) {
				//if not, check for -l flag
				//after -l flag, we have a space, so increment i
				//next string should be a positive number
				i++;
				if (!args[i].matches("^[0-9]+$"))
					throw new IllegalArgumentException("The -l option must be given with a valid positive number");
				this.wordLengthLimit = Integer.parseInt(args[i]);
			} else if (args[i].equals("-d")) {
				//if not, check for -d flag
				//after -l flag, we have a space, so increment i
				//next string should be character delimiters
				i++;
				//if (i >= args.length)
					//-d was given, but either there is no 
				this.delimeters = args[i].toCharArray();
			} else {
				//otherwise, invalid
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	public char[] getDelimeters() {
		return this.delimeters;
	}
	
	public static final char[] getDefaultDelimeters() {
		return Core.defaultDelimeters;
	}
	
	public String getFileName() {
		return this.file;
	}
	
	public int getWordLengthLimit() {
		return this.wordLengthLimit;
	}
	
	public double getAverageSentenceLength() {
		return 0;
	}

}