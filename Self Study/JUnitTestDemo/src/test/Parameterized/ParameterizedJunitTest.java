/**
 * 
 */
package test.Parameterized;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junitTestDemo.JUnitTestCaseExample;

/**
 * @author BJIT
 *
 */

@RunWith(Parameterized.class)
public class ParameterizedJunitTest {
	
	JUnitTestCaseExample obj = new JUnitTestCaseExample();  
	
	private String name;
	
	public ParameterizedJunitTest(String name)
	{
		this.name = name;
		
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> input()
	{
		return Arrays.asList(new Object[][] {{"Emma"},{"Rayan"},{"Thompson"}});
	}
	
	@Test
	public void testSize()
	{
		obj.add(name); 
		assertEquals("Checking size of List",1, obj.sizeOfStudent()); 
	}

}
