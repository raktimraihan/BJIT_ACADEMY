package com.bjit.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ExceptionHandling {
	public static int divide(int[] array, int index) {
		try {
			System.out.println("\nFirst try block in divide() entered");
			array[index + 2] = array[index]/array[index + 1];
			System.out.println("Code at end of first try block in divide()");
			// return array[index + 2];
		} catch(ArithmeticException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Index-out-of-bounds exception caught in divide()\n" 
									+"\nMessage in exception object:\n\t" + e.getMessage());
			System.err.println("\nStack trace output:\n");
			e.printStackTrace();
			System.out.println("\nEnd of stack trace output\n");
		} finally {
			System.err.println("finally clause in divide()");}
			System.out.println("Executing code after try block in divide()");
			return array[index + 2];
		}    
        
        public static void main(String[] args) throws IOException{
            
            int[] numberArr = {10,5,0};
            int result = ExceptionHandling.divide(numberArr, 0);
            System.out.println(result);
            
            FileInputStream sourceStream = null;
            FileOutputStream targetStream = null;
      
            try {
                sourceStream = new FileInputStream("src/input.txt");
                targetStream = new FileOutputStream("src/Output-File.txt");
      
                // Reading source file and writing
                // content to target file byte by byte
                int temp;
                while ((temp = sourceStream.read())!= -1)
                    targetStream.write((byte)temp);
            }
            finally {
                if (sourceStream != null)
                    sourceStream.close();
                if (targetStream != null)
                    targetStream.close();
            }
            
         // InputStreamReader class to read input
            InputStreamReader inp = null;
      
            // Storing the input in inp
            inp = new InputStreamReader(System.in);
      
            System.out.println("Enter characters, "
                               + " and '0' to quit.");
            char c;
            
//            for(char c = (char)inp.read(); c != '0'; c=(char)inp.read())
            do {
                c = (char)inp.read();
                System.out.println(c);
            } while (c != '0');
            
            char[] in = new char[50]; // to store input
    		int size = 0;
    		try {
    			File file = new File("src/fileWrite2.txt");
    			file.createNewFile();
    			FileWriter fw =new FileWriter(file); 
    				// create an actual file// & a FileWriter obj
    			PrintWriter pw = new PrintWriter(file);
    			pw.println("howdy"); // write the data
    			pw.println("folks"); 	
    			pw.flush(); 	// flush before closing
    			pw.close(); 	// close file when done

    			FileReader fr =new FileReader(file); 
    					// create a FileReader object
    			BufferedReader br =new BufferedReader(fr);
    			String s;
    			while( (s = br.readLine()) != null) // read data
    			System.out.println(s);
    			br.close();	 // again, always close
    		} catch(IOException e) { }

        }

}
