/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.environment.types;


import com.sc2mod.andromeda.environment.visitors.VoidSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.NoResultSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.ParameterSemanticsVisitor;

public class SpecialType extends BasicType {

	public static SpecialType NULL = new TypeUnknown();
	public static SpecialType VOID = new SpecialType("void");
	
	
	protected SpecialType(String name) {
		super(name);
	}


	@Override
	public String getDescription() {
		return "void";
	}

	@Override
	public TypeCategory getCategory() {
		return TypeCategory.OTHER;
	}


	public static void registerSpecialTypes(TypeProvider t) {
		t.registerBasicType(VOID);
		t.registerBasicType(NULL);
	}
	

	public void accept(VoidSemanticsVisitor visitor) { visitor.visit(this); }
	public <P> void accept(NoResultSemanticsVisitor<P> visitor,P state) { visitor.visit(this,state); }
	public <P,R> R accept(ParameterSemanticsVisitor<P,R> visitor,P state) { return visitor.visit(this,state); }
}
