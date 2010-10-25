/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class UnOpExprNode extends ExprNode {

  private ExprNode expression;
  private UnOpSE unOp;

  public UnOpExprNode (ExprNode expression, UnOpSE unOp) {
    this.expression = expression;
    if (expression != null) expression.setParent(this);
    this.unOp = unOp;
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public ExprNode getExpression() {
    return expression;
  }

  public void setExpression(ExprNode expression) {
    this.expression = expression;
  }

  public UnOpSE getUnOp() {
    return unOp;
  }

  public void setUnOp(UnOpSE unOp) {
    this.unOp = unOp;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (expression != null) expression.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (expression != null) result$ = visitor.reduce(result$,expression.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (expression != null) expression.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("UnOpExprNode(\n");
      if (expression != null)
        buffer.append(expression.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+unOp);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [UnOpExprNode]");
    return buffer.toString();
  }
}
