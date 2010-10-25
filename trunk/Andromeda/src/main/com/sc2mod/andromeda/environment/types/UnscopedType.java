package com.sc2mod.andromeda.environment.types;

import com.sc2mod.andromeda.environment.scopes.Scope;
import com.sc2mod.andromeda.environment.scopes.Visibility;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;

public abstract class UnscopedType extends Type {

	/**
	 * Unscoped types have no scope...
	 */
	protected UnscopedType() {
		super(null);
	}
	
	/**
	 * Unscoped types are always public
	 */
	@Override
	public Visibility getVisibility() {
		return Visibility.PUBLIC;
	}
	
	/**
	 * Unscoped types have no definition :(
	 */
	@Override
	public SyntaxNode getDefinition() {
		return null;
	}

}
