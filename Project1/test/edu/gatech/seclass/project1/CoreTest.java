/**
 * 
 */
package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
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
	    exception.expectMessage("The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"-l", "4", "-d", ";", "non_existant_file"});
	}
	
	@Test
	public void testParseArgsBadFileName2() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"non_existant_file"});
	}
	
	@Test
	public void testParseArgsBadFileName3() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("The file either does not exist or it is not a file");
		this.c.parseArgs(new String[]{"-d", ".", "non_existant_file"});
	}
	
	@Test
	public void testParseArgsValid1() {
		//test all valid args
		this.c.parseArgs(new String[]{"-d", ".", "./samedirfile.txt"});
		assertArrayEquals(".".toCharArray(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(3, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid2() {
		//test all valid args
		this.c.parseArgs(new String[]{"-l", "5", "./samedirfile.txt"});
		assertArrayEquals(Core.getDefaultDelimeters(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(5, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid3() {
		//test all valid args
		this.c.parseArgs(new String[]{"-l", "5", "-d", ".;", "./samedirfile.txt"});
		assertArrayEquals(".;".toCharArray(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(5, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid4() {
		//test all valid args
		this.c.parseArgs(new String[]{"-d", ".", "-l", "6", "./samedirfile.txt"});
		assertArrayEquals(".".toCharArray(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(6, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsValid5() {
		//test all valid args
		this.c.parseArgs(new String[]{"./samedirfile.txt"});
		assertArrayEquals(Core.getDefaultDelimeters(), this.c.getDelimeters());
		assertEquals("./samedirfile.txt", this.c.getFileName());
		assertEquals(3, this.c.getWordLengthLimit());
	}
	
	@Test
	public void testParseArgsBad1() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
		this.c.parseArgs(new String[]{"./samedirfile.txt", "-l", "4"});
	}
	
	@Test
	public void testParseArgsBad2() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("The -l option must be given with a valid positive number");
		this.c.parseArgs(new String[]{"-l", "a", "-d", ";", "non_existant_file"});
	}
	
	@Test
	public void testParseArgsBad3() {
		//test invalid file
		exception.expect(IllegalArgumentException.class);
	    //exception.expectMessage("The -l option must be given with a valid positive number");
		this.c.parseArgs(new String[]{"-l", "3", "-d", "non_existant_file"});
	}

}
