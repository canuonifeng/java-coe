package com.codeages.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class IoTest {
	@Test
	public void testInputOutput() throws Exception {
		File file = new File("/Users/fengni/java/workspace/java-coe/src/main/java/com/codeages/io/IoTest.java");
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
		File file = new File("/Users/fengni/java/workspace/java-coe/src/main/java/com/codeages/io/IoTest.java");
		InputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
		
		OutputStream outputStream = System.out;
		for(int i = bufferedInputStream.read(); i >= 0; i = inputStream.read()) {
			outputStream.write(i);
		}
		
		outputStream.flush();
		inputStream.close();
		outputStream.close();
	}
	
	@Test
	public void testOutputStream() throws Exception {
		File file = new File("/Users/fengni/java/workspace/java-coe/src/main/java/com/codeages/io/IoTest.java");
		InputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
		
		File newFile = new File("/Users/fengni/java/workspace/java-coe/src/main/java/com/codeages/io/IoTest1.java");
		OutputStream outputStream = new FileOutputStream(newFile);
		for(int i = bufferedInputStream.read(); i >= 0; i = inputStream.read()) {
			outputStream.write(i);
		}
		
		outputStream.flush();
		inputStream.close();
		outputStream.close();
		
	}
}
