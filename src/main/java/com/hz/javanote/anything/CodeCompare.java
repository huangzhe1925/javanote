package com.hz.javanote.anything;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class CodeCompare {

	public static Map<String, CodeDTO> csvDBMap;
	public static Map<String, CodeDTO> restCommonUIMap;

	public static void main(String args[]) throws IOException {
		File csvDB = new File("C:\\workspace\\bugs\\2011211\\mystic_event_code.csv");
		File restCommonUI = new File("C:\\workspace\\bugs\\2011211\\RestCommon.properties");
//		File newcsvDB = new File("C:\\workspace\\bugs\\2011211\\new_mystic_event_code.csv");
//		if(newcsvDB.exists()) {
//			newcsvDB.delete();
//		}
//		
//		newcsvDB.createNewFile();
		
		getCSVDBMap(csvDB);
		getRestCommonUIMap(restCommonUI);
		
//		findAndReplace(csvDB,newcsvDB);
		
		int count=0;
		
		for(Map.Entry<String, CodeDTO> csvEntry:csvDBMap.entrySet()) {
			if(restCommonUIMap.containsKey(csvEntry.getKey())) {
				CodeDTO restCodeDTO=restCommonUIMap.get(csvEntry.getKey());
				if(!restCodeDTO.getMessage().equals(csvEntry.getValue().getMessage())) {
					System.out.println("KEY : "+ csvEntry.getKey()+"  CSV MESSAGE : "+csvEntry.getValue().getMessage()+"  REST MESSAGE : "+restCodeDTO.getMessage());
					count++;
				}else {
					
				}
				
			}else {
				System.out.println("Didnt find CSV key "+csvEntry.getKey()+" in RESTCommon");
			}
		}
		
		System.out.println("different "+count);
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		count=0;
		
		for(Map.Entry<String, CodeDTO> restEntry:restCommonUIMap.entrySet()) {
			if(csvDBMap.containsKey(restEntry.getKey())) {
				CodeDTO csvCodeDTO=csvDBMap.get(restEntry.getKey());
				if(!csvCodeDTO.getMessage().equals(restEntry.getValue().getMessage())) {
					System.out.println("KEY : "+ restEntry.getKey()+"  CSV MESSAGE : "+csvCodeDTO.getMessage()+"  REST MESSAGE : "+restEntry.getValue().getMessage());
					count++;
				}else {
					
				}
			}else {
				System.out.println("Didnt find rest key "+restEntry.getKey()+" : "+restEntry.getValue().getMessage()+" in csvCommon ");
			}
		}
		
		System.out.println("different "+count);
		System.out.println();
		System.out.println(restCommonUIMap.size());
		System.out.println(csvDBMap.size());
		
	}
	
	public static void findAndReplace(File csvFile,File newcsvFile) throws IOException {
		
		FileWriter newcsvFileWriter = new FileWriter(newcsvFile);
		
		LineIterator it = FileUtils.lineIterator(csvFile, "UTF-8");
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				if (null != line && !line.isEmpty()) {
					if(line.contains("\"")) {
						String msg=line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
						line=line.replaceFirst("\".*\"", "");
//						System.out.println(line+" "+msg);
						String attrs[] = line.split(",");
						if (!csvDBMap.containsKey(attrs[0])) {
							csvDBMap.put(attrs[0], new CodeDTO(attrs[0], msg, attrs[2], attrs[3]));
						} else {
							System.out.println(attrs[0] + " is duplicated in CSV DB");
						}
						
					}else {
						String attrs[] = line.split(",");
						if (!csvDBMap.containsKey(attrs[0])) {
							csvDBMap.put(attrs[0], CodeDTO.convertToCodeDTO(attrs));
						} else {
							System.out.println(attrs[0] + " is duplicated in CSV DB");
						}
					}
					
				}
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
	}

	public static void getCSVDBMap(File file) throws IOException {
		if (null == csvDBMap) {
			csvDBMap = new LinkedHashMap<>();
		}
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				if (null != line && !line.isEmpty()) {
					
					if(line.contains("\"")) {
						String msg=line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
						line=line.replaceFirst("\".*\"", "");
//						System.out.println(line+" "+msg);
						String attrs[] = line.split(",");
						if (!csvDBMap.containsKey(attrs[0])) {
							csvDBMap.put(attrs[0], new CodeDTO(attrs[0], msg, attrs[2], attrs[3]));
						} else {
							System.out.println(attrs[0] + " is duplicated in CSV DB");
						}
					}else {
						String attrs[] = line.split(",");
						if (!csvDBMap.containsKey(attrs[0])) {
							csvDBMap.put(attrs[0], CodeDTO.convertToCodeDTO(attrs));
						} else {
							System.out.println(attrs[0] + " is duplicated in CSV DB");
						}
					}
					
				}
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
	}

	public static void getRestCommonUIMap(File file) throws IOException {
		if (null == restCommonUIMap) {
			restCommonUIMap = new LinkedHashMap<>();
		}
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		Pattern pattern=Pattern.compile("MYSTIC.*=");
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				String code=null;
				Matcher m=pattern.matcher(line);
				if(m.find()) {
					code=m.group();
					code=code.substring(0, code.length()-1);
				}
				if(!restCommonUIMap.containsKey(code)) {
					CodeDTO codeDTO=new CodeDTO();
					codeDTO.setCode(code);
					codeDTO.setMessage(line.substring(line.indexOf("=")+1, line.length()));
					restCommonUIMap.put(code, codeDTO);
				}else {
					System.out.println(code + " is duplicated in REST Common");
				}
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
	}

}

class CodeDTO {
	@Override
	public String toString() {
		return "CodeDTO [code=" + code + ", message=" + message + ", topic=" + topic + ", YN=" + YN + "]";
	}

	private String code;
	private String message;
	private String topic;
	private String YN;

	public static CodeDTO convertToCodeDTO(String[] attr) {
		CodeDTO result = null;
		if (null == attr) {
			return null;
		}
		if (attr.length == 4) {
			result = new CodeDTO(attr[0], attr[1], attr[2], attr[3]);
		} else {
			System.out.println("ERROR WITH LINE : " + Arrays.toString(attr));
		}
		return result;
	}

	public CodeDTO() {

	}

	public CodeDTO(String code, String message, String topic, String yN) {
		super();
		this.code = code;
		this.message = message;
		this.topic = topic;
		this.YN = yN;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getYN() {
		return YN;
	}

	public void setYN(String yN) {
		YN = yN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeDTO other = (CodeDTO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
