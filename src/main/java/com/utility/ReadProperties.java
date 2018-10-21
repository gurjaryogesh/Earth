package com.utility;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ReadProperties {
	
	public String getProperties(String key) {
		StartUpInitilize initilize = new StartUpInitilize();
		Properties prop = new Properties();
		String value = null;
		try {
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\" + initilize.getEnvName() + ".properties";
			System.out.println("path : "+path);
			File f = new File(path);
			FileReader reader = new FileReader(f);
			prop.load(reader);
			System.out.println("prop.getProperty(+"+key+"+) : "+prop.getProperty(key));
			value = prop.getProperty(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}

}