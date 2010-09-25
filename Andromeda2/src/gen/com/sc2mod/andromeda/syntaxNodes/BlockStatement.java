/*
 * Generated by classgen, version 1.5
 * 25.09.10 11:38
 */
package com.sc2mod.andromeda.syntaxNodes;

public class BlockStatement extends Statement {

  private StatementList statements;

  public BlockStatement (StatementList statements) {
    this.statements = statements;
    if (statements != null) statements.setParent(this);
  }

  public StatementList getStatements() {
    return statements;
  }

  public void setStatements(StatementList statements) {
    this.statements = statements;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (statements != null) statements.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (statements != null) statements.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (statements != null) statements.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("BlockStatement(\n");
      if (statements != null)
        buffer.append(statements.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [BlockStatement]");
    return buffer.toString();
  }
}
