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
		c.parseArgs(args);
		System.out.println(c.getAverageSentenceLength());
	}

}
