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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import com.sc2mod.andromeda.environment.AbstractFunction;
import com.sc2mod.andromeda.environment.Function;
import com.sc2mod.andromeda.environment.Method;
import com.sc2mod.andromeda.environment.Scope;
import com.sc2mod.andromeda.environment.Signature;
import com.sc2mod.andromeda.environment.Visibility;
import com.sc2mod.andromeda.notifications.CompilationError;
import com.sc2mod.andromeda.parsing.AndromedaReader;
import com.sc2mod.andromeda.syntaxNodes.MethodInvocation;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;

public class EnrichmentMethodSet {

	private LinkedHashMap<String, LinkedHashMap<Signature, LinkedList<AbstractFunction>>> methods = new LinkedHashMap<String, LinkedHashMap<Signature, LinkedList<AbstractFunction>>>();
	private Type forType;
	
	public EnrichmentMethodSet(Type forType) {
		this.forType = forType;
	}

	void addMethod(AbstractFunction f) {
		String uid = f.getUid();
		LinkedHashMap<Signature, LinkedList<AbstractFunction>> meths = methods.get(uid);
		if (meths == null) {
			meths = new LinkedHashMap<Signature, LinkedList<AbstractFunction>>();
			methods.put(uid, meths);
		}
		addMethod(f, meths);
	}

	private void addMethod(AbstractFunction f, LinkedHashMap<Signature, LinkedList<AbstractFunction>> meths) {
		LinkedList<AbstractFunction> list = meths.get(f.getSignature());

				
		// No duplicate method? Everything fine!
		if (list == null){
			list = new LinkedList<AbstractFunction>();
			meths.put(f.getSignature(), list);
			list.add(f);
			return;
		}
		
		//Function is just a forward declaration and there is already such a function? Skip!
		if(f.isForwardDeclaration()) return;
		
		//Test for duplicate functions
		ListIterator<AbstractFunction> iter = list.listIterator();
		while(iter.hasNext()){
			AbstractFunction f2 = iter.next();
			
			//Forward declaration? Remove!
			if(f2.isForwardDeclaration()){
				iter.remove();
				continue;
			}
			
			//Duplicate?
			if(	f2.getScope().equals(f.getScope())||(f.getVisibility()!=Visibility.PRIVATE&&f2.getVisibility()!=Visibility.PRIVATE)){
				
				if(f2.isOverride()||f.isOverride()){
					if(f2.isOverride()&&f.isOverride())
						throw new CompilationError(f.getDefinition(),f2.getDefinition(),
										"Duplicate override definition of function '" + f.getName() + "'.",
										"First defined here");
					
				} else throw new CompilationError(f.getDefinition(),f2.getDefinition(),
										"Duplicate definition of function '" + f.getName() + "'.",
										"First defined here");
			}
		}
		list.add(f);
	
	}
	
	private static AbstractFunction chooseFunctionForInvocation(LinkedList<AbstractFunction> functions, Scope scope){
		AbstractFunction candidate = null;
		int candidatePriority = 0;
		int curPriority;
		for(AbstractFunction f: functions){				
			curPriority = 0;
			
			//Scoped private functions override global ones
			if(f.getScope()==scope){
				curPriority = 2;
				if(f.isOverride()){
					curPriority = 4;
				}
			}else if(f.getVisibility()!=Visibility.PRIVATE){
				curPriority = 1;
				if(f.isOverride()){
					curPriority = 3;
				}
			}
			if(curPriority>candidatePriority){
				candidate = f;
				candidatePriority = curPriority;
			}
		}
		return candidate;
	}

	public AbstractFunction resolveInvocation(Scope scope,SyntaxNode where, String name, Signature s) {
		LinkedHashMap<Signature, LinkedList<AbstractFunction>> funcs = methods.get(name);
		if(funcs==null) return null;
		//No signature specified? Only one method with the name allowed
		if(s == null){
			if(funcs.size()>1) throw new CompilationError(where,"Ambiguous access! There is more than one method with the name " + name);
			return chooseFunctionForInvocation(funcs.entrySet().iterator().next().getValue(),scope);			
		}
		LinkedList<AbstractFunction> funcList = funcs.get(s);
		if(funcList == null){
			//No direct hit :(
			
			AbstractFunction globalCandidate = null;
			AbstractFunction candidate = null;
			boolean error = false;
			for(Signature sig: funcs.keySet()){
				if(s.canImplicitCastTo(sig)){
					funcList = funcs.get(sig);
					candidate = chooseFunctionForInvocation(funcList,scope);
					if(candidate != null && globalCandidate != null){
						error = true;
					}
					globalCandidate = candidate;

				}
				if(error) throw new CompilationError(where,candidate.getDefinition(),globalCandidate.getDefinition()
										,"This function call is ambiguous. Functions that could be called:",
										"First","Second");					
				globalCandidate = candidate;				
				
			}
			return globalCandidate;
		}
		
		//Direct signature hit!
		return chooseFunctionForInvocation(funcList, scope);		
	}
	
	public void generateFunctionIndex(){
		for(String s: methods.keySet()){
			int index = 0;
			LinkedHashMap<Signature, LinkedList<AbstractFunction>> funcs = methods.get(s);
			for(Signature sig: funcs.keySet()){
				LinkedList<AbstractFunction> sameFuncs = funcs.get(sig);
				boolean danglingDeclaration = true;
				
				//If we have only one function, we need no function indices.
				//However, we still mus go through the loop to check for dangling forward-declarations.
				if(sameFuncs.size()==1&&funcs.size()==1){
					index = 0;
				}
				for(AbstractFunction f: sameFuncs){
					if(f.hasBody()){
						f.setIndex(index++);
						danglingDeclaration = false;
					} else if(f.isNative()){
						danglingDeclaration = false;
					}
				}
				if(danglingDeclaration){
					throw new CompilationError(sameFuncs.getFirst().getDefinition(), "No definition found for forward-declared function '" + sameFuncs.getFirst().getName() + "'.");
				}
			}
		}
	}
}
