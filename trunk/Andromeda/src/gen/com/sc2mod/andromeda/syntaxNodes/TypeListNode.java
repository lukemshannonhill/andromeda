/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;
import com.sc2mod.andromeda.syntaxNodes.util.*;

import java.util.Vector;
import java.util.Enumeration;

public class TypeListNode extends SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public TypeListNode() {
    items = new Vector();
  }

  public TypeListNode(TypeNode anItem) {
    this();
    append(anItem);
  }

  public TypeListNode append(TypeNode anItem) {
    if (anItem == null) return this;
    anItem.setParent(this);
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public TypeNode elementAt(int index) {
    return (TypeNode) items.elementAt(index);
  }

  public void setElementAt(TypeNode item, int index) {
    item.setParent(this);
    items.setElementAt(item, index);
  }

  public void insertElementAt(TypeNode item, int index) {
    item.setParent(this);
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(TypeNode item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(TypeNode item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TypeListNode (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append(((TypeNode) items.elementAt(i)).toString("  "+tab));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [TypeListNode]");
    return buffer.toString();
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
  public void childrenAccept(VoidVisitor visitor) {
    for (int i = 0, size = size(); i < size; i++){
    TypeNode val = elementAt(i);
      if (val!=null) val.accept(visitor);}
  }

	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
  public <P,R> R childrenAccept(Visitor<P,R> visitor,P state) {
    R result$ = null;
    for (int i = 0, size = size(); i < size; i++){
      TypeNode val = elementAt(i);
      if (val!=null) result$ = visitor.reduce(result$, val.accept(visitor,state));
    }
    return result$;
  }

	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public <P> void childrenAccept(NoResultVisitor<P> visitor,P state) {
    for (int i = 0, size = size(); i < size; i++){
      TypeNode val = elementAt(i);
      if (val!=null) val.accept(visitor,state);
    }
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

}
