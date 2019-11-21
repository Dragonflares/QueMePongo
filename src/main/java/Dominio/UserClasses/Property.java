package Dominio.UserClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
	static String path = "properties.properties";

	public Property (String path) {
		Property.path = path;
	}

	public void changePath(String newPath) {		
		Property.path= newPath;		
	}

	public static String getSpecifiedProperty(String property) {

		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(getPath());
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		try {
			pro.load(in);
		} catch (IOException e) {
			//e.printStackTrace();
		}
		String finalProperty = pro.getProperty(property); 
	
		return finalProperty;

	}

	private static String getPath() {
		return path;
	}

	public void setPath(String path) {
		Property.path = path;
	}

}



