

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
		//test extra argument
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: Arguments invalid");
		this.c.parseArgs(new String[]{"simple.txt", "-l", "4", "extra" });
	}
	
	@Test
	public void testParseArgsBad15() {
		//test extra arguments
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: -l option invalid");
		this.c.parseArgs(new String[]{"-d", "-l"});
	}
	
	@Test
	public void testParseArgsBad16() {
		//test extra argument
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("ERROR: Arguments invalid");
		this.c.parseArgs(new String[]{"simple.txt", "-l", "4", "-d", "asdf", "extra" });
	}
	
	@Test
	public void testGetAverageSentenceLength1() throws Exception {
		//test default flag values
		this.c.parseArgs(new String[]{"./simple.txt"});
		assertEquals(3, this.c.getAverageSentenceLength(), 0.1);
	}
	
	@Test
	public void testGetAverageSentenceLength2() throws FileNotFoundException {
        //test empty file
		this.c.parseArgs(new String[]{"-d", ".", "./Emptyfile.txt"});
		assertEquals(0, this.c.getAverageSentenceLength(), 0);
	}
	
	@Test
	public void testGetAverageSentenceLength3() throws Exception {
		// Check other file extension, should also work
		this.c.parseArgs(new String[]{"./simple.pdf"});
		assertEquals(3, this.c.getAverageSentenceLength(), 0);
	}
	
	@Test
	public void testGetAverageSentenceLength4() throws Exception{
		// Check a slightly longer text file
		//c.setDebug(true);
    	this.c.parseArgs(new String[]{"./samedirfile.txt"});
    	assertEquals(5.5, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength5() throws Exception{
		// Check a slightly longer text file
		this.c.parseArgs(new String[]{"-l", "1", "-d", ".","./samedirfile.txt"});
		assertEquals(14, this.c.getAverageSentenceLength(), 0);
	}

	@Test
	public void testGetAverageSentenceLength6() throws Exception{
		// Check a slightly longer text file
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"-l", "2", "-d", ".","./samedirfile.txt"});
		assertEquals(12.67, this.c.getAverageSentenceLength(), 0);
    }

	@Test
	public void testGetAverageSentenceLength7() throws Exception{
		// Check a file with all short words
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./short_words.txt"});
		assertEquals(0, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength8() throws Exception{
		// Check delimiters other than .
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./special_delimiters1.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength9() throws Exception{
		// Check delimiters other than .
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./special_delimiters2.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength10() throws Exception{
		// Check delimiters other than .
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./special_delimiters3.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength11() throws Exception{
		// Check delimiters other than .
		//c.setDebug(true);
		this.c.parseArgs(new String[]{ "-d", "$%", "./special_delimiters4.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength12() throws Exception{
		// Check delimiters other than .
		//c.setDebug(true);
		this.c.parseArgs(new String[]{ "-d", "$%", "./special_delimiters5.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength13() throws Exception{
		// Check a large text file
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./large_file.txt"});
		assertEquals(10, this.c.getAverageSentenceLength(), 100);
    }
	
	@Test
	public void testGetAverageSentenceLength14() throws Exception{
		// Check a non-text file
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./image.jpg"});
		assertEquals(10, this.c.getAverageSentenceLength(), 10);
    }
	
	@Test
	public void testGetAverageSentenceLength15() throws Exception{
		// Check a non-text file with large lists of delimiters
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"-d", "@#$%@#$%@#%$qerqwerqwerasdfasfzxczxvafdasdfaasfdasas", "./image.jpg"});
		assertEquals(10, this.c.getAverageSentenceLength(), 10);
    }

	@Test
	public void testGetAverageSentenceLength16() throws Exception{
		// Check a real pdf file
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"./Project1Deliverable2.pdf"});
		assertEquals(10, this.c.getAverageSentenceLength(), 10);
    }
	
	@Test
	public void testGetAverageSentenceLength17() throws Exception{
		// Check a real pdf file with lots of delimiters
		//c.setDebug(true);
		this.c.parseArgs(new String[]{"-d", "qerasfasfasd", "./Project1Deliverable2.pdf"});
		assertEquals(10, this.c.getAverageSentenceLength(), 10);
    }

	public void testGetAverageSentenceLength18() throws Exception{
		// Check a very long characters length for -l word.
		this.c.parseArgs(new String[]{"-l", "100", "-d", ".", "./samedirfile.txt"});
		assertEquals(0, this.c.getAverageSentenceLength(), 0);
    }

	@Test
	public void testGetAverageSentenceLength19() throws Exception{
		// Check tab for word.
		this.c.parseArgs(new String[]{"-l", "3", "-d", ".", "./simple3-tab.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength20() throws Exception{
		// Check " " quote case  for word.
		this.c.parseArgs(new String[]{"-l", "3", "-d", ".", "./simple4.txt"});
		assertEquals(3, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength21() throws Exception{
		// Check no delimiter case  for word.
		this.c.parseArgs(new String[]{"-l", "3", "-d", "!", "./simple4.txt"});
		assertEquals(0, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength22() throws Exception{
		// Check 0 character  for word.
		this.c.parseArgs(new String[]{"-l", "0", "-d", ".", "./simple3-tab.txt"});
		assertEquals(2, this.c.getAverageSentenceLength(), 0);
    }
	
	@Test
	public void testGetAverageSentenceLength23() throws Exception{
		// Check delimiter as space.
		this.c.parseArgs(new String[]{"-l", "3", "-d", " ", "./simple.txt"});
		assertEquals(1, this.c.getAverageSentenceLength(), 0);
    }
}
