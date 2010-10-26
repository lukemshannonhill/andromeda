/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class ThisExprNode extends ExprNode {

  private ExprNode thisClassName;

  public ThisExprNode (ExprNode thisClassName) {
    this.thisClassName = thisClassName;
    if (thisClassName != null) thisClassName.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public ExprNode getThisClassName() {
    return thisClassName;
  }

  public void setThisClassName(ExprNode thisClassName) {
    this.thisClassName = thisClassName;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (thisClassName != null) thisClassName.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (thisClassName != null) result$ = visitor.reduce(result$,thisClassName.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (thisClassName != null) thisClassName.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ThisExprNode(\n");
      if (thisClassName != null)
        buffer.append(thisClassName.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ThisExprNode]");
    return buffer.toString();
  }
}