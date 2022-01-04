package edu.kh.semi.common;

public class XSS {
	
	//XSS 방지 메소드 
	public static String replaceParameter(String parameter) {
		if(parameter != null) {
			parameter = parameter.replaceAll("&", "&amp;");
			parameter = parameter.replaceAll("<", "&lt;");
			parameter = parameter.replaceAll(">", "&gt;");
			parameter = parameter.replaceAll("\"", "&quot;");
		}
		
		return parameter;
	}
}
