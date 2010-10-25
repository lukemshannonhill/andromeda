/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;


public abstract class ExprNode extends SyntaxNode {

  private com.sc2mod.andromeda.environment.types.Type inferedType;
  private boolean constant;
  private com.sc2mod.andromeda.vm.data.DataObject value;
  private SyntaxNode parent;

  public ExprNode getLeftExpression() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setLeftExpression(ExprNode leftExpression) {
    throw new ClassCastException("tried to call abstract method");
  }

  public AssignOpSE getAssignOp() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setAssignOp(AssignOpSE assignOp) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getRightExpression() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setRightExpression(ExprNode rightExpression) {
    throw new ClassCastException("tried to call abstract method");
  }

  public String getName() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setName(String name) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getRightExpression2() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setRightExpression2(ExprNode rightExpression2) {
    throw new ClassCastException("tried to call abstract method");
  }

  public BinOpSE getBinOp() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setBinOp(BinOpSE binOp) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getExpression() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setExpression(ExprNode expression) {
    throw new ClassCastException("tried to call abstract method");
  }

  public UnOpSE getUnOp() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setUnOp(UnOpSE unOp) {
    throw new ClassCastException("tried to call abstract method");
  }

  public TypeNode getType() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setType(TypeNode type) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getSuperClassName() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setSuperClassName(ExprNode superClassName) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getThisClassName() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setThisClassName(ExprNode thisClassName) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprNode getPrefix() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setPrefix(ExprNode prefix) {
    throw new ClassCastException("tried to call abstract method");
  }

  public String getFuncName() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setFuncName(String funcName) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprListNode getArguments() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setArguments(ExprListNode arguments) {
    throw new ClassCastException("tried to call abstract method");
  }

  public SpecialInvocationSE getSpecial() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setSpecial(SpecialInvocationSE special) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ExprListNode getDefinedDimensions() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setDefinedDimensions(ExprListNode definedDimensions) {
    throw new ClassCastException("tried to call abstract method");
  }

  public int getAdditionalDimensions() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setAdditionalDimensions(int additionalDimensions) {
    throw new ClassCastException("tried to call abstract method");
  }

  public ArrayInitNode getArrayInitializer() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setArrayInitializer(ArrayInitNode arrayInitializer) {
    throw new ClassCastException("tried to call abstract method");
  }

  public MemberDeclListNode getClassBody() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setClassBody(MemberDeclListNode classBody) {
    throw new ClassCastException("tried to call abstract method");
  }

  public LiteralNode getLiteral() {
    throw new ClassCastException("tried to call abstract method");
  }

  public void setLiteral(LiteralNode literal) {
    throw new ClassCastException("tried to call abstract method");
  }

  public com.sc2mod.andromeda.environment.types.Type getInferedType() {
    return inferedType;
  }

  public void setInferedType(com.sc2mod.andromeda.environment.types.Type inferedType) {
    this.inferedType = inferedType;
  }

  public boolean isConstant() {
    return constant;
  }

  public void setConstant(boolean constant) {
    this.constant = constant;
  }

  public com.sc2mod.andromeda.vm.data.DataObject getValue() {
    return value;
  }

  public void setValue(com.sc2mod.andromeda.vm.data.DataObject value) {
    this.value = value;
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public String toString() {
    return toString("");
  }

  public abstract String toString(String tab);
}
