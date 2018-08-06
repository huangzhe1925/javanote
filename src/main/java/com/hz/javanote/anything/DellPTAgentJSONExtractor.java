package com.hz.javanote.anything;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class DellPTAgentJSONExtractor {

	public static void main(String args[]) throws IOException {
		File file = new File("C:\\workspace\\logs\\EMC-7505900453\\"
				+ "VxRail_Support_Bundle_2017_12_27_22_34_54\\mystic_manager_data_collection_2017-12-27_22_34_33\\logs\\mystic\\DellPTAgentResponse.log");

		//
		// File newFile = new
		// File("C:\\\\workspace\\\\bugs\\\\2020082\\\\DellPTAgentResponse-4-new.log");
		//
		// if(newFile.exists()) {
		// newFile.delete();
		// }
		// newFile.createNewFile();

		extractToFiles(file);
	}

	public static void extractToFiles(File file) throws IOException {

		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		String filePrefix = "Dec ";
		String extractedFolder = file.getParentFile().getAbsolutePath() + "\\extractedJson";
		File jsonFolderFile = new File(extractedFolder);
		if (!jsonFolderFile.exists()) {
			jsonFolderFile.mkdir();
		}
		File newFile = null;
		FileWriter newFileWriter = null;
		Boolean writeFlag = Boolean.FALSE;

		Integer fileNumber = 1;

		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				if (null != line && !line.isEmpty()) {
					if (line.startsWith(filePrefix)&&line.contains("DellPTAdapter.refreshHardwareInfo:196")) {
						if(null!=newFileWriter) {
							newFileWriter.flush();
							newFileWriter.close();
						}
						newFile = new File(jsonFolderFile.getAbsolutePath() + "\\DellPTA_" + fileNumber + ".log");
						if(!newFile.exists()) {
							newFile.createNewFile();
						}
						fileNumber++;
						
						newFileWriter = new FileWriter(newFile);
					} else if (line.startsWith("{")) {
						writeFlag = Boolean.TRUE;
						if (null != newFileWriter) {
							newFileWriter.write(line+"\n");
							
						}
					} else if (line.startsWith("}")) {
						writeFlag = Boolean.FALSE;
						if (null != newFileWriter) {
							newFileWriter.write(line+"\n");
							
						}
					} else {
						if (writeFlag && null != newFileWriter) {
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
