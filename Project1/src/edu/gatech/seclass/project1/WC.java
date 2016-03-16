/**
 * 
 */
package edu.gatech.seclass.project1;

/**
 * @author Team 54
 *
 */
public class WC {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		Core c = new Core();
		try {
			c.parseArgs(args);
			//c.setDebug(true);
			System.out.format("%.2f%n", c.getAverageSentenceLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			//comment out the below to get the CoreTestInstructors.java JUnit cases to run
			System.exit(1);
		}
	}

}
