package com.codeages.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.junit.Test;

import com.codeages.annotation.Athlete;

import junit.framework.Assert;

public class IoTest {
	@Test
	public void testInputOutput() throws Exception {
		File file = new File(getWebClassesPath()+"../../src/main/java/com/codeages/io/IoTest.java");
		InputStream inputStream = new FileInputStream(file);
		
		OutputStream outputStream = System.out;
		for(int i = inputStream.read(); i >= 0; i = inputStream.read()) {
			outputStream.write(i);
		}
		
		outputStream.flush();
		inputStream.close();
		outputStream.close();
	}
	
	@Test
	public void testBufferedInputStream() throws Exception {
		File file = new File(getWebClassesPath()+"../../src/main/java/com/codeages/io/IoTest.java");
		InputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
		
		OutputStream outputStream = System.out;
		for(int i = bufferedInputStream.read(); i >= 0; i = bufferedInputStream.read()) {
			outputStream.write(i);
		}
		
		outputStream.flush();
		inputStream.close();
		outputStream.close();
	}
	
	@Test
	public void testOutputStream() throws Exception {
		File file = new File(getWebClassesPath()+"../../src/main/java/com/codeages/io/IoTest.java");
		InputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
		
		File newFile = new File(getWebClassesPath()+"../IoTest1.java");
		OutputStream outputStream = new FileOutputStream(newFile);
		for(int i = bufferedInputStream.read(); i >= 0; i = inputStream.read()) {
			outputStream.write(i);
		}
		
		outputStream.flush();
		inputStream.close();
		outputStream.close();
		
	}
	
	@Test
	public void testObjectOutputStream() throws Exception {
		Athlete athlete = new Athlete();
		athlete.setName("张三");
		
		FileOutputStream fileOutputStream = new FileOutputStream(new File(getWebClassesPath()+"../IoTest1.java"));
		ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
		outputStream.writeObject(athlete);
		outputStream.close();
		fileOutputStream.close();
		
		FileInputStream fileInputStream = new FileInputStream(new File(getWebClassesPath()+"../IoTest1.java"));
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Athlete athlete2 = (Athlete)objectInputStream.readObject();
		Assert.assertEquals(athlete.getName(), athlete2.getName());
		objectInputStream.close();
		fileInputStream.close();
	}
	
	@Test
	public void testReader() throws Exception {
		File file = new File(getWebClassesPath()+"../../src/main/java/com/codeages/io/IoTest.java");
		File file1 = new File(getWebClassesPath()+"../IoTest1.java");
		FileReader reader = new FileReader(file);
		Writer writer = new FileWriter(file1);
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		
		String str="";
		while((str = bufferedReader.readLine()) != null) {  
			System.out.print(str);
			bufferedWriter.write(str);  
		}
		
		bufferedReader.close();
		bufferedWriter.close();
		reader.close();
		writer.close();
	}
	
	public String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		return path;
	}
}
