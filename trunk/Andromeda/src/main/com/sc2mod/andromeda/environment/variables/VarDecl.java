/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.environment.variables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sc2mod.andromeda.environment.IDefined;
import com.sc2mod.andromeda.environment.IIdentifiable;
import com.sc2mod.andromeda.environment.IModifiable;
import com.sc2mod.andromeda.environment.SemanticsElement;
import com.sc2mod.andromeda.environment.Util;
import com.sc2mod.andromeda.environment.scopes.Scope;
import com.sc2mod.andromeda.environment.scopes.ScopedElement;
import com.sc2mod.andromeda.environment.scopes.ScopedElementType;
import com.sc2mod.andromeda.environment.scopes.Visibility;
import com.sc2mod.andromeda.environment.types.RecordType;
import com.sc2mod.andromeda.environment.types.Type;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.ProblemId;
import com.sc2mod.andromeda.syntaxNodes.IdentifierNode;
import com.sc2mod.andromeda.syntaxNodes.ModifierListNode;
import com.sc2mod.andromeda.syntaxNodes.StmtNode;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;
import com.sc2mod.andromeda.syntaxNodes.TypeNode;
import com.sc2mod.andromeda.syntaxNodes.VarDeclNode;
import com.sc2mod.andromeda.vm.data.DataObject;

/**
 * Semantics representation of any declared variable
 * (Global, Field, Accessor, Local, Parameter)
 * @author J. 'gex' Finis
 *
 */
public abstract class VarDecl extends SemanticsElement implements ScopedElement, IIdentifiable, IDefined, IModifiable{

	public static final int TYPE_LOCAL = 1;
	public static final int TYPE_FIELD = 2;
	public static final int TYPE_GLOBAL = 3;
	public static final int TYPE_STATIC_FIELD = 4;
	public static final int TYPE_PARAMETER = 5;
	public static final int TYPE_ACCESSOR = 6;
	public static final int TYPE_STATIC_ACCESSOR = 7;
	public static final int TYPE_STATIC = 8;
	public static final int TYPE_FUNCTION_POINTER = 9;
	public static final int TYPE_IMPLICIT = 10;
	
	protected Visibility visibility = Visibility.DEFAULT;	
	private IdentifierNode declaration;
	private TypeNode typeNode;
	protected Type type;
	private String generatedName;
	private String name;
	private ModifierListNode mods;
	private DataObject value;
	protected LocalVarDecl overrides;
	private Scope scope;
	private int numReads;
	private int numWrites;
	private int numInlines;
	private boolean createCode = true;
	private List<StmtNode> initCode;
	
	public VarDecl(ModifierListNode mods,Type type,IdentifierNode def, Scope scope) {
		this.declaration = def;
		this.name = def.getId();
		this.mods = mods;
		this.scope = scope;
		def.setSemantics(this);
		Util.processModifiers(this, mods);
		setResolvedType(type);
	}
	
	public VarDecl(ModifierListNode mods,com.sc2mod.andromeda.syntaxNodes.TypeNode type,IdentifierNode def, Scope scope) {
		this.declaration = def;
		this.scope = scope;
		this.name = def.getId();
		this.mods = mods;
		this.typeNode = type;
		def.setSemantics(this);
		Util.processModifiers(this, mods);
	}
	
	public VarDecl() {
		//Constructor only for special var decls used by the name resolver
	}

	
	@Override
	public ScopedElementType getElementType(){
		return ScopedElementType.VAR;
	}
	
	public TypeNode getTypeNode() {
		return typeNode;
	}
	
	@Override
	public Scope getScope() {
		return scope;
	}
	
	public List<StmtNode> getInitCode() {
		return initCode;
	}

	public void addInitCode(Collection<StmtNode> initCode) {
		if(this.initCode==null){
			this.initCode = new ArrayList<StmtNode>(initCode.size());
		}
		this.initCode.addAll(initCode);
	}

	public boolean isCreateCode() {
		return createCode;
	}

	public void setCreateCode(boolean createCode) {
		this.createCode = createCode;
	}

	public abstract int getDeclType();
	

	/**
	 * Called by the resolve and check visitor when
	 * the type is resolved to entry it.
	 * @param t
	 */
	public void setResolvedType(Type t){
		this.type = t;
		if(declaration != null)
			declaration.setInferedType(t);
	}
	
	public ModifierListNode getModifiers(){
		return mods;
	}

	public String getGeneratedName() {
		return generatedName==null?getUid():generatedName;
	}

	public void setGeneratedName(String generatedName) {
		this.generatedName = generatedName;
	}
	

	@Override
	public String getUid() {
		return name;
	}

	@Override
	public SyntaxNode getDefinition() {
		return declaration;
	}
	
	public Type getType(){
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void override(LocalVarDecl overridden){
		if(overridden.overrides!=null) overrides = overridden.overrides;
		else overrides = overridden;
	}
	
	public LocalVarDecl getOverride(){
		return overrides;
	}
	
	public boolean doesOverride(){
		return overrides!=null;
	}
	

	public boolean isAccessor(){
		return false;
	}

	@Override
	public Visibility getVisibility() {return visibility; }
	@Override
	public boolean isAbstract() {return false;}
	@Override
	public boolean isConst() {return false;}
	@Override
	public boolean isFinal() {return false;}
	@Override
	public boolean isNative() {return false;}
	@Override
	public boolean isOverride() {return false;}
	@Override
	public boolean isStatic() {return false;}
	
	@Override
	public void setAbstract() {
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
				.details("This definition","abstract")
				.raiseUnrecoverable();
	}
	@Override
	public void setConst() {
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
		.details("This definition","const")
		.raiseUnrecoverable();
}
	@Override
	public void setFinal() {
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
		.details("This definition","final")
		.raiseUnrecoverable();
}
	@Override
	public void setNative() {
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
		.details("Variables","native")
		.raiseUnrecoverable();
}
	@Override
	public void setOverride(){
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
		.details("Variables","override")
		.raiseUnrecoverable();
	}
	@Override
	public void setStatic() {
		throw Problem.ofType(ProblemId.INVALID_MODIFIER).at(mods)
		.details("This definition","static")
		.raiseUnrecoverable();
	}
	@Override
	public void setVisibility(Visibility visibility){
		throw Problem.ofType(ProblemId.INVALID_VISIBILITY_MODIFIER).at(mods)
		.details("This definition")
		.raiseUnrecoverable();
	}
	
	public DataObject getValue() {
		return this.value;
	}
	
	public void setValue(DataObject value) {
		this.value = value;
	}
	
	public int getIndex(){
		return -1;
	}

	public void registerAccess(boolean write) {
		if(write){
			numWrites++;
		} else {
			numReads++;
		}
	}
	
	public void registerInline(){
		numInlines++;
	}
	
	public int getNumReadAccesses(){
		return numReads;
	}
	
	public int getNumWriteAccesses(){
		return numWrites;
	}
	
	public int getNumInlines(){
		return numInlines;
	}
	
	public boolean isGlobalField(){
		return false;
	}

	/**
	 * The type to which this variable belongs, if it is a field or accessor,
	 * or null for global variables and locals.
	 * 
	 * Any type is possible, not only record types, since an enrichment can
	 * even make a native type contain accessors.
	 * @return the containing type
	 */
	public Type getContainingType(){
		return null;
	}
	
	public abstract boolean isInitDecl();

	public VarDeclNode getDeclarator() {
		return null;
	}

	public boolean isMember() {
		return false;
	}
	
	@Override
	public String getElementTypeName() {
		return "field";
	}
}