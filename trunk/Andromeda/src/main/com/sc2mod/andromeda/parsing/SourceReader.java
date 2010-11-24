/**
O *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.parsing;

import java.io.FilterReader;
import java.io.IOException;

import com.sc2mod.andromeda.parsing.framework.Source;

public class SourceReader extends FilterReader{

	private int index;
	private InclusionType inclusionType;
	private SourceManager environment;
	public InclusionType getInclusionType() {
		return inclusionType;
	}	
		
	SourceReader(SourceManager s, Source f, InclusionType inclusionType, int count) throws IOException{
		super(f.createReader());
		this.inclusionType = inclusionType;
		this.index = count;
		this.environment = s;
	}
		
	public int getFileId(){
		return index;		
	}

	public SourceManager getSourceEnvironment() {
		return environment;
	}
	

	

	
}
