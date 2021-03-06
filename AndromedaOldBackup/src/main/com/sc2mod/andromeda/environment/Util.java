/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.environment;

import java.util.HashMap;
import java.util.HashSet;

import com.sc2mod.andromeda.notifications.InternalProgramError;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.ProblemId;
import com.sc2mod.andromeda.syntaxNodes.AnnotationNode;
import com.sc2mod.andromeda.syntaxNodes.AnnotationListNode;
import com.sc2mod.andromeda.syntaxNodes.ModifierTypeSE;
import com.sc2mod.andromeda.syntaxNodes.ModifierListNode;

public final class Util {

	private Util(){}
	
	public static void processAnnotations(IAnnotatable annotatable, AnnotationListNode al){
		if(al==null) return;
		HashMap<String, AnnotationNode> annotations = new HashMap<String, AnnotationNode>();
		HashSet<String> allowedAnnotations = annotatable.getAllowedAnnotations();
		annotatable.setAnnotationTable(annotations);
		int size = al.size();
		for(int i=0; i<size; i++){
			AnnotationNode a = al.elementAt(i);
			String name = a.getName();
			if(!allowedAnnotations.contains(name)){
				throw Problem.ofType(ProblemId.UNKNOWN_ANNOTATION).at(a)
						.details(name,annotatable.getDescription())
						.raiseUnrecoverable();
			}
			AnnotationNode old = annotations.put(name, a);
			if(old != null){
				throw Problem.ofType(ProblemId.UNKNOWN_ANNOTATION).at(a)
				.details(name,annotatable.getDescription())
				.raiseUnrecoverable();
			}
		}
		annotatable.afterAnnotationsProcessed();
	}
	
	public static void processModifiers(IModifiable m, ModifierListNode mods){
		if(mods==null) return;
		int size = mods.size();
		for(int i=0;i<size;i++){
			switch(mods.elementAt(i)){
			case ModifierTypeSE.ABSTRACT:
				if(m.isAbstract()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("abstract")
							.raise();
				else
					m.setAbstract();
				break;
			case ModifierTypeSE.FINAL:
				if(m.isFinal()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("final")
							.raise();
				else
					m.setFinal();
				break;
			case ModifierTypeSE.STATIC:
				if(m.isStatic()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("static")
							.raise();
				else
					m.setStatic();
				break;
			case ModifierTypeSE.CONST:
				if(m.isConst()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("const")
							.raise();
				else
					m.setConst();
				break;
			case ModifierTypeSE.OVERRIDE:
				if(m.isOverride()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("override")
							.raise();
				else
					m.setOverride();
				break;
			case ModifierTypeSE.PRIVATE:
				if(m.getVisibility()!=Visibility.DEFAULT)
					Problem.ofType(ProblemId.DUPLICATE_VISIBILITY_MODIFIER).at(mods)
							.raise();
				else
					m.setVisibility(Visibility.PRIVATE);
				break;
			case ModifierTypeSE.PROTECTED:
				if(m.getVisibility()!=Visibility.DEFAULT)
					Problem.ofType(ProblemId.DUPLICATE_VISIBILITY_MODIFIER).at(mods)
							.raise();
				else
					m.setVisibility(Visibility.PROTECTED);
				break;
			case ModifierTypeSE.PUBLIC:
				if(m.getVisibility()!=Visibility.DEFAULT)
					Problem.ofType(ProblemId.DUPLICATE_VISIBILITY_MODIFIER).at(mods)
							.raise();
				else 
					m.setVisibility(Visibility.PUBLIC);
				break;				
			case ModifierTypeSE.NATIVE:
				if(m.isNative()) 
					Problem.ofType(ProblemId.DUPLICATE_MODIFIER).at(mods)
							.details("native")
							.raise();
				else
					m.setNative();
				break;
			default:
				throw new InternalProgramError(mods,"Unknown modifier.");
			}
		}
	
	}
}
