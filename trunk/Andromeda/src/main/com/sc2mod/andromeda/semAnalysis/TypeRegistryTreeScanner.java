/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.semAnalysis;

import com.sc2mod.andromeda.environment.Environment;
import com.sc2mod.andromeda.environment.scopes.FileScope;
import com.sc2mod.andromeda.environment.scopes.IScope;
import com.sc2mod.andromeda.environment.scopes.Package;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.syntaxNodes.ClassDeclNode;
import com.sc2mod.andromeda.syntaxNodes.CompilationUnitIdentifierNode;
import com.sc2mod.andromeda.syntaxNodes.FieldDeclNode;
import com.sc2mod.andromeda.syntaxNodes.InterfaceDeclNode;
import com.sc2mod.andromeda.syntaxNodes.MethodDeclNode;
import com.sc2mod.andromeda.syntaxNodes.PackageDeclNode;
import com.sc2mod.andromeda.syntaxNodes.SourceFileNode;
import com.sc2mod.andromeda.syntaxNodes.StaticInitDeclNode;
import com.sc2mod.andromeda.syntaxNodes.StructDeclNode;
import com.sc2mod.andromeda.syntaxNodes.TypeAliasDeclNode;
import com.sc2mod.andromeda.syntaxNodes.TypeExtensionDeclNode;
import com.sc2mod.andromeda.util.visitors.NoResultTreeScanVisitor;

/**
 * This scanner does the first step of the semantic analysis by registering
 * all types in all files and registering their scope. These types are later
 * needed to type-check all kinds of expressions.
 * @author J. 'gex' Finis
 *
 */
public class TypeRegistryTreeScanner extends NoResultTreeScanVisitor<IScope>{

	private TypeProvider tprov;
	private Environment env;
	
	public TypeRegistryTreeScanner(Environment env) {
		this.env = env;
		tprov = env.typeProvider;
		
	}
	
	/**
	 * Creates a package and registers it in its parent package, or gets
	 * the package if it already exists.
	 * @param id
	 * @return
	 */
	private Package buildPackageFromName(CompilationUnitIdentifierNode id) {
		if(id == null) return env.getDefaultPackage();
		String name = id.getName().getId();
		
		//Recursively build or fetch parent package
		//We can cast to field access here safely since a package decl only consists of field accesses
		Package parent;
		CompilationUnitIdentifierNode prefix = id.getPrefix();
		if(prefix == null){
			parent = env.getDefaultPackage();
		} else {
			parent = buildPackageFromName(prefix);
		}
		
		//Get or create the package in its parent
		return parent.addOrGetSubpackage(name,id);
	}
		
	/**
	 * Builds a package scope from a package declaration.
	 * @param packageDecl
	 * @return
	 */
	private Package buildPackageFromDecl(PackageDeclNode packageDecl) {
		if(packageDecl==null){
			return env.getDefaultPackage();
		}
		
		CompilationUnitIdentifierNode packageName = packageDecl.getPackageName();
		return buildPackageFromName(packageName);
	}


	@Override
	public void visit(SourceFileNode andromedaFile, IScope s) {
		PackageDeclNode packageDecl = andromedaFile.getPackageDecl();
		Package p;
		p = buildPackageFromDecl(andromedaFile.getPackageDecl());
		
		s = new FileScope(andromedaFile.getSourceInfo().getFileId()+"",andromedaFile.getSourceInfo().getType(),p);
		andromedaFile.setSemantics(s);
		andromedaFile.childrenAccept(this,s);
	}
	


	@Override
	public void visit(ClassDeclNode classDeclaration, IScope scope) {
		tprov.registerClass(classDeclaration,scope);
	}
	
	@Override
	public void visit(InterfaceDeclNode interfaceDeclaration, IScope scope) {
		tprov.registerInterface(interfaceDeclaration,scope);
	}
	
	@Override
	public void visit(StructDeclNode structDeclaration, IScope scope) {
		tprov.registerStruct(structDeclaration,scope);
	}
	

	

	
	@Override
	public void visit(TypeAliasDeclNode typeAlias, IScope scope) {
		tprov.registerTypeAlias(typeAlias,scope);
	}
	
	@Override
	public void visit(TypeExtensionDeclNode typeExtension, IScope scope) {
		tprov.registerTypeExtension(typeExtension,scope);
	}
	
	//Stop scan if one of these constructs is reached (since they cannot contain types at the moment)
	public void visit(MethodDeclNode mdecl, FileScope scope){}
	public void visit(StaticInitDeclNode sdecl, FileScope scope){}
	public void visit(FieldDeclNode mdecl, FileScope scope){}
	


}
