/**
 * 
 */
package test.WhiteBox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junitTestDemo.JUnitTestCaseExample;

/**
 * @author BJIT
 *
 */
public class WhiteBoxJunitTest {
	
	JUnitTestCaseExample obj = new JUnitTestCaseExample();  
    
    @Before
    public void setup()
    {
    	System.out.println("....Unit Test Started....");
    }
	
	@Test  
    public void testAdd() {  
        obj.add("Emma");  
        obj.add("Ronan");  
        obj.add("Antonio");  
        obj.add("Paul");  
        assertEquals("Adding 4 student to list", 2, obj.sizeOfStudent());
        //assertNotNull(obj.sizeOfStudent());
        
       }  
      
    @Test  
    public void testSize() {  
        obj.add("Emma");  
        obj.add("Ronan");  
        obj.add("Antonio");  
        assertEquals("Checking size of List", 3, obj.sizeOfStudent());  
    }  
      
    @Test  
    public void testRemove() {  
        obj.add("Antonio");  
        obj.add("Paul");  
        obj.remove("Paul");  
        assertEquals("Removing 1 student from list", 1, obj.sizeOfStudent());  
    }  
      
    @Test  
    public void testremoveAll() {  
        obj.removeAll();  
    }  
    
    @After
    public void tearDown()
    {
    	System.out.println("....Unit Test Ended....");
    }

}
