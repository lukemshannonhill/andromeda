/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class AssignmentExprNode extends ExprNode {

  private ExprNode leftExpression;
  private AssignOpSE assignOp;
  private ExprNode rightExpression;

  public AssignmentExprNode (ExprNode leftExpression, AssignOpSE assignOp, ExprNode rightExpression) {
    this.leftExpression = leftExpression;
    if (leftExpression != null) leftExpression.setParent(this);
    this.assignOp = assignOp;
    this.rightExpression = rightExpression;
    if (rightExpression != null) rightExpression.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public ExprNode getLeftExpression() {
    return leftExpression;
  }

  public void setLeftExpression(ExprNode leftExpression) {
    this.leftExpression = leftExpression;
  }

  public AssignOpSE getAssignOp() {
    return assignOp;
  }

  public void setAssignOp(AssignOpSE assignOp) {
    this.assignOp = assignOp;
  }

  public ExprNode getRightExpression() {
    return rightExpression;
  }

  public void setRightExpression(ExprNode rightExpression) {
    this.rightExpression = rightExpression;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (leftExpression != null) leftExpression.accept(visitor);
		if (rightExpression != null) rightExpression.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (leftExpression != null) result$ = visitor.reduce(result$,leftExpression.accept(visitor,state));
		if (rightExpression != null) result$ = visitor.reduce(result$,rightExpression.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (leftExpression != null) leftExpression.accept(visitor,state);
		if (rightExpression != null) rightExpression.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("AssignmentExprNode(\n");
      if (leftExpression != null)
        buffer.append(leftExpression.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+assignOp);
    buffer.append("\n");
      if (rightExpression != null)
        buffer.append(rightExpression.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [AssignmentExprNode]");
    return buffer.toString();
  }
}
