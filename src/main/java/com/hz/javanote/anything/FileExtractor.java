package com.hz.javanote.anything;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileExtractor {
	
	
	public static void main(String args[]) throws IOException {
		File file = new File("C:\\workspace\\bugs\\2020082\\DellPTAgentResponse-4.log");
		File newFile = new File("C:\\\\workspace\\\\bugs\\\\2020082\\\\DellPTAgentResponse-4-new.log");
		
		if(newFile.exists()) {
			newFile.delete();
		}
		newFile.createNewFile();
		
		testExtract(file,newFile,"2017-12-10T","app34-04.localdomain.localDellPTAdapter");
	}

	public static void testExtract(File file, File newFile,String prefix,String keyword) throws IOException {
		FileWriter newFileWriter = new FileWriter(newFile);
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		
		int relatedLineCount=0;
		int lineCount=0;
		
		Boolean deleteFlag=true;
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				if (null != line && !line.isEmpty()) {
					if (line.startsWith(prefix)&&line.contains(keyword)) {
						newFileWriter.write(line+"\n");
						deleteFlag=false;
						lineCount=0;
					} else if(line.startsWith(prefix)){
						if(lineCount<relatedLineCount) {
							newFileWriter.write(line+"\n");
							lineCount++;
						}else {
							deleteFlag=true;
						}
					}else {
						if(deleteFlag) {
							if(lineCount<relatedLineCount) {
								newFileWriter.write(line+"\n");
								lineCount++;
							}
						}else {
							newFileWriter.write(line+"\n");
						}
					}
				}
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
	}

}
