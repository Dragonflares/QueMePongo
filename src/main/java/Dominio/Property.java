package Dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

	
	
	String path;
	
	
	public Property (String path) {
		
	
		this.path = path;
	}
	
	
	public void changePath(String newPath) {
		
		
		this.path= newPath;
		
	}
	
	
	
	public String getSpecifiedProperty(String property) {
	
	Properties pro = new Properties();
	FileInputStream in = null;
	try {
		in = new FileInputStream(this.path);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		pro.load(in);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	// getting values from property file
	String finalProperty = pro.getProperty(property);//key value in prop file 
//	String password = pro.getProperty("passwordv3");//eg. username="zub"
	//String delimiter = ",";                         //password="abc"
//	temp1=username.split(delimiter);
//	temp2=password.split(delimiter);
//	
	return finalProperty;
	
	}
	
}



