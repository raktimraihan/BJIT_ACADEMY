/**
 * 
 */
package test.BlackBox;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junitTestDemo.JUnitTestCaseExample;

/**
 * @author BJIT
 *
 */
public class BlackBoxJunitTest {
	
	JUnitTestCaseExample obj = new JUnitTestCaseExample();  
	
	   @Before
	    public void setup()
	    {
	    	System.out.println("....Unit Test Started....");
	    }
	
	   @Test  
	    public void testadd1() {  
		  obj.add();   
	      assertEquals("Checking size of List", 1, obj.sizeOfStudent());  
	    }  
	  
	   @Test  
	    public void testadd2() {  
		  obj.add();   
	      assertEquals("Checking size of List", 2, obj.sizeOfStudent());  
	    } 
	  
	   @Test  
	    public void testadd3() {  
		  obj.add();   
	      assertEquals("Checking size of List", 3, obj.sizeOfStudent());  
	    } 
	 
	   @Test  
	    public void testadd4() {  
		  obj.add();   
	      assertEquals("Checking size of List", 4, obj.sizeOfStudent());  
	    } 
	  
	    @After
	    public void tearDown()
	    {
	    	System.out.println("....Unit Test Ended....");
	    }
}
