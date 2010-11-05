package com.sc2mod.andromeda.environment.types;

import com.sc2mod.andromeda.environment.Signature;
import com.sc2mod.andromeda.environment.scopes.IScope;
import com.sc2mod.andromeda.environment.scopes.content.NameResolver;
import com.sc2mod.andromeda.environment.scopes.content.ResolveUtil;
import com.sc2mod.andromeda.environment.scopes.content.ScopeContentSet;
import com.sc2mod.andromeda.environment.types.generic.GenericMemberGenerationVisitor;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.ProblemId;
import com.sc2mod.andromeda.syntaxNodes.ArrayTypeNode;
import com.sc2mod.andromeda.syntaxNodes.BasicTypeNode;
import com.sc2mod.andromeda.syntaxNodes.FuncPointerTypeNode;
import com.sc2mod.andromeda.syntaxNodes.PointerTypeNode;
import com.sc2mod.andromeda.syntaxNodes.QualifiedTypeNode;
import com.sc2mod.andromeda.syntaxNodes.SimpleTypeNode;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;
import com.sc2mod.andromeda.syntaxNodes.TypeListNode;
import com.sc2mod.andromeda.syntaxNodes.util.VisitorAdapter;

/**
 * Visitor for resolving potentially nested types.
 * 
 * Is part of the TypeProvider class.
 * @author gex
 *
 */
public class TypeResolver extends VisitorAdapter<IScope,IType>{

	private TypeProvider tprov;	
	private GenericMemberGenerationVisitor genericsResolver;


	public TypeResolver(TypeProvider tprov){
		this.tprov = tprov;
		this.genericsResolver = new GenericMemberGenerationVisitor(tprov);
	}
	
	public IType raiseUnknownType(SyntaxNode type, String name){
		throw Problem.ofType(ProblemId.UNKNOWN_TYPE).at(type)
		.details(name)
		.raiseUnrecoverable();
		//TODO: As soon as error recovery works:
		//return TypeError.INSTANCE;
	}
	
	@Override
	public IType visit(SimpleTypeNode type, IScope scope) {
		
		//Resolve type name
		String name = type.getName();
		
		IType result = ResolveUtil.resolveUnprefixedType(name, scope, type);
		if(result == null){
			return raiseUnknownType(type, name);
		}
		
		//Generics
		TypeListNode args = type.getTypeArguments();
		if(result.isGenericDecl()){
			
			//If no arguments were stated, we return the declaration of the generic type itself
			if(args == null)
				return result;
			
			//We have a generic type with type arguments, i.e. a generic type instance. Resolve arguments
			int size  = args.size();
			IType[] typeArgs = new IType[size];
			for(int i=0;i<size;i++){
				//Resolve arguments
				IType t = args.elementAt(i).accept(this,scope);
				typeArgs[i]=t;				
			}
			INamedType genericInstance = tprov.getGenericInstance(((INamedType)result),new Signature(typeArgs));
			//If immediate generic resolving is activated, then the generic instance members are copied down
			if(tprov.doResolveGenerics() && !genericInstance.isGenericDecl()){
				genericInstance.accept(genericsResolver);
			}
		} else{
			if(args != null)
				throw Problem.ofType(ProblemId.NON_GENERIC_TYPE_HAS_TYPE_ARGUMENTS).at(args)
								.details(result.getFullName())
								.raiseUnrecoverable();
		}
		return result;
	}
	
	@Override
	public IType visit(BasicTypeNode type, IScope scope) {
		String name = type.getName();
		IType result = ResolveUtil.resolveUnprefixedType(name, scope, type);
		if(result == null){
			return raiseUnknownType(type, name);
		}
		return result;
	}
	
	@Override
	public IType visit(QualifiedTypeNode qualifiedTypeNode, IScope state) {
		throw new Error("Qualified type names not possible yet!");
	}
	
	@Override
	public IType visit(PointerTypeNode type, IScope scope) {
		return tprov.getPointerType(type.getWrappedType().accept(this,scope));
	}
	
	@Override
	public IType visit(ArrayTypeNode type, IScope scope) {
		return tprov.getArrayType(type.getWrappedType().accept(this,scope),type.getDimensions());
	}
	
	@Override
	public IType visit(FuncPointerTypeNode type, IScope scope) {
		return tprov.getFunctionPointerType(type, scope);
	}
	
	
	
}
