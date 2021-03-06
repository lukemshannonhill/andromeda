/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.codegen.buffers;

import com.sc2mod.andromeda.environment.types.IType;
import com.sc2mod.andromeda.parsing.options.Configuration;

public class GlobalVarBuffer extends AdvancedBuffer{

	private int bytes = 0;
	
	public GlobalVarBuffer(int initialSize, Configuration conf) {
		super(initialSize,conf);
	}
	

	
	public GlobalVarBuffer beginVarDecl(IType varType, String varName){
		//System.out.println(varName);
		append(varType.getGeneratedDefinitionName()).append(" ").append(varName);
		bytes+=varType.getGeneratedType().getByteSize();
		return this;
	}
	
//	public GlobalVarBuffer beginVarDecl(Type varType,String typeName, String varName){
//		append(typeName).append(" ").append(varName);
//		bytes+=varType.getByteSize();
//
//		System.out.println(varType.getUid() + " : " + varType.getByteSize());
//		return this;
//	}
	
	public GlobalVarBuffer beginArrayDecl(IType varType,String typeName, int size, String varName){
		append(typeName).append("[").append(size).append("] ").append(varName);
		bytes+=varType.getByteSize()*size;
		return this;
	}
	
	public GlobalVarBuffer beginArrayDecl(IType varType, int size, String varName){
		append(varType.getGeneratedDefinitionName()).append("[").append(size).append("] ").append(varName);
		bytes+=varType.getGeneratedType().getByteSize()*size;
		return this;
	}


	public int getSizeBytes() {
		return bytes;
	}
	
	
	
}
