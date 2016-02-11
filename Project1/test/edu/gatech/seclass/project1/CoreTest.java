

/**
 * 
 */
package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * @author Team 54
 *
 */
public class CoreTest extends Core {
	private Core c;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.c = new Core();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.c = null;
	}

	@Test
	public void testParseArgsBadFileName1() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"-l", "4", "-d", ";", "non_existant_file"});
		
	}
	
	@Test
	public void testParseArgsBadFileName2() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"non_existant_file"});
	}
	
	@Test
	public void testParseArgsBadFileName3() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"-d", ".", "non_existant_file"});
	}
	
	@Test
	public void testParseArgsValid1() {
		//test all valid args
		this.c.parseArgs(new String[]{"-d", ".", "./samedirfile.txt"});
		assertEquals(".", this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(4, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid2() {
		//test all valid args
		this.c.parseArgs(new String[]{"-l", "5", "./samedirfile.txt"});
		assertEquals(Core.getDefaultDelimeters(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(5, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid3() {
		//test all valid args
		this.c.parseArgs(new String[]{"-l", "5", "-d", ".;", "./samedirfile.txt"});
		assertEquals(".;", this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(5, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid4() {
		//test all valid args
		this.c.parseArgs(new String[]{"-d", ".", "-l", "6", "./samedirfile.txt"});
		assertEquals(".", this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(6, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid5() {
		//test all valid args
		this.c.parseArgs(new String[]{"./samedirfile.txt"});
		assertEquals(Core.getDefaultDelimeters(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(4, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid6() {
		//-d delimiter is valid, and same as file
		this.c.parseArgs(new String[]{"-l", "3", "-d", "7", "7"});
		assertEquals("7", this.c.getDelimeters());
		assertEquals("7", this.c.getFileName());
		assertEquals(3, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsBad1() {
		//file name should not be first
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("ERROR: Arguments invalid");
		this.c.parseArgs(new String[]{"./samedirfile.txt", "-l", "4"});
	}
	
	@Test
	public void testParseArgsBad2() {
		//test -l argument bad, also file doesn't exist
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -l option invalid");
		this.c.parseArgs(new String[]{"-l", "a", "-d", ";", "non_existant_file"});
	}
	
	@Test
	public void testParseArgsBad3() {
		//test no file name given, -d delimiter is a proper file name
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: No file name given");
		this.c.parseArgs(new String[]{"-l", "3", "-d", "7"});
	}
	
	@Test
	public void testParseArgsBad4() {
		//test no file name given but proper flag
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: No file name given");
		this.c.parseArgs(new String[]{"-l", "7"});
	}
	
	@Test
	public void testParseArgsBad5() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -l option invalid");
		this.c.parseArgs(new String[]{"-l"});
	}
	
	@Test
	public void testParseArgsBad6() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -l option invalid");
		this.c.parseArgs(new String[]{"-l", "-d", "."});
	}
	
	@Test
	public void testParseArgsBad7() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -d option invalid");
		this.c.parseArgs(new String[]{"-d"});
	}
	
	@Test
	public void testParseArgsBad8() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -d option invalid");
		this.c.parseArgs(new String[]{"-d", "-l", "4"});
	}
	
	@Test
	public void testParseArgsBad9() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("ERROR: The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"-c"});
	}
	
	@Test
	public void testParseArgsBad10() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("ERROR: Arguments invalid");
		this.c.parseArgs(new String[]{"-c", ".", "-l", "4"});
	}
	
	@Test
	public void testParseArgsBad11() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -d option invalid");
		this.c.parseArgs(new String[]{"-l", "4", "-d"});
	}
	
	@Test
	public void testParseArgsBad12() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: No arguments given");
		this.c.parseArgs(new String[]{});
	}
	
	@Test
	public void testParseArgsBad13() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: No file name given");
		this.c.parseArgs(new String[]{"-d", "7"});
	}
	
	@Test
	public void testParseArgsBad14() {
		//test only flags
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -l option invalid");
		this.c.parseArgs(new String[]{"-d", "-l"});
	}
	
	@Ignore
	@Test
	public void testGetAverageSentenceLength() throws Exception {
		this.c.parseArgs(new String[]{"-d", "bb.", "./simple.txt"});
		//assertEquals("..", this.c.getDelimeters());
		//here is the output i get when running this test manually with
		//my debug enabled
		//aaa (w)b(s)bb bb.
		//1.0
		//
		//delimiters are b, b, and .
		//the below is wrong
		//also, we shouldn't use a delta
		assertEquals(2, this.c.getAverageSentenceLength(), 0.1);
	}
	
	@Test
	@Ignore
	//test empty file
	//re work
	public void testGetAverageSentenceLength1() throws FileNotFoundException {
		this.c.parseArgs(new String[]{"-d", ".", "./Emptyfile.txt"});
		assertEquals(0, this.c.getAverageSentenceLength(),0);

	}
	
	@Ignore
	@Test(expected=IllegalArgumentException.class)
	//Test to check if appropriate exception is thrown when a file doesn't exist
	public void testGetAverageSentenceLength2() throws Exception {
		//invalid file names are also checked in parseArgs
		//need to rewrite this
		//-paul
		this.c.parseArgs(new String[]{"-d", ".", "./NoSuchFileExists.txt"});
		assertEquals("", "ERROR: Unable to open file 'simple.txt'", this.c.getAverageSentenceLength());

	}
	
	@Ignore
	@Test(expected=IllegalArgumentException.class)
	//Test to check appropriate exception is thrown when file extension is given differently 
	//i did not see where there had to be an extension of the filename check
	//i assumed any filename passed was a text file, so .pdf is never checked
	//-paul
	public void testGetAverageSentenceLength3() {
		this.c.parseArgs(new String[]{"-d", ".", "./simple.pdf"});
	}
	
	@Ignore
	@Test
	public void testGetAverageSentenceLength4() throws Exception{
	//Checks if a number with only 2 decimal places is returned	
		//this is incorrect by my calculations
		//you can use the debug feature to see how the words and sentences are counted
		//-paul
    	this.c.parseArgs(new String[]{"-l", "3", "-d", ".","./samedirfile.txt"});
    	assertEquals("Checks if a number with only 2 decimal places is returned", 14.33, this.c.getAverageSentenceLength(), 0);
	}
	
	@Ignore
	@Test
	public void testGetAverageSentenceLength5() throws Exception{
		//Checks if the value returned is rounded up if digits after decimal is greater than 5
    	this.c.parseArgs(new String[]{"-l", "1", "-d", ".","./samedirfile.txt"});
    	assertEquals("Checks if the value returned is rounded up if greater than 5", 17.67, this.c.getAverageSentenceLength(), 0);
	}

	@Ignore
	@Test
	public void testGetAverageSentenceLength6() throws Exception{
		//Checks if the value returned is rounded down if digits after decimal is less than 5

    	this.c.parseArgs(new String[]{"-l", "2", "-d", ".","./samedirfile.txt"});
    	assertEquals("Checks if the value returned is rounded down if less than 5", 16.0, this.c.getAverageSentenceLength(), 0);
	}

	
	


}

