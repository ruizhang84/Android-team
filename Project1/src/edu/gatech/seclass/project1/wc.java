/**
 * 
 */
package edu.gatech.seclass.project1;

/**
 * @author Team 54
 *
 */
public class wc {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		Core c = new Core();
		try {
			c.parseArgs(args);
			//c.parseArgs(new String[]{"-d", "bb.", "./simple.txt"});
			c.setDebug(true);
			//System.out.println(c.getAverageSentenceLength());
			System.out.format("%.2f%n", c.getAverageSentenceLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			System.exit(1);
		}
		
	}

}
