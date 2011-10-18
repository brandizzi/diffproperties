package br.com.seatecnologia.diffproperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		if (args.length < 2) {
			File moduleFile;
			try {
				moduleFile = new File(Main.class.getProtectionDomain()
						.getCodeSource().getLocation().toURI());
				System.err.println("Usage:\n" +
						"    java -jar " + moduleFile.getName() + " <properties-file-1> <properties-file-2>");
			} catch (URISyntaxException e) {
				System.err.println("Unexpected error while getting name of jar file: "+e.getLocalizedMessage());
			}
			return;
		}
		String file1 = args[0];
		String file2 =  args[1];
		
		System.out.println("Original: "+file1 + "\nCompared to: "+ file2+"\n");
		
		Properties p1, p2;
		try {
			p1 = loadProperties(file1);
			p2 = loadProperties(file2);
		} catch (IOException e) {
			System.err.println("Error opening a file: " + e.getLocalizedMessage());
			return;
		}
		Enumeration<String> pn1 = (Enumeration<String>) p1.propertyNames();
		while (pn1.hasMoreElements()) {
			String key = pn1.nextElement(); 
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
		while (pn2.hasMoreElements()) {
			String key = pn2.nextElement(); 
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
