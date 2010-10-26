/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class IfStmtNode extends StmtNode {

  private ExprNode condition;
  private StmtNode thenStatement;
  private StmtNode elseStatement;

  public IfStmtNode (ExprNode condition, StmtNode thenStatement, StmtNode elseStatement) {
    this.condition = condition;
    if (condition != null) condition.setParent(this);
    this.thenStatement = thenStatement;
    if (thenStatement != null) thenStatement.setParent(this);
    this.elseStatement = elseStatement;
    if (elseStatement != null) elseStatement.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public ExprNode getCondition() {
    return condition;
  }

  public void setCondition(ExprNode condition) {
    this.condition = condition;
  }

  public StmtNode getThenStatement() {
    return thenStatement;
  }

  public void setThenStatement(StmtNode thenStatement) {
    this.thenStatement = thenStatement;
  }

  public StmtNode getElseStatement() {
    return elseStatement;
  }

  public void setElseStatement(StmtNode elseStatement) {
    this.elseStatement = elseStatement;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (condition != null) condition.accept(visitor);
		if (thenStatement != null) thenStatement.accept(visitor);
		if (elseStatement != null) elseStatement.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (condition != null) result$ = visitor.reduce(result$,condition.accept(visitor,state));
		if (thenStatement != null) result$ = visitor.reduce(result$,thenStatement.accept(visitor,state));
		if (elseStatement != null) result$ = visitor.reduce(result$,elseStatement.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (condition != null) condition.accept(visitor,state);
		if (thenStatement != null) thenStatement.accept(visitor,state);
		if (elseStatement != null) elseStatement.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("IfStmtNode(\n");
      if (condition != null)
        buffer.append(condition.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (thenStatement != null)
        buffer.append(thenStatement.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (elseStatement != null)
        buffer.append(elseStatement.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [IfStmtNode]");
    return buffer.toString();
  }
}