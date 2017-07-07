package com.risksense.converters;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map.Entry;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class XMLJSONConverter implements XMLJSONConverterI {

	@Override
	public void convertJSONtoXML(File json, File xml) throws IOException {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(new FileReader(json), JsonElement.class);
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xtw = null;
		try {
			xtw = xof.createXMLStreamWriter(new FileWriter(xml));
			// xtw.writeStartDocument();
			addElement(jsonElement, xtw);
			// xtw.writeEndDocument();
			xtw.flush();
			xtw.close();
		} catch (XMLStreamException e) {
			throw new IOException(e);
		}
	}
	
	private void writeStringToFile(String content, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String convertJSONTOXML(String json) throws IOException {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xtw = null;
		try {
			StringWriter stream = new StringWriter();
			xtw = xof.createXMLStreamWriter(stream);
			// xtw.writeStartDocument();
			addElement(jsonElement, xtw);
			// xtw.writeEndDocument();
			xtw.flush();
			xtw.close();
			return stream.toString();
		} catch (XMLStreamException e) {
			throw new IOException(e);
		}
	}

	private void addElement(JsonElement jsonElement, XMLStreamWriter xtw) throws XMLStreamException {
		addElement(null, jsonElement, xtw);
	}

	private void addElement(String attributeName, JsonElement jsonElement, XMLStreamWriter xtw)
			throws XMLStreamException {

		if (jsonElement.isJsonNull()) {
			xtw.writeEmptyElement("null");
			writeAttribute(attributeName, xtw);
		} else if (jsonElement.isJsonPrimitive()) {
			JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
			String tagName = null;
			if (jsonPrimitive.isBoolean()) {
				tagName = "boolean";
			} else if (jsonPrimitive.isNumber()) {
				tagName = "number";
			} else if (jsonPrimitive.isString()) {
				tagName = "string";
			}
			if (tagName != null && tagName.length() > 0) {
				xtw.writeStartElement(tagName);
				writeAttribute(attributeName, xtw);
				xtw.writeCharacters(jsonElement.getAsString());
				xtw.writeEndElement();
			}
		} else if (jsonElement.isJsonArray()) {
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			xtw.writeStartElement("array");
			writeAttribute(attributeName, xtw);
			for (int i = 0; i < jsonArray.size(); i++) {
				addElement(jsonArray.get(i), xtw);
			}
			xtw.writeEndElement();
		} else if (jsonElement.isJsonObject()) {
			xtw.writeStartElement("object");
			writeAttribute(attributeName, xtw);
			for (Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
				addElement(entry.getKey(), entry.getValue(), xtw);
			}
			xtw.writeEndElement();
		}
	}

	private void writeAttribute(String attributeName, XMLStreamWriter xtw) throws XMLStreamException {
		if (attributeName != null && attributeName.length() > 0) {
			xtw.writeAttribute("name", attributeName);
		}
	}

}
