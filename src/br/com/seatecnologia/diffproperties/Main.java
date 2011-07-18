package br.com.seatecnologia.diffproperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String file1 = args[0];
		String file2 =  args[1];
		
		System.out.println("Original: "+file1 + "\nCompared to: "+ file2+"\n");
		
		Properties p1 = loadProperties(file1), 
		p2 = loadProperties(file2);
		Enumeration<String> pn1 = (Enumeration<String>) p1.propertyNames();
		for (String key = pn1.nextElement(); pn1.hasMoreElements();	key = pn1.nextElement()) {
			String p1value = p1.getProperty(key);
			String p2value = p2.getProperty(key);
			if (p2value == null) {
				System.out.println("-"+key+" = "+p1value+"\n");
			} else if (!p1value.equals(p2value)) {
				System.out.println("-"+key+" = "+p1value);
				System.out.println("+"+key+" = "+p2value+"\n");
			}

		}
		Enumeration<String> pn2 = (Enumeration<String>) p2.propertyNames();
		for (String key = pn2.nextElement(); pn2.hasMoreElements();	key = pn2.nextElement()) {
			String p1value = p1.getProperty(key);
			String p2value = p2.getProperty(key);
			if (p1value == null) {
				System.out.println("+"+key+" = "+p2value+"\n");
			}
		}
	}

	private static Properties loadProperties(String filename) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(filename));
		return properties;
	}

}
