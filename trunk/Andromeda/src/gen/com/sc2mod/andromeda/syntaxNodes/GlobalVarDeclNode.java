/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class GlobalVarDeclNode extends GlobalStructureNode {

  private FieldDeclNode fieldDecl;

  public GlobalVarDeclNode (FieldDeclNode fieldDecl) {
    this.fieldDecl = fieldDecl;
    if (fieldDecl != null) fieldDecl.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public FieldDeclNode getFieldDecl() {
    return fieldDecl;
  }

  public void setFieldDecl(FieldDeclNode fieldDecl) {
    this.fieldDecl = fieldDecl;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (fieldDecl != null) fieldDecl.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (fieldDecl != null) result$ = visitor.reduce(result$,fieldDecl.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (fieldDecl != null) fieldDecl.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("GlobalVarDeclNode(\n");
      if (fieldDecl != null)
        buffer.append(fieldDecl.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [GlobalVarDeclNode]");
    return buffer.toString();
  }
}
