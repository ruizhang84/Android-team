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
		
		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].equals("-l")) {
				//check for -l flag
				//next string should be a positive number
				i++;
				if (!args[i].matches("^[0-9]+$"))
					throw new IllegalArgumentException("The -l option must be given with a valid positive number");
				this.wordLengthLimit = Integer.parseInt(args[i]);
			} else if (args[i].equals("-d")) {
				//check for -d flag
				//next string should be character delimiters
				i++;
				this.delimeters = args[i].toCharArray();
			} else {
				//otherwise, invalid
				throw new IllegalArgumentException();
			}
			if (i == args.length - 1)
				throw new IllegalArgumentException("No file name given.");
		}
			
		//last argument should be a file, verify
		String a = args[args.length - 1];
		if (a.equals(""))
			throw new IllegalArgumentException("No file name given.");
		File f = new File(a);
		if (!f.exists() || !f.isFile())
			throw new IllegalArgumentException("The file either does not exist or it is not a file");
		this.file = a;
		
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