/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class ArrayTypeNode extends TypeNode {

  private TypeNode wrappedType;
  private ExprListNode dimensions;

  public ArrayTypeNode (TypeNode wrappedType, ExprListNode dimensions) {
    this.wrappedType = wrappedType;
    if (wrappedType != null) wrappedType.setParent(this);
    this.dimensions = dimensions;
    if (dimensions != null) dimensions.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public TypeNode getWrappedType() {
    return wrappedType;
  }

  public void setWrappedType(TypeNode wrappedType) {
    this.wrappedType = wrappedType;
  }

  public ExprListNode getDimensions() {
    return dimensions;
  }

  public void setDimensions(ExprListNode dimensions) {
    this.dimensions = dimensions;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (wrappedType != null) wrappedType.accept(visitor);
		if (dimensions != null) dimensions.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (wrappedType != null) result$ = visitor.reduce(result$,wrappedType.accept(visitor,state));
		if (dimensions != null) result$ = visitor.reduce(result$,dimensions.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (wrappedType != null) wrappedType.accept(visitor,state);
		if (dimensions != null) dimensions.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ArrayTypeNode(\n");
      if (wrappedType != null)
        buffer.append(wrappedType.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (dimensions != null)
        buffer.append(dimensions.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ArrayTypeNode]");
    return buffer.toString();
  }
}