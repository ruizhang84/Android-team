package edu.gatech.seclass.project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoreTestInstructors {

    private String dir;
    private PrintStream stdout;
    private PrintStream stderr;
    private ByteArrayOutputStream baosout;
    private ByteArrayOutputStream baoserr;

    @Before
    public void setUp() throws Exception {
        dir = new String("DB" + File.separator);
        baosout = new ByteArrayOutputStream();
        baoserr = new ByteArrayOutputStream();
        PrintStream psout = new PrintStream(baosout);
        PrintStream pserr = new PrintStream(baoserr);
        stdout = System.out;
        stderr = System.err;
//        Core.stdout = stdout;
//        Core.stderr = stderr;
        System.setOut(psout);
        System.setErr(pserr);
    }

    @After
    public void tearDown() throws Exception {
        baosout=null;
        baoserr=null;
        System.setOut(stdout);
        System.setErr(stderr);
        stdout=null;
        stderr=null;
    }

    @Test
    public void testComputeAverageSentenceLength1() {
    	String[] args = new String[1];
    	args[0] = dir+"multi.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(5.5, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLength2() {
    	String[] args = new String[1];
    	args[0] = dir+"essay.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(8.21, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLength3() {
    	String[] args = new String[3];
    	args[0] = "-l";
    	args[1] = "5";
    	args[2] = dir+"essay.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(5.05, Double.parseDouble(result), 0);
    }
    
    @Test
    public void testComputeAverageSentenceLength4() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = "/|";
    	args[2] = "-l";
    	args[3] = "1";
    	args[4] = dir+"numbers.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(2.2, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLengt5() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = ".";
    	args[2] = "-l";
    	args[3] = "1";
    	args[4] = dir+"case1.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
    	assertEquals(7, Double.parseDouble(result), 0);
    }
    
    @Test
    public void testComputeAverageSentenceLengt6() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = ".?!";
    	args[2] = "-l";
    	args[3] = "2";
    	args[4] = dir+"case2.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(4, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLengt7() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = ".";
    	args[2] = "-l";
    	args[3] = "3";
    	args[4] = dir+"case3.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(4, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLengt8() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = ".";
    	args[2] = "-l";
    	args[3] = "2";
    	args[4] = dir+"case4.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(3.33, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLengt9() {
    	String[] args = new String[5];
    	args[0] = "-d";
    	args[1] = ".";
    	args[2] = "-l";
    	args[3] = "1";
    	args[4] = dir+"case4.txt";
    	WC.main(args);
    	System.out.flush();
    	String result=baosout.toString().trim();
        assertEquals(3.67, Double.parseDouble(result), 0);
    }

    @Test
    public void testComputeAverageSentenceLengtA() {
    	String[] args = new String[5];
    	args[0] = dir+"case999.txt";
    	WC.main(args);
    	System.err.flush();
    	String result=baoserr.toString().trim();
        assertTrue((result.toLowerCase().startsWith("err")));
    }

    @Test
    public void testComputeAverageSentenceLengtB() {
    	String[] args = new String[1];
    	args[0] = "-d";
    	WC.main(args);
    	System.err.flush();
    	String result=baoserr.toString().trim();
        assertTrue((result.toLowerCase().startsWith("err")));
    }

    @Test
    public void testComputeAverageSentenceLengtC() {
    	String[] args = new String[1];
    	args[0] = "-l";
    	WC.main(args);
    	System.err.flush();
    	String result=baoserr.toString().trim();
    	assertTrue((result.toLowerCase().startsWith("err")));
    }
}
