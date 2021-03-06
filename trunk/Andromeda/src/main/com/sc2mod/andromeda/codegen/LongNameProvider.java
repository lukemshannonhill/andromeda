/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.codegen;

import java.util.Hashtable;

import com.sc2mod.andromeda.environment.Environment;
import com.sc2mod.andromeda.environment.operations.Function;
import com.sc2mod.andromeda.environment.types.IStruct;
import com.sc2mod.andromeda.environment.types.IType;
import com.sc2mod.andromeda.environment.types.TypeUtil;
import com.sc2mod.andromeda.environment.variables.FieldDecl;
import com.sc2mod.andromeda.environment.variables.Variable;

/**
 * Implementation of the NameProvider interface which
 * tries to create human-readable names that resemble the 
 * names in the Andromeda code.
 * @author J. 'gex' Finis
 *
 */
public class LongNameProvider implements INameProvider {

	private Object token = new Object();
	private Hashtable<String, Object> localNames = new Hashtable<String, Object>();
	private FunctionIndexProvider indexProvider;
	private IndexInformation indexInfo;
	
	public LongNameProvider(IndexInformation indexInfo, Environment semEnv) {
		this.indexProvider = new FunctionIndexProvider(semEnv);
		this.indexInfo = indexInfo;
	}
	
	@Override
	public void assignLocalNamesForMethod(Function function) {
		
		Variable[] vars = function.getParams();
		for(int i=0;i<vars.length;i++){
			Variable v = vars[i];
			v.setGeneratedName(v.getUid());
		}
		vars = function.getLocals();
		

		localNames.clear();
		
		if(vars!=null){
			for(int i=0;i<vars.length;i++){
				Variable v = vars[i];
				if(v.doesOverride()) continue;
				
				//We need to check local vars for duplicate names because the override mechanisms can
				//create duplicates
				String name = v.getUid();
				if(localNames.put(name, token)==token){
					int index = 2;
					String newName;
					do{
						newName = name + index++;
					} while(localNames.put(newName, token)==token);
					name = newName;
				}
						
				v.setGeneratedName(name);
			}
		}
		
	}

	private StringBuilder builder = new StringBuilder(32);
	private int initIndex = 1;
	
	@Override
	public String getFunctionName(Function function) {
		//Reset builder
		builder.setLength(0);
		
		boolean writeIndex = false;
		switch(function.getOperationType()){
		case NATIVE:
			return function.getName();
		case STATIC_METHOD:
			builder.append("s_");
		case METHOD:
			builder.append(function.getContainingType().getUid()).append("__");
		case FUNCTION:
			builder.append(function.getName());
			writeIndex = true;
			break;
		case CONSTRUCTOR:
			builder.append("new__");
			builder.append(function.getContainingType().getUid());
			writeIndex = true;
			break;
		case DESTRUCTOR:
			builder.append("delete__");
			builder.append(function.getContainingType().getUid());
			break;
		case STATIC_INIT:
			builder.append("init___");
			IType r = function.getContainingType();
			if(r != null){
				builder.append(r.getUid()).append("__");
			} else {
				builder.append("global");
			}
			builder.append(initIndex++);
			
			break;
		default:
			throw new Error("Unknown function type!");
		}
		
		if(writeIndex){
			int index = indexProvider.getFuncIndex(function);
			if(index != 0){
				builder.append("__").append(index);
			}
		}
		return builder.toString();
	}

	@Override
	public String getGlobalName(Variable decl) {
		return decl.getUid();
	}


	@Override
	public String getTypeName(IType type) {
		return type.getUid();
	}

	@Override
	public void assignFieldNames(IStruct struct) {
		Iterable<Variable> fields = TypeUtil.getNonStaticTypeFields(struct, false);
		for (Variable field : fields) {
			field.setGeneratedName(field.getUid());
		}
	}

	@Override
	public String getFieldName(FieldDecl decl, IType clazz) {
		StringBuilder b = new StringBuilder(24);
		if(decl.isStaticElement()){
			b.append("s_");
			b.append(clazz.getUid());		
			b.append("__");
			b.append(decl.getUid());
		} else {
			b.append(clazz.getUid());		
			b.append("__");
			b.append(decl.getUid());
			if(indexInfo.isFieldAliased(decl)){
				b.append("_mult");
			}
		}

		return b.toString();
	}

	@Override
	public String getGlobalNameRaw(String name) {
		return "A__".concat(name);
	}

	@Override
	public String getLocalNameRaw(String name, int index) {
		return name;
	}

	@Override
	public String getGlobalNameRawNoPrefix(String name) {
		return name;
	}



	
}
