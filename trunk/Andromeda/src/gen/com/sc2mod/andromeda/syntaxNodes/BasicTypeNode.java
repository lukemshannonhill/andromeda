/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes;

import com.sc2mod.andromeda.syntaxNodes.util.*;

public class BasicTypeNode extends TypeNode {

  private String name;

  public BasicTypeNode (String name) {
    this.name = name;
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

	public void childrenAccept(VoidVisitor visitor){
	}

	public <P,R> R childrenAccept(Visitor<P,R> visitor,P state){
		R result$ = null;
		return result$;
	}
	public <P> void childrenAccept(NoResultVisitor<P> visitor,P state){
	}
  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("BasicTypeNode(\n");
    buffer.append("  "+tab+name);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [BasicTypeNode]");
    return buffer.toString();
  }
}
