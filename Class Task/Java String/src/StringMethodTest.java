import java.util.*;

public class StringMethodTest {
	
	
	public static void main(String[] args) {
		String str = " Hello World this is r123";
		
		char[] ch = {'c', 'h','r'};
		
		String str_1 = new String(ch);
		
		String str_2 = new String(str);
		
		System.out.println(str+" "+str_1+" "+str_2);
		
		System.out.println("Char at: 2 is: "+str.charAt(1)+" Length: "+str.length());
		System.out.println("After trimming str:"+str.trim());
		str = str.trim();
		System.out.println("COncat str with str_1: "+str.concat(" "+str_1));
		str = str.concat(" "+str_1);
		
		System.out.println("Substring of str from index 0 to 5: "+str.substring(0,5));
		
		String[] arrayOfString = str.split(" ");
		
		System.out.println("After spliting str at each space:");
		for(int i=0; i<arrayOfString.length; i++) {
			System.out.println(arrayOfString[i]);
		}
		
		System.out.println("Join hypen after spliting: "+String.join("-", arrayOfString));
		System.out.println("Replacing a with e: "+str.replace('o', 'O'));
		
		//String valueString = 
		
	}

}
