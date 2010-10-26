/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

import com.sc2mod.andromeda.notifications.InternalProgramError;
import com.sc2mod.andromeda.environment.SemanticsElement;
import com.sc2mod.andromeda.environment.types.Enrichment;

public class EnrichDeclNode extends GlobalStructureNode {

  private AnnotationListNode annotations;
  private ModifierListNode modifiers;
  private TypeNode enrichedType;
  private MemberDeclListNode body;

  public EnrichDeclNode (AnnotationListNode annotations, ModifierListNode modifiers, TypeNode enrichedType, MemberDeclListNode body) {
    this.annotations = annotations;
    if (annotations != null) annotations.setParent(this);
    this.modifiers = modifiers;
    if (modifiers != null) modifiers.setParent(this);
    this.enrichedType = enrichedType;
    if (enrichedType != null) enrichedType.setParent(this);
    this.body = body;
    if (body != null) body.setParent(this);
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

  public TypeNode getEnrichedType() {
    return enrichedType;
  }

  public void setEnrichedType(TypeNode enrichedType) {
    this.enrichedType = enrichedType;
  }

  public MemberDeclListNode getBody() {
    return body;
  }

  public void setBody(MemberDeclListNode body) {
    this.body = body;
  }

  private Enrichment semantics;
  @Override
  public void setSemantics(SemanticsElement semantics){
  	if(!(semantics instanceof Enrichment)) throw new InternalProgramError(this,"Trying to assign semantics of type "
  				+ semantics.getClass().getSimpleName() + " to node " + this.getClass().getSimpleName());
  	this.semantics = (Enrichment)semantics;
  }
  @Override
  public Enrichment getSemantics(){
  	return semantics;
  }

	public void childrenAccept(VoidVisitor visitor){
		if (annotations != null) annotations.accept(visitor);
		if (modifiers != null) modifiers.accept(visitor);
		if (enrichedType != null) enrichedType.accept(visitor);
		if (body != null) body.accept(visitor);
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		if (annotations != null) result$ = visitor.reduce(result$,annotations.accept(visitor,state));
		if (modifiers != null) result$ = visitor.reduce(result$,modifiers.accept(visitor,state));
		if (enrichedType != null) result$ = visitor.reduce(result$,enrichedType.accept(visitor,state));
		if (body != null) result$ = visitor.reduce(result$,body.accept(visitor,state));
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
		if (annotations != null) annotations.accept(visitor,state);
		if (modifiers != null) modifiers.accept(visitor,state);
		if (enrichedType != null) enrichedType.accept(visitor,state);
		if (body != null) body.accept(visitor,state);
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("EnrichDeclNode(\n");
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
      if (enrichedType != null)
        buffer.append(enrichedType.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (body != null)
        buffer.append(body.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [EnrichDeclNode]");
    return buffer.toString();
  }
}