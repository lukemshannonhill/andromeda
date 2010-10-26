package com.sc2mod.andromeda.environment.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sc2mod.andromeda.environment.Signature;
import com.sc2mod.andromeda.syntaxNodes.AnnotationNode;
import com.sc2mod.andromeda.syntaxNodes.ReturnStmtNode;
import com.sc2mod.andromeda.syntaxNodes.StmtNode;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;

/**
 * Like GenericFunctionProxy, but for methods.
 * @author XPilot
 */
import com.sc2mod.andromeda.environment.scopes.FileScope;
import com.sc2mod.andromeda.environment.scopes.Scope;
import com.sc2mod.andromeda.environment.scopes.Visibility;
import com.sc2mod.andromeda.environment.types.RecordType;
import com.sc2mod.andromeda.environment.types.Type;
import com.sc2mod.andromeda.environment.types.TypeProvider;
import com.sc2mod.andromeda.environment.variables.FuncPointerDecl;
import com.sc2mod.andromeda.environment.variables.LocalVarDecl;
import com.sc2mod.andromeda.environment.variables.ParamDecl;
import com.sc2mod.andromeda.environment.visitors.VoidSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.NoResultSemanticsVisitor;
import com.sc2mod.andromeda.environment.visitors.ParameterSemanticsVisitor;
//FIXME Why are here two classes GenericMethodProxz and GenericFunctionProxy??
public class GenericMethodProxy extends Method {

	private Method method;
	private Signature signature;
	private Type returnType;
	
	public GenericMethodProxy(Method method, Signature signature, Type returnType) {
		this.method = method;
		this.signature = signature;
		this.returnType = returnType;
	}
	
	@Override
	public Type getReturnType() {
		return returnType;
	}
	
	@Override
	public String getDescription() {
		Type t = getContainingType();
		if(t == null) return "method proxy " + getUid();
		return "method proxy " + getContainingType().getUid() + "." + getUid();
	}
	
	@Override
	public Signature getSignature() {
		return signature;
	}

	public Method getWrappedMethod() {
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
	public void addOverride(Operation overrider) {
		method.addOverride(overrider);
	}

	@Override
	public Type getContainingType() {
		return method.getContainingType();
	}

	@Override
	public int getCurVirtualCallChildIndex() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public int getOperationType() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public int getNextVirtualCallChildIndex() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public Operation getOverridenMethod() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public ArrayList<Operation> getOverridingMethods() {
		return method.getOverridingMethods();
	}

	@Override
	public String getVirtualCaller() {
		return method.getVirtualCaller();
	}

	@Override
	public int getVirtualCallIndex() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public int getVirtualTableIndex() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isAbstract() {
		return method.isAbstract();
	}

	@Override
	public boolean isCalledVirtually() {
		return method.isCalledVirtually();
	}

	@Override
	public boolean isMember() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isOverridden() {
		return method.isOverridden();
	}

	@Override
	public boolean isStatic() {
		return method.isStatic();
	}

	@Override
	public void registerVirtualCall() {
		method.registerVirtualCall();
	}

	@Override
	public void setAbstract() {
		method.setAbstract();
	}

	@Override
	public void setNative() {
		method.setNative();
	}

	@Override
	protected void setOverriddenMethod(Method method) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setStatic() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setVirtualCallerName(String virtualCallerName) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setVirtualCallIndex(int virtualCallIndex) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setVirtualTableIndex(int virtualTableIndex) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean usesThis() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
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
	public void afterAnnotationsProcessed() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean flowReachesEnd() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public HashSet<String> getAllowedAnnotations() {
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
	public int getIndex() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
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
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public ParamDecl[] getParams() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public FuncPointerDecl getPointerDecl(TypeProvider typeProvider) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public Scope getScope() {
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
	public boolean hasAnnotation(String name) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean hasBody() {
		return method.hasBody();
	}

	@Override
	public boolean isConst() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isCreateCode() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isFinal() {
		return method.isFinal();
	}

	@Override
	public boolean isInline() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isMarked() {
		return method.isMarked();
	}

	@Override
	public boolean isNative() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isOverride() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public boolean isStrcall() {
		return method.isStrcall();
	}

	@Override
	public void mark() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}


	@Override
	public void setAnnotationTable(HashMap<String, AnnotationNode> annotations) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setConst() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setCreateCode(boolean createCode) {
		method.setCreateCode(createCode);
	}

	@Override
	public void setFinal() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
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
	public void setIndex(int index) {
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
	public void setOverride() {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setReturnType(Type returnType) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}

	@Override
	public void setVisibility(Visibility visibility) {
		// TODO Auto-generated method stub
		throw new Error("Not implemented!");
	}
	
	@Override
	public String getElementTypeName() {
		return method.getElementTypeName();
	}
	
	public void accept(VoidSemanticsVisitor visitor) { visitor.visit(this); }
	public <P> void accept(NoResultSemanticsVisitor<P> visitor,P state) { visitor.visit(this,state); }
	public <P,R> R accept(ParameterSemanticsVisitor<P,R> visitor,P state) { return visitor.visit(this,state); }
}