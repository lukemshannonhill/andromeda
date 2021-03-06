/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.xml.gen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.sc2mod.andromeda.notifications.Message;
import com.sc2mod.andromeda.notifications.MessageSeverity;
import com.sc2mod.andromeda.notifications.SourcePosition;
import com.sc2mod.andromeda.parsing.Source;

public class ResultXMLWriter {

	
	public void genXML(List<Message> messages, File xmlFile){
		int[] numMessages = new int[3];
		for(Message m: messages){
			numMessages[m.getSeverity()]++;
		}
		boolean errors = numMessages[MessageSeverity.ERROR]>0;
		
		XMLWriter writer;
		try {
			writer = new XMLWriter(xmlFile);
		} catch (XMLStreamException e) {
			throw new XMLError(e);
		} catch (IOException e) {
			throw new XMLError(e);
		}
		
		writer.writeStartDocument();
		writer.writeStartElement("andromedaParseResult");
		writer.writeAttribute("successful", errors?"false":"true");
		
			writer.writeStartElement("messages");
			writer.writeAttribute("num",""+messages.size());
			writer.writeAttribute("information",String.valueOf(numMessages[MessageSeverity.INFO]));
			writer.writeAttribute("warning",String.valueOf(numMessages[MessageSeverity.WARNING]));
			writer.writeAttribute("error",String.valueOf(numMessages[MessageSeverity.ERROR]));
			
			for(Message m: messages){
				writer.writeStartElement("message");
				writer.writeAttribute("severity", MessageSeverity.getName(m.getSeverity()));
				writer.writeAttribute("text", m.getText());
				for(SourcePosition s: m.getPositions()){
					writer.writeEmptyElement("location");
					Source a = s.getSource();
					writer.writeAttribute("type", a.getTypeName());
					writer.writeAttribute("name", a.getName());
					writer.writeAttribute("line", String.valueOf(s.getLine()));
					writer.writeAttribute("column", String.valueOf(s.getColumn()));
					writer.writeAttribute("length", String.valueOf(s.getLength()));
					writer.writeAttribute("offset", String.valueOf(s.getFileOffset()));
					writer.writeAttribute("path", a.getFullPath());
				}
				writer.writeEndElement();
			}
		
			writer.writeEndElement();
		writer.writeEndElement();	
		writer.writeEndDocument();
		try {
			writer.close();
		} catch (XMLStreamException e) {
			throw new XMLError(e);
		}
	}
}
