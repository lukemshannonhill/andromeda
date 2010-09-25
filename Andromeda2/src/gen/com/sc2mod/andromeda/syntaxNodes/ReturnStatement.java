/*
 * Generated by classgen, version 1.5
 * 25.09.10 11:38
 */
package com.sc2mod.andromeda.syntaxNodes;

public class ReturnStatement extends Statement {

  private Expression result;

  public ReturnStatement (Expression result) {
    this.result = result;
    if (result != null) result.setParent(this);
  }

  public Expression getResult() {
    return result;
  }

  public void setResult(Expression result) {
    this.result = result;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (result != null) result.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (result != null) result.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (result != null) result.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ReturnStatement(\n");
      if (result != null)
        buffer.append(result.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ReturnStatement]");
    return buffer.toString();
  }
}
