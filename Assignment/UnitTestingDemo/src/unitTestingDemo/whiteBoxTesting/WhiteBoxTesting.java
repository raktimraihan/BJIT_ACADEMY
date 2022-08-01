package unitTestingDemo.whiteBoxTesting;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unitTestingDemo.SystemUnderTest;

public class WhiteBoxTesting {
	
	SystemUnderTest systemUnderTest = new SystemUnderTest();
	
	@Before
	public void setup() {
		System.out.println("White Box testing Begun.");
	}
	
	@Test
	public void testCae1() {
		System.out.println("Testing increment once and increment by negative value.");
		systemUnderTest.incrementOnce();
		systemUnderTest.incrementByValue(-5);
		assertEquals("Checking value: ", 1, systemUnderTest.getCounter());
	}
	
	@Test
	public void testCae2() {
		System.out.println("Testing increment once twice and increment by positive value.");
		systemUnderTest.incrementOnce();
		systemUnderTest.incrementOnce();
		systemUnderTest.incrementByValue(5);
		assertEquals("Checking value: ", 6, systemUnderTest.getCounter());
	}
	
	@Test
	public void testCae3() {
		System.out.println("Testing increment once twice and increment by positive value.");
		systemUnderTest.incrementOnce();
		systemUnderTest.incrementOnce();
		systemUnderTest.incrementByValue(5);
		assertEquals("Checking value: ", 7, systemUnderTest.getCounter());
	}
	
	@After
	public void tearDown() {
		System.out.println("White Box testing Ended.");
	}
}
