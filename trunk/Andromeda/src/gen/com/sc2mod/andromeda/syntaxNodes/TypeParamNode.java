/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class TypeParamNode extends SyntaxNode {

  private SyntaxNode parent;
  private String name;
  private Object typeBound;

  public TypeParamNode (String name, Object typeBound) {
    this.name = name;
    this.typeBound = typeBound;
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getTypeBound() {
    return typeBound;
  }

  public void setTypeBound(Object typeBound) {
    this.typeBound = typeBound;
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

	public void childrenAccept(VoidVisitor visitor){
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
	}
  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TypeParamNode(\n");
    buffer.append("  "+tab+name);
    buffer.append("\n");
    buffer.append("  "+tab+typeBound);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TypeParamNode]");
    return buffer.toString();
  }
}
