/**
 * 
 */
package junitTestDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BJIT
 *
 */
public class JUnitTestCaseExample {
	
	private List<String> students = new ArrayList<String>();  
	

    public void remove(String name) {  
        students.remove(name);  
    }  
      
    public void add(String name) {  
        students.add(name);
         
    }  
    
    public void add()
    {
    	students.add("Emma");
    	students.add("Ronan");  
        students.add("Antonio");
    }
      
    public void removeAll(){  
        students.clear();  
    }  
      
    public int sizeOfStudent() {  
        return students.size();  
    } 

}
