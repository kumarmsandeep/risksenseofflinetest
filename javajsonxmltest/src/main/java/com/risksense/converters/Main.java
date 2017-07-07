package com.risksense.converters;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		XMLJSONConverterI createXMLJSONConverter = ConverterFactory.createXMLJSONConverter();
		try {
			createXMLJSONConverter.convertJSONtoXML(new File(args[0]), new File(args[1]));
		} catch (IOException e) {
			System.err.println("Failed to generate XML File, because of [" + e.getMessage() + "]");
		}
	}
}
