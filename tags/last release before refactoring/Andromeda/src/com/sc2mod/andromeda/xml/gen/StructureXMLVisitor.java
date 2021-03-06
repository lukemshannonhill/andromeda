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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sc2mod.andromeda.parsing.AndromedaFileInfo;
import com.sc2mod.andromeda.parsing.Source;
import com.sc2mod.andromeda.parsing.SourceEnvironment;
import com.sc2mod.andromeda.program.Options;
import com.sc2mod.andromeda.syntaxNodes.AccessorDeclaration;
import com.sc2mod.andromeda.syntaxNodes.AndromedaFile;
import com.sc2mod.andromeda.syntaxNodes.ClassDeclaration;
import com.sc2mod.andromeda.syntaxNodes.EnrichDeclaration;
import com.sc2mod.andromeda.syntaxNodes.FieldDeclaration;
import com.sc2mod.andromeda.syntaxNodes.FunctionDeclaration;
import com.sc2mod.andromeda.syntaxNodes.GlobalVarDeclaration;
import com.sc2mod.andromeda.syntaxNodes.IncludedFile;
import com.sc2mod.andromeda.syntaxNodes.MethodDeclaration;
import com.sc2mod.andromeda.syntaxNodes.StructDeclaration;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;
import com.sc2mod.andromeda.syntaxNodes.VisitorAdaptor;
import com.sc2mod.andromeda.environment.Function;
import com.sc2mod.andromeda.environment.Method;
import com.sc2mod.andromeda.environment.Visibility;
import com.sc2mod.andromeda.environment.types.Class;
import com.sc2mod.andromeda.environment.types.Enrichment;
import com.sc2mod.andromeda.environment.variables.AccessorDecl;
import com.sc2mod.andromeda.environment.variables.FieldDecl;
import com.sc2mod.andromeda.environment.variables.LocalVarDecl;
import com.sc2mod.andromeda.environment.variables.NonParamDecl;
import com.sc2mod.andromeda.environment.variables.ParamDecl;

public class StructureXMLVisitor extends VisitorAdaptor{

	
	private XMLWriter writer;
	private SourceEnvironment env;
	private boolean inRecord;
	private Options options;
	
	public StructureXMLVisitor(Options options){
		this.options = options;
	}
	
	public void genXml(SourceEnvironment env, File xmlFile, AndromedaFile code) throws XMLStreamException, IOException{
		writer = new XMLWriter(xmlFile);
		this.env = env;
		inRecord = false;
		
		writer.writeStartDocument();
		writer.writeStartElement("andromedaStructure");
		code.getContent().childrenAccept(this);
		writer.writeEndElement();
		writer.writeEndDocument();
	
		writer.close();
	}
	
	private void writePosition(SyntaxNode syntaxNode) {
		int offset = syntaxNode.getLeftPos()&0x00FFFFFF;
		int length = (syntaxNode.getRightPos()&0x00FFFFFF)-offset;
		writer.writeAttribute("offset", String.valueOf(offset));
		writer.writeAttribute("length", String.valueOf(length));
		
	}
	
	@Override
	public void visit(AndromedaFile andromedaFile) {
		XMLWriter writer = this.writer;
		AndromedaFileInfo info = andromedaFile.getFileInfo();
		int inclusionType = info.getInclusionType();
		
		//Omit natives if not desired to output them
		if(inclusionType==AndromedaFileInfo.TYPE_NATIVE&&!options.outputNatives){
			return;
		}
		
		writer.writeStartElement("source");
		
		Source source = env.getSourceById(info.getFileId());
		writer.writeAttribute("name", source.getName());
		writer.writeAttribute("type", source.getTypeName());
		writer.writeAttribute("inclusionType", String.valueOf(inclusionType));
		writer.writeAttribute("path", source.getFullPath());
				
		andromedaFile.getContent().childrenAccept(this);
		writer.writeEndElement();
	}
	
	@Override
	public void visit(IncludedFile includedFile) {
		includedFile.childrenAccept(this);
	}
	
	@Override
	public void visit(FunctionDeclaration functionDeclaration) {
		functionDeclaration.childrenAccept(this);
	}
	
	@Override
	public void visit(GlobalVarDeclaration globalVarDeclaration) {
		globalVarDeclaration.childrenAccept(this);
	}
	
	
	@Override
	public void visit(ClassDeclaration classDeclaration) {
		XMLWriter writer = this.writer;
		writer.writeStartElement("class");
		

		
		Class c = (Class)classDeclaration.getSemantics();
		writer.writeAttribute("name", c.getName());
		writePosition(classDeclaration);
		writer.writeAttribute("visibility", Visibility.getName(c.getVisibility()));
		if(c.isStatic()){
			writer.writeAttribute("static", "true");
		}
		if(c.isFinal()){
			writer.writeAttribute("final", "true");
		}
		if(c.getSuperClass()!=null){
			writer.writeAttribute("extends", c.getName());
		}
		
		boolean inRecordBefore = inRecord;
		inRecord=true;		
		classDeclaration.getBody().childrenAccept(this);
		inRecord=inRecordBefore;
		
		writer.writeEndElement();
	}
	
	@Override
	public void visit(StructDeclaration structDeclaration) {
		XMLWriter writer = this.writer;
		writer.writeStartElement("struct");
		writer.writeAttribute("name", structDeclaration.getName());
		writePosition(structDeclaration);
		
		boolean inRecordBefore = inRecord;
		inRecord=true;		
		structDeclaration.childrenAccept(this);
		inRecord=inRecordBefore;
		
		writer.writeEndElement();
	}
	


	@Override
	public void visit(EnrichDeclaration enrichDeclaration) {
		XMLWriter writer = this.writer;
		writer.writeStartElement("enrich");
		
		Enrichment c = (Enrichment)enrichDeclaration.getSemantics();
		writer.writeAttribute("type", c.getEnrichedType().getUid());
		writePosition(enrichDeclaration);
		writer.writeAttribute("visibility", Visibility.getName(c.getVisibility()));
		
		boolean inRecordBefore = inRecord;
		inRecord=true;		
		enrichDeclaration.getBody().childrenAccept(this);
		inRecord=inRecordBefore;
		
		writer.writeEndElement();
	}
	
	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		XMLWriter writer = this.writer;
		Function f = (Function) methodDeclaration.getSemantics();
		if(f.isForwardDeclaration()) return;
		if(inRecord){
			writer.writeStartElement("method");
		} else {
			writer.writeStartElement("function");
			
		}
		
		
		writer.writeAttribute("name", f.getName());
		writePosition(methodDeclaration);
		writer.writeAttribute("visibility", Visibility.getName(f.getVisibility()));
		
		
		if(inRecord){
			if(f.isFinal()){
				writer.writeAttribute("final", "true");
			}
			if(f.isAbstract()){
				writer.writeAttribute("abstract", "true");
			}
			if(f.isStatic()){
				writer.writeAttribute("static", "true");
			}
				
		} else {
			if(f.isNative()){
				writer.writeAttribute("native", "true");
			}
				
		}
		
		if(f.isInline()){
			writer.writeAttribute("inline", "true");
		}
		if(f.isOverride()){
			writer.writeAttribute("override", "true");
		}
		if(f.isStrcall()){
			writer.writeAttribute("strcall", "true");
		}
		writer.writeAttribute("signature", f.getSignature().toString());
		writer.writeAttribute("returnType", f.getReturnType().getUid());
		
		ParamDecl[] params = f.getParams();
		for(ParamDecl p: params){
			writer.writeEmptyElement("param");
			writer.writeAttribute("name", p.getUid());
			writer.writeAttribute("type", p.getType().getUid());

		}
		LocalVarDecl[] locals = f.getLocals();
		for(LocalVarDecl l: locals){
			writer.writeEmptyElement("local");
			writer.writeAttribute("name", l.getUid());
			writer.writeAttribute("type", l.getType().getUid());

		}
		
		writer.writeEndElement();
	}
	
	@Override
	public void visit(FieldDeclaration fieldDeclaration) {
		XMLWriter writer = this.writer;
		int size = fieldDeclaration.getDeclaredVariables().size();
		for(int i = 0; i < size; i++){
			SyntaxNode sn =  fieldDeclaration.getDeclaredVariables().elementAt(i);
			NonParamDecl fd = (NonParamDecl) sn.getSemantics();
			
			if(inRecord){
				writer.writeEmptyElement("field");
			} else {
				writer.writeEmptyElement("globalVariable");
			}
			
			writer.writeAttribute("name", fd.getUid());
			writePosition(sn);
			writer.writeAttribute("type", fd.getType().getUid());
			writer.writeAttribute("visibility", Visibility.getName(fd.getVisibility()));
			
			if(fd.isConst()){
				writer.writeAttribute("const","true");
				writer.writeAttribute("value", fd.getValue().toString());
			}
			if(fd.isStatic()){
				writer.writeAttribute("static", "true");
			}
		}
	}
	
	@Override
	public void visit(AccessorDeclaration accessorDeclaration) {
		XMLWriter writer = this.writer;
		writer.writeStartElement("accessor");
		
		AccessorDecl ad = (AccessorDecl) accessorDeclaration.getSemantics();
		writer.writeAttribute("name", ad.getUid());
		writePosition(accessorDeclaration);
		writer.writeAttribute("type", ad.getType().getUid());
		writer.writeAttribute("visibility", Visibility.getName(ad.getVisibility()));
		
		if(ad.isStatic()){
			writer.writeAttribute("static", "true");
		}
		
		Method m = ad.getGetter();
		if(m != null){
			writeGetSet(m,true);
		}
		m = ad.getSetter();
		if(m != null){
			writeGetSet(m,false);
		}
		
		writer.writeEndElement();
	}

	private void writeGetSet(Method m, boolean isGet) {
		XMLWriter writer = this.writer;
		if(isGet){
			writer.writeEmptyElement("get");
		} else {
			writer.writeEmptyElement("set");
		}
		writePosition(m.getDefinition());
		writer.writeAttribute("visibility", Visibility.getName(m.getVisibility()));
	}
	
}
