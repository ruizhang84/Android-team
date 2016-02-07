/**
 * 
 */
package edu.gatech.seclass.project1;

/**
 * @author Team 54
 *
 */
public class AverageSentenceLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Core c = new Core();
		try {
			c.parseArgs(args);
		} catch (IllegalArgumentException e) {
			System.out.println("Usage: java wc [-ld] [file]");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println(c.getAverageSentenceLength());
	}

}
