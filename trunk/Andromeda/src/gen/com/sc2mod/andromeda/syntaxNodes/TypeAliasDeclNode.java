/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class TypeAliasDeclNode extends GlobalStructureNode {

  private AnnotationListNode annotations;
  private ModifierListNode modifiers;
  private String name;
  private TypeNode enrichedType;

  public TypeAliasDeclNode (AnnotationListNode annotations, ModifierListNode modifiers, String name, TypeNode enrichedType) {
    this.annotations = annotations;
    if (annotations != null) annotations.setParent(this);
    this.modifiers = modifiers;
    if (modifiers != null) modifiers.setParent(this);
    this.name = name;
    this.enrichedType = enrichedType;
    if (enrichedType != null) enrichedType.setParent(this);
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public AnnotationListNode getAnnotations() {
    return annotations;
  }

  public void setAnnotations(AnnotationListNode annotations) {
    this.annotations = annotations;
  }

  public ModifierListNode getModifiers() {
    return modifiers;
  }

  public void setModifiers(ModifierListNode modifiers) {
    this.modifiers = modifiers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TypeNode getEnrichedType() {
    return enrichedType;
  }

  public void setEnrichedType(TypeNode enrichedType) {
    this.enrichedType = enrichedType;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (annotations != null) annotations.accept(visitor);
		if (modifiers != null) modifiers.accept(visitor);
		if (enrichedType != null) enrichedType.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (annotations != null) result$ = visitor.reduce(result$,annotations.accept(visitor,state));
		if (modifiers != null) result$ = visitor.reduce(result$,modifiers.accept(visitor,state));
		if (enrichedType != null) result$ = visitor.reduce(result$,enrichedType.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (annotations != null) annotations.accept(visitor,state);
		if (modifiers != null) modifiers.accept(visitor,state);
		if (enrichedType != null) enrichedType.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TypeAliasDeclNode(\n");
      if (annotations != null)
        buffer.append(annotations.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (modifiers != null)
        buffer.append(modifiers.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+name);
    buffer.append("\n");
      if (enrichedType != null)
        buffer.append(enrichedType.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TypeAliasDeclNode]");
    return buffer.toString();
  }
}
