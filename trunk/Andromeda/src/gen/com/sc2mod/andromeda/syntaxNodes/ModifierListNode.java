/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;
import com.sc2mod.andromeda.syntaxNodes.util.*;

import java.util.Vector;
import java.util.Enumeration;

public class ModifierListNode extends SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public ModifierListNode() {
    items = new Vector();
  }

  public ModifierListNode(ModifierSE anItem) {
    this();
    append(anItem);
  }

  public ModifierListNode append(ModifierSE anItem) {
    if (anItem == null) return this;
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public ModifierSE elementAt(int index) {
    return (ModifierSE) items.elementAt(index);
  }

  public void setElementAt(ModifierSE item, int index) {
    items.setElementAt(item, index);
  }

  public void insertElementAt(ModifierSE item, int index) {
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(ModifierSE item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(ModifierSE item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ModifierListNode (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append("  "+tab+items.elementAt(i));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [ModifierListNode]");
    return buffer.toString();
  }

	public void accept(VoidVisitor visitor) { visitor.visit(this); }
  public void childrenAccept(VoidVisitor visitor) {
  }

	public <P,R> R accept(Visitor<P,R> visitor,P state) { return visitor.visit(this,state); }
  public <P,R> R childrenAccept(Visitor<P,R> visitor,P state) {
    R result$ = null;
    return result$;
  }

	public <P> void accept(NoResultVisitor<P> visitor,P state) { visitor.visit(this,state); }
  public <P> void childrenAccept(NoResultVisitor<P> visitor,P state) {
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

}
