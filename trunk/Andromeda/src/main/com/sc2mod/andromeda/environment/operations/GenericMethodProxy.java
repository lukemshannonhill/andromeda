package com.sc2mod.andromeda.environment.operations;

import java.util.ArrayList;
import java.util.List;

import com.sc2mod.andromeda.environment.ModifierSet;
import com.sc2mod.andromeda.environment.Signature;
import com.sc2mod.andromeda.environment.access.OperationAccess;
import com.sc2mod.andromeda.environment.annotations.AnnotationSet;
import com.sc2mod.andromeda.environment.scopes.IScope;
import com.sc2mod.andromeda.environment.scopes.Visibility;
import com.sc2mod.andromeda.environment.types.IType;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.environment.variables.ImplicitParamDecl;
import com.sc2mod.andromeda.environment.variables.LocalVarDecl;
import com.sc2mod.andromeda.environment.variables.ParamDecl;
import com.sc2mod.andromeda.environment.visitors.NoResultSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.ParameterSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.VoidSemanticsVisitor;
import com.sc2mod.andromeda.syntaxNodes.ReturnStmtNode;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;

public class GenericMethodProxy extends Operation {

	private Operation method;
	private Signature signature;
	private IType returnType;
	
	public GenericMethodProxy(Operation method, Signature signature, IType returnType) {
		this.method = method;
		this.signature = signature;
		this.returnType = returnType;
	}
	
	@Override
	public IType getReturnType() {
		return returnType;
	}
	
	@Override
	public String getDescription() {
		IType t = getContainingType();
		if(t == null) return "method proxy " + getUid();
		return "method proxy " + getContainingType().getUid() + "." + getUid();
	}
	
	@Override
	public Signature getSignature() {
		return signature;
	}

	public Operation getWrappedMethod() {
		return method;
	}

	//***** Delegates to the actual method ****

	/**
	 * All GenericMethodProxies are equal to the base method.
	 */
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj)) return true;
		if(obj instanceof GenericMethodProxy) {
			return method.equals(((GenericMethodProxy)obj).method);
		} else if(obj instanceof Method) {
			return method.equals(obj);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return method.hashCode();
	}

	@Override
	public IType getContainingType() {
		return method.getContainingType();
	}

	@Override
	public OperationType getOperationType() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isStaticElement() {
		return method.isStaticElement();
	}

	@Override
	public void addImplicitLocals(ArrayList<LocalVarDecl> vars) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void addInline() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void addInvocation() {
		method.addInvocation();
	}

	@Override
	public void addReturnStmt(ReturnStmtNode r) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean flowReachesEnd() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public SyntaxNode getDefinition() {
		return method.getDefinition();
	}

	@Override
	public synchronized String getGeneratedName() {
		return method.getGeneratedName();
	}

	@Override
	public int getInvocationCount() {
		return method.getInvocationCount();
	}

	@Override
	public LocalVarDecl[] getLocals() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public ParamDecl[] getParams() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public OperationAccess getPointerDecl(TypeProvider typeProvider) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public IScope getScope() {
		return method.getScope();
	}

	@Override
	public String getUid() {
		return method.getUid();
	}

	@Override
	public Visibility getVisibility() {
		return method.getVisibility();
	}


	@Override
	public boolean hasBody() {
		return method.hasBody();
	}

	@Override
	public boolean isCreateCode() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setCreateCode(boolean createCode) {
		method.setCreateCode(createCode);
	}

	@Override
	public void setFlowReachesEnd(boolean b) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public synchronized void setGeneratedName(String generatedName) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setLocals(LocalVarDecl[] locals) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setReturnType(IType returnType) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public String getElementTypeName() {
		return method.getElementTypeName();
	}
	

	@Override
	public List<ImplicitParamDecl> getImplicitParams() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}
	
	@Override
	public OverrideInformation getOverrideInformation() {
		return method.getOverrideInformation();
	}


	@Override
	public AnnotationSet getAnnotations(boolean createIfNotExistant) {
		return method.getAnnotations(createIfNotExistant);
	}

	public void accept(VoidSemanticsVisitor visitor) { visitor.visit(this); }
	public <P> void accept(NoResultSemanticsVisitor<P> visitor,P state) { visitor.visit(this,state); }
	public <P,R> R accept(ParameterSemanticsVisitor<P,R> visitor,P state) { return visitor.visit(this,state); }

	@Override
	public ModifierSet getModifiers() {
		return method.getModifiers();
	}
}
