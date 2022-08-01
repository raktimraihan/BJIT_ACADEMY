/**
 * 
 */
package unitTestingDemo;

/**
 * @author BJIT
 *
 */
public class SystemUnderTest {
	
	private int counter = 0;
	 
    private void increment(){
	      counter++;
	}

	public int getCounter() {
		return counter;
	}

	public void incrementOnce() {
		increment();
	}
	
	public void incrementByValue(int value) {
		for(int i=0; i<value; i++) {
			increment();
		}
	}
    
    

}
