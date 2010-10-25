package com.sc2mod.andromeda.semAnalysis;

import java.util.ArrayList;
import java.util.List;

import com.sc2mod.andromeda.environment.Environment;
import com.sc2mod.andromeda.environment.Signature;
import com.sc2mod.andromeda.environment.operations.Constructor;
import com.sc2mod.andromeda.environment.operations.Destructor;
import com.sc2mod.andromeda.environment.operations.Function;
import com.sc2mod.andromeda.environment.operations.Method;
import com.sc2mod.andromeda.environment.operations.StaticInit;
import com.sc2mod.andromeda.environment.scopes.Scope;
import com.sc2mod.andromeda.environment.types.BasicType;
import com.sc2mod.andromeda.environment.types.Class;
import com.sc2mod.andromeda.environment.types.Enrichment;
import com.sc2mod.andromeda.environment.types.Extension;
import com.sc2mod.andromeda.environment.types.Interface;
import com.sc2mod.andromeda.environment.types.RecordType;
import com.sc2mod.andromeda.environment.types.SpecialType;
import com.sc2mod.andromeda.environment.types.Type;
import com.sc2mod.andromeda.environment.types.TypeCategory;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.environment.variables.AccessorDecl;
import com.sc2mod.andromeda.environment.variables.FieldDecl;
import com.sc2mod.andromeda.environment.variables.GlobalVarDecl;
import com.sc2mod.andromeda.environment.variables.ParamDecl;
import com.sc2mod.andromeda.environment.variables.VarDecl;
import com.sc2mod.andromeda.environment.visitors.VoidSemanticsVisitorAdapter;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.ProblemId;
import com.sc2mod.andromeda.syntaxNodes.GlobalStructureNode;
import com.sc2mod.andromeda.syntaxNodes.IdentifierNode;
import com.sc2mod.andromeda.syntaxNodes.ParameterListNode;
import com.sc2mod.andromeda.syntaxNodes.ParameterNode;
import com.sc2mod.andromeda.syntaxNodes.TypeAliasDeclNode;
import com.sc2mod.andromeda.syntaxNodes.TypeListNode;
import com.sc2mod.andromeda.syntaxNodes.TypeNode;
import com.sc2mod.andromeda.syntaxNodes.util.VoidVisitorAdapter;
import com.sc2mod.andromeda.util.Pair;

/**
 * This semantics visitor resolves the types for a given semantics element.
 * 
 * For classes and interfaces, it resolves and checks the types in the extends/implements clause.
 * 
 * For method it resolves and checks parameters and return type.
 * 
 * Can raise a variety of problems that arise during the resolving and checking.
 * @author gex
 *
 */
public class ResolveAndCheckTypesVisitor extends VoidSemanticsVisitorAdapter {

	private TypeProvider tprov;
	private static ParamDecl[] NO_PARAMS = new ParamDecl[0];
	
	public ResolveAndCheckTypesVisitor(Environment env) {
		this.tprov = env.typeProvider;
	}
	
	
	//*************************************************************
	//***														***
	//***						VARIABLES						***
	//***														***
	//*************************************************************
	
	/**
	 * Resolves the type of a var decl
	 */
	private void resolveVarType(VarDecl varDecl) {
		TypeNode typeNode = varDecl.getTypeNode();
		
		/*
		 * If this var has no type node, we cannot resolve it.
		 * (This can happen if the var was created with an already resolved type
		 * for example at implicit decls)
		 */
		if(typeNode == null)
			return;
		
		Type t = tprov.resolveType(typeNode, varDecl.getScope());
		varDecl.setResolvedType(t);
	}
	
	@Override
	public void visit(FieldDecl fieldDecl) {
		resolveVarType(fieldDecl);
	}
	
	@Override
	public void visit(AccessorDecl accessorDecl) {
		resolveVarType(accessorDecl);
		Method getter = accessorDecl.getGetter();
		Method setter = accessorDecl.getSetter();
		if(getter!=null) getter.accept(this);
		if(setter!=null){
			setter.accept(this);
			setter.addImplicitParam(accessorDecl.getType(), "value");
		}
	}
	
	@Override
	public void visit(GlobalVarDecl globalVarDecl) {
		resolveVarType(globalVarDecl);
	}
	
	
	
	//*************************************************************
	//***														***
	//***						OPERATIONS						***
	//***														***
	//*************************************************************
	
	
	private void resolveFuncParams(Function function){
		Scope scope = function.getScope();
		ParameterListNode paramList = function.getHeader().getParameters();
		int size = paramList.size();
		Type[] sig = new Type[size];
		ParamDecl[] params = new ParamDecl[size];

		for(int i=0;i<size;i++){
			ParameterNode param = paramList.elementAt(i);
			Type type = tprov.resolveType(param.getType(),scope);
			if(!type.isValidAsParameter()) 
				throw Problem.ofType(ProblemId.ARRAY_OR_STRUCT_AS_PARAMETER).at(param)
						.raiseUnrecoverable();
			sig[i] = type;
			params[i] = new ParamDecl(null,type,param.getName());
	
		}
		function.setResolvedParameters(params);
	}
	
	private void resolveReturnType(Function function){
		Type returnType = tprov.resolveType(function.getHeader().getReturnType(),function.getScope());
		if(!returnType.isValidAsParameter()) 
			throw Problem.ofType(ProblemId.ARRAY_OR_STRUCT_RETURNED).at(function.getHeader().getReturnType())
						.raiseUnrecoverable();
		function.setReturnType(returnType);
	}
	
	@Override
	public void visit(Function function) {
		resolveFuncParams(function);
		resolveReturnType(function);
	}
	
	@Override
	public void visit(Method method) {
		resolveFuncParams(method);
		resolveReturnType(method);
	}
	
	@Override
	public void visit(Constructor constructor) {
		resolveFuncParams(constructor);
		constructor.setReturnType(SpecialType.VOID);
	}
	
	@Override
	public void visit(Destructor destructor) {
		destructor.setResolvedParameters(NO_PARAMS);
		destructor.setReturnType(SpecialType.VOID);
	}
	
	@Override
	public void visit(StaticInit staticInit) {
		staticInit.setReturnType(SpecialType.VOID);
		staticInit.setResolvedParameters(NO_PARAMS);
	}

	//*************************************************************
	//***														***
	//***						INTERFACES						***
	//***														***
	//*************************************************************
	
	protected void resolveInterfaceExtends(Interface interfac) {
		TypeListNode tl = interfac.getDefinition().getInterfaces();
		int size = tl.size();
		for(int i=0;i<size;i++){
			Type in = tprov.resolveType(tl.elementAt(i),interfac);
			if(in.getCategory()!=TypeCategory.INTERFACE){
				throw Problem.ofType(ProblemId.INTERFACE_EXTENDING_NON_INTERFACE).at(tl.elementAt(i))
							.raiseUnrecoverable();
			}
			if(!interfac.getInterfaces().add((Interface)in)){
				throw Problem.ofType(ProblemId.DUPLICATE_EXTENDS).at(tl.elementAt(i))
						.raiseUnrecoverable();
			}
		}	
	}
	
	@Override
	public void visit(Interface interface1) {
		resolveInterfaceExtends(interface1);
	}
	
	//*************************************************************
	//***														***
	//***						CLASSES							***
	//***														***
	//*************************************************************
	
	/**
	 * XPilot: enabled extending of generic classes.
	 */
	protected void resolveClassExtends(Class clazz) {
		Type type = tprov.resolveType(clazz.getDefinition().getSuperClass(),clazz);
		if(!type.isClass())
			throw Problem.ofType(ProblemId.CLASS_EXTENDS_NON_CLASS).at(clazz.getDefinition().getSuperClass())
							.raiseUnrecoverable();
		clazz.setSuperClass((Class)type);
	}

	protected void resolveClassImplements(Class clazz) {
		TypeListNode tl = clazz.getDefinition().getInterfaces();
		int size = tl.size();
		for(int i=0;i<size;i++){
			Type in = tprov.resolveType(tl.elementAt(i),clazz);
			if(in.getCategory()!=TypeCategory.INTERFACE){
				throw Problem.ofType(ProblemId.CLASS_IMPLEMENTS_NON_INTERFACE).at(tl.elementAt(i))
						.raiseUnrecoverable();
			}
			if(!clazz.getInterfaces().add((Interface)in)){
					throw Problem.ofType(ProblemId.DUPLICATE_IMPLEMENTS).at(tl.elementAt(i))
						.raiseUnrecoverable();
			}
		}		
		if(clazz.isStatic())
			throw Problem.ofType(ProblemId.STATIC_CLASS_HAS_IMPLEMENTS).at(clazz.getDefinition().getInterfaces())
						.raiseUnrecoverable();
	}
	

	@Override
	public void visit(Class class1) {
		GlobalStructureNode decl = class1.getDefinition();
		TypeListNode in =decl.getInterfaces();
		if(in!=null&&!in.isEmpty())
			resolveClassImplements(class1);
		if(decl.getSuperClass() != null)
			resolveClassExtends(class1);
	}
	

	//*************************************************************
	//***														***
	//***						EXTENSIONS						***
	//***														***
	//*************************************************************
	
	@Override
	public void visit(Extension extension) {
		Type extendedType = tprov.resolveType(extension.getDefinition().getEnrichedType(), extension.getScope());
		BasicType extendedBaseType;
		int hierarchyLevel;
		switch(extendedType.getCategory()){
		case BASIC:
			extendedBaseType = (BasicType)extendedType;
			hierarchyLevel = 0;
			break;
		case EXTENSION:
			Extension e = ((Extension)extendedType);
			extendedBaseType = e.getBaseType();
			hierarchyLevel = e.getExtensionHierachryLevel()+1;
			if(extension.isDistinct())
				throw Problem.ofType(ProblemId.DISJOINT_EXTENSION_BASED_ON_EXTENSION).at(extension.getDefinition())
						.raiseUnrecoverable();
			break;
		default:
			throw Problem.ofType(ProblemId.EXTENSION_TYPE_INVALID).at(extension.getDefinition())
						.details(extendedType.getFullName())
						.raiseUnrecoverable();
		}		
		if(extendedType.isKeyType()){
			throw Problem.ofType(ProblemId.EXTENSION_OF_KEY_TYPE).at(extension.getDefinition())
					.raiseUnrecoverable();
		}
		if(extension.isKey()){
			if(extendedBaseType != BasicType.INT && extendedBaseType != BasicType.STRING){
				throw Problem.ofType(ProblemId.INVALID_BASE_TYPE_FOR_KEY_TYPE).at(extension.getDefinition())
						.raiseUnrecoverable();
			}
		}
		
		//Entry the results into the extension
		extension.setResolvedExtendedType(extendedType,extendedBaseType,hierarchyLevel);
		
	}
	
	//*************************************************************
	//***														***
	//***						ENRICHMENTS						***
	//***														***
	//*************************************************************

	@Override
	public void visit(Enrichment enrichment) {
		Type enrichedType = tprov.resolveType(enrichment.getDefinition().getEnrichedType(), enrichment.getParentScope());
		enrichment.setResolvedEnrichedType(enrichedType);
	}
}
