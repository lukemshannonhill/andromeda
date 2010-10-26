/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class QualifiedTypeNode extends TypeNode {

  private FieldAccessExprNode qualifiedName;
  private TypeListNode typeArguments;

  public QualifiedTypeNode (FieldAccessExprNode qualifiedName, TypeListNode typeArguments) {
    this.qualifiedName = qualifiedName;
    if (qualifiedName != null) qualifiedName.setParent(this);
    this.typeArguments = typeArguments;
    if (typeArguments != null) typeArguments.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public FieldAccessExprNode getQualifiedName() {
    return qualifiedName;
  }

  public void setQualifiedName(FieldAccessExprNode qualifiedName) {
    this.qualifiedName = qualifiedName;
  }

  public TypeListNode getTypeArguments() {
    return typeArguments;
  }

  public void setTypeArguments(TypeListNode typeArguments) {
    this.typeArguments = typeArguments;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (qualifiedName != null) qualifiedName.accept(visitor);
		if (typeArguments != null) typeArguments.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (qualifiedName != null) result$ = visitor.reduce(result$,qualifiedName.accept(visitor,state));
		if (typeArguments != null) result$ = visitor.reduce(result$,typeArguments.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (qualifiedName != null) qualifiedName.accept(visitor,state);
		if (typeArguments != null) typeArguments.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("QualifiedTypeNode(\n");
      if (qualifiedName != null)
        buffer.append(qualifiedName.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (typeArguments != null)
        buffer.append(typeArguments.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [QualifiedTypeNode]");
    return buffer.toString();
  }
}