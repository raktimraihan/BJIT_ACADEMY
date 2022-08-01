package unitTestingDemo.blackBoxTesting;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unitTestingDemo.SystemUnderTest;
import unitTestingDemo.UnitTestingDemo;

public class BlackBoxTestingModel {
	
	SystemUnderTest systemUnderTest = new SystemUnderTest();
	
	@Before
	public void setup() {
		System.out.println("Unit test Started...Method: Black Box Testing.");
	}
	
	@Test
	public void testCase1() {
		System.out.println("Callig method increment by once. ");
		systemUnderTest.incrementOnce();
		assertEquals("Checking the value for increment once:  ", 1, systemUnderTest.getCounter());
	}
	
	@Test
	public void testCase2() {
		System.out.println("Callig method increment by value. ");
		systemUnderTest.incrementByValue(10);
		assertEquals("Checking value for increment by value 10: ", 5, systemUnderTest.getCounter());
	}
	
	@Test
	public void testCae1() {
		System.out.println("Testing increment by negative value.");
		systemUnderTest.incrementByValue(-5);
		assertEquals("Checking value: ", -5, systemUnderTest.getCounter());
	}
	
	@After
	public void tearDown() {
		System.out.println("....Unit testing Ended...");
	}

}
