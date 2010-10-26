/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.vm.data;

import com.sc2mod.andromeda.environment.operations.Operation;

public class FuncNameObject extends StringObject {

	private Operation func;
	public FuncNameObject(Operation func) {
		super(func.getGeneratedName());
		this.func = func;
	}
	
	@Override
	public String getStringValue() {
		return func.getGeneratedName();
	}

	@Override
	public String toString() {
		return func.getGeneratedName();
	}
	
	public Operation getFunction() {
		return func;
	}
}