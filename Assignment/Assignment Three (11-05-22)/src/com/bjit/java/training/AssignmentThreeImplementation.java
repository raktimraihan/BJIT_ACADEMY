package com.bjit.java.training;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AssignmentThreeImplementation {
	
	public static void main(String[] args) throws IOException {

		//make a new directory under src folder if the directory doean't exist
		File fileDirectory = new File("src//TextFiles");
		if(!fileDirectory.exists()){
			fileDirectory.mkdir();
			System.out.println("Directory Created Successfully. Path = "+fileDirectory.getAbsolutePath());
		}
		else System.out.println("Old Directory Exists.Path ="+fileDirectory.getAbsolutePath());
		
		//define fileName
		String fileName = "11548_raktim.txt";
		//create file in that path
		File textFile = new File(fileDirectory.getAbsolutePath()+"\\"+fileName);
		//check if file exist and create new File
		if(!textFile.exists()) {
			textFile.createNewFile();
			System.out.println("New File Created. File Name= "+textFile.getName());
		}
		else System.out.println("File Exists. File Name= "+textFile.getName());
		
		//Using BufferedWriter write texts into file.
		String content = "Hello this is Raktim. I am the author of this file. ";
		//create buffered writer object
		BufferedWriter textWriter = new BufferedWriter(new FileWriter(textFile));
		//write the content to the file
		textWriter.write(content);
		//initialize another to content
		content = "This file is written using Notepad.";
		//write another line 
		textWriter.newLine();
		textWriter.write(content);
		
		//print file path
		System.out.println("File path= "+textFile.getPath());
		
		//close and writer
		textWriter.close();
		System.out.println("File modified Successfully.");
		
		//read the text File using Buffered Reader
		BufferedReader textReader = new BufferedReader(new FileReader(textFile));
		
		//read from file and print into console
		System.out.println("Content from the file...");
		String line;
		while((line = textReader.readLine()) != null) {
			System.out.println(line);
		}
		textReader.close();

	}

}
