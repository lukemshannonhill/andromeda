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

import com.sc2mod.andromeda.environment.types.IType;
import com.sc2mod.andromeda.environment.types.RuntimeType;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.syntaxNodes.ExprNode;
import com.sc2mod.andromeda.syntaxNodes.LiteralTypeSE;

public class BoolObject extends DataObject{
	
	private static final BoolObject TRUE = new BoolObject(true);
	private static final BoolObject FALSE = new BoolObject(false);
	
	public static BoolObject getBool(boolean val)
	{
		if(val) return TRUE;
		return FALSE;
	}
	
	private boolean val;

	private BoolObject(boolean val) {
		super();
		this.val = val;
	}
	
	@Override
	public String toString() {
		return String.valueOf(val);
	}
	
	@Override
	public ExprNode getExpression(TypeProvider tp) {
		if(val) return TRUE.getLiteralExpr(tp,LiteralTypeSE.BOOL);
		return FALSE.getLiteralExpr(tp, LiteralTypeSE.BOOL);
	}
	
	@Override
	public IType getType(TypeProvider tp) {
		return tp.BASIC.BOOL;
	}
	
	@Override
	public DataObject castTo(IType type) {
		switch(type.getRuntimeType()){
		case RuntimeType.BOOL: return this;
		case RuntimeType.STRING: return new StringObject(String.valueOf(val));
		case RuntimeType.TEXT: return new TextObject(String.valueOf(val));
			
		}
		return super.castTo(type);
	}
	
	
}
