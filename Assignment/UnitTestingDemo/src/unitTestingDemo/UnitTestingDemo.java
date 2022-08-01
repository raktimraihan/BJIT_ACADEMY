/**
 * 
 */
package unitTestingDemo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import unitTestingDemo.blackBoxTesting.BlackBoxTestingModel;
import unitTestingDemo.whiteBoxTesting.WhiteBoxTesting;

/**
 * @author BJIT
 *
 */
public class UnitTestingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = JUnitCore.runClasses(BlackBoxTestingModel.class);  
		for (Failure fail : result.getFailures()) {  
			System.out.println(fail.toString());  
		}  
		if(result.wasSuccessful()) {
			System.out.println("Test result: "+result.wasSuccessful()); 
		}
		else System.out.println("Failed Cases: "+result.getFailureCount());
		
		
		Result resultOfWhiteBoxTesting = JUnitCore.runClasses(WhiteBoxTesting.class);
		
		for(Failure fail: resultOfWhiteBoxTesting.getFailures()) {
			System.out.println(fail.toString());
		}
		
		if(resultOfWhiteBoxTesting.wasSuccessful()) {
			System.out.println("Test result: "+resultOfWhiteBoxTesting.wasSuccessful());
		}
		else System.out.println("Test Case failed: "+ resultOfWhiteBoxTesting.getFailureCount());
		
		
	}

}
