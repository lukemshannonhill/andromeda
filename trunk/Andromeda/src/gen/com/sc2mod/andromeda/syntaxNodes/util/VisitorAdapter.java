/*
 * Generated by classgen, version 1.5
 * 24.10.10 21:59
 */
package com.sc2mod.andromeda.syntaxNodes.util;
import com.sc2mod.andromeda.syntaxNodes.*;

public abstract class VisitorAdapter<P,R> implements Visitor<P,R> {

  public R visit(GlobalStructureListNode globalStructureListNode,P state) { return visitDefault(globalStructureListNode,state); }
  public R visit(ModifierListNode modifierListNode,P state) { return visitDefault(modifierListNode,state); }
  public R visit(AnnotationListNode annotationListNode,P state) { return visitDefault(annotationListNode,state); }
  public R visit(TypeParamListNode typeParamListNode,P state) { return visitDefault(typeParamListNode,state); }
  public R visit(TypeListNode typeListNode,P state) { return visitDefault(typeListNode,state); }
  public R visit(MemberDeclListNode memberDeclListNode,P state) { return visitDefault(memberDeclListNode,state); }
  public R visit(VarDeclListNode varDeclListNode,P state) { return visitDefault(varDeclListNode,state); }
  public R visit(ParameterListNode parameterListNode,P state) { return visitDefault(parameterListNode,state); }
  public R visit(VarInitializerListNode varInitializerListNode,P state) { return visitDefault(varInitializerListNode,state); }
  public R visit(StmtListNode stmtListNode,P state) { return visitDefault(stmtListNode,state); }
  public R visit(ExprListNode exprListNode,P state) { return visitDefault(exprListNode,state); }
  public R visit(IdentifierNode identifierNode,P state) { return visitDefault(identifierNode,state); }
  public R visit(SourceFileNode sourceFileNode,P state) { return visitDefault(sourceFileNode,state); }
  public R visit(PackageDeclNode packageDeclNode,P state) { return visitDefault(packageDeclNode,state); }
  public R visit(LiteralNode literalNode,P state) { return visitDefault(literalNode,state); }
  public R visit(AnnotationNode annotationNode,P state) { return visitDefault(annotationNode,state); }
  public R visit(TypeParamNode typeParamNode,P state) { return visitDefault(typeParamNode,state); }
  public R visit(AccessorBodyTransNode accessorBodyTransNode,P state) { return visitDefault(accessorBodyTransNode,state); }
  public R visit(MethodHeaderNode methodHeaderNode,P state) { return visitDefault(methodHeaderNode,state); }
  public R visit(MethodDeclaratorTransNode methodDeclaratorTransNode,P state) { return visitDefault(methodDeclaratorTransNode,state); }
  public R visit(ParameterNode parameterNode,P state) { return visitDefault(parameterNode,state); }
  public R visit(ArrayInitNode arrayInitNode,P state) { return visitDefault(arrayInitNode,state); }
  public R visit(LocalVarDeclNode localVarDeclNode,P state) { return visitDefault(localVarDeclNode,state); }
  public R visit(ArrayDimensionsNode arrayDimensionsNode,P state) { return visitDefault(arrayDimensionsNode,state); }
  public R visit(GlobalStructureNode globalStructureNode,P state) { return visitDefault(globalStructureNode,state); }
  public R visit(ClassDeclNode classDeclNode,P state) { return visitDefault(classDeclNode,state); }
  public R visit(EnrichDeclNode enrichDeclNode,P state) { return visitDefault(enrichDeclNode,state); }
  public R visit(GlobalFuncDeclNode globalFuncDeclNode,P state) { return visitDefault(globalFuncDeclNode,state); }
  public R visit(GlobalVarDeclNode globalVarDeclNode,P state) { return visitDefault(globalVarDeclNode,state); }
  public R visit(GlobalStaticInitDeclNode globalStaticInitDeclNode,P state) { return visitDefault(globalStaticInitDeclNode,state); }
  public R visit(InterfaceDeclNode interfaceDeclNode,P state) { return visitDefault(interfaceDeclNode,state); }
  public R visit(StructDeclNode structDeclNode,P state) { return visitDefault(structDeclNode,state); }
  public R visit(IncludeNode includeNode,P state) { return visitDefault(includeNode,state); }
  public R visit(TypeAliasDeclNode typeAliasDeclNode,P state) { return visitDefault(typeAliasDeclNode,state); }
  public R visit(TypeExtensionDeclNode typeExtensionDeclNode,P state) { return visitDefault(typeExtensionDeclNode,state); }
  public R visit(InstanceLimitSetterNode instanceLimitSetterNode,P state) { return visitDefault(instanceLimitSetterNode,state); }
  public R visit(TypeNode typeNode,P state) { return visitDefault(typeNode,state); }
  public R visit(BasicTypeNode basicTypeNode,P state) { return visitDefault(basicTypeNode,state); }
  public R visit(SimpleTypeNode simpleTypeNode,P state) { return visitDefault(simpleTypeNode,state); }
  public R visit(QualifiedTypeNode qualifiedTypeNode,P state) { return visitDefault(qualifiedTypeNode,state); }
  public R visit(ArrayTypeNode arrayTypeNode,P state) { return visitDefault(arrayTypeNode,state); }
  public R visit(PointerTypeNode pointerTypeNode,P state) { return visitDefault(pointerTypeNode,state); }
  public R visit(FuncPointerTypeNode funcPointerTypeNode,P state) { return visitDefault(funcPointerTypeNode,state); }
  public R visit(MemberDeclNode memberDeclNode,P state) { return visitDefault(memberDeclNode,state); }
  public R visit(MethodDeclNode methodDeclNode,P state) { return visitDefault(methodDeclNode,state); }
  public R visit(FieldDeclNode fieldDeclNode,P state) { return visitDefault(fieldDeclNode,state); }
  public R visit(StaticInitDeclNode staticInitDeclNode,P state) { return visitDefault(staticInitDeclNode,state); }
  public R visit(AccessorDeclNode accessorDeclNode,P state) { return visitDefault(accessorDeclNode,state); }
  public R visit(VarDeclNode varDeclNode,P state) { return visitDefault(varDeclNode,state); }
  public R visit(UninitedVarDeclNode uninitedVarDeclNode,P state) { return visitDefault(uninitedVarDeclNode,state); }
  public R visit(VarAssignDeclNode varAssignDeclNode,P state) { return visitDefault(varAssignDeclNode,state); }
  public R visit(VarArrayInitDeclNode varArrayInitDeclNode,P state) { return visitDefault(varArrayInitDeclNode,state); }
  public R visit(StmtNode stmtNode,P state) { return visitDefault(stmtNode,state); }
  public R visit(BlockStmtNode blockStmtNode,P state) { return visitDefault(blockStmtNode,state); }
  public R visit(ExprStmtNode exprStmtNode,P state) { return visitDefault(exprStmtNode,state); }
  public R visit(LocalTypeDeclStmtNode localTypeDeclStmtNode,P state) { return visitDefault(localTypeDeclStmtNode,state); }
  public R visit(LocalVarDeclStmtNode localVarDeclStmtNode,P state) { return visitDefault(localVarDeclStmtNode,state); }
  public R visit(IfStmtNode ifStmtNode,P state) { return visitDefault(ifStmtNode,state); }
  public R visit(WhileStmtNode whileStmtNode,P state) { return visitDefault(whileStmtNode,state); }
  public R visit(DoWhileStmtNode doWhileStmtNode,P state) { return visitDefault(doWhileStmtNode,state); }
  public R visit(ForStmtNode forStmtNode,P state) { return visitDefault(forStmtNode,state); }
  public R visit(ForCountStmtNode forCountStmtNode,P state) { return visitDefault(forCountStmtNode,state); }
  public R visit(ForEachStmtNode forEachStmtNode,P state) { return visitDefault(forEachStmtNode,state); }
  public R visit(BreakStmtNode breakStmtNode,P state) { return visitDefault(breakStmtNode,state); }
  public R visit(ContinueStmtNode continueStmtNode,P state) { return visitDefault(continueStmtNode,state); }
  public R visit(ReturnStmtNode returnStmtNode,P state) { return visitDefault(returnStmtNode,state); }
  public R visit(DeleteStmtNode deleteStmtNode,P state) { return visitDefault(deleteStmtNode,state); }
  public R visit(ThrowStmtNode throwStmtNode,P state) { return visitDefault(throwStmtNode,state); }
  public R visit(ExplicitConsCallStmtNode explicitConsCallStmtNode,P state) { return visitDefault(explicitConsCallStmtNode,state); }
  public R visit(EmptyStmtNode emptyStmtNode,P state) { return visitDefault(emptyStmtNode,state); }
  public R visit(ExprNode exprNode,P state) { return visitDefault(exprNode,state); }
  public R visit(AssignmentExprNode assignmentExprNode,P state) { return visitDefault(assignmentExprNode,state); }
  public R visit(NameExprNode nameExprNode,P state) { return visitDefault(nameExprNode,state); }
  public R visit(FieldAccessExprNode fieldAccessExprNode,P state) { return visitDefault(fieldAccessExprNode,state); }
  public R visit(ArrayAccessExprNode arrayAccessExprNode,P state) { return visitDefault(arrayAccessExprNode,state); }
  public R visit(ConditionalExprNode conditionalExprNode,P state) { return visitDefault(conditionalExprNode,state); }
  public R visit(BinOpExprNode binOpExprNode,P state) { return visitDefault(binOpExprNode,state); }
  public R visit(UnOpExprNode unOpExprNode,P state) { return visitDefault(unOpExprNode,state); }
  public R visit(InstanceofExprNode instanceofExprNode,P state) { return visitDefault(instanceofExprNode,state); }
  public R visit(CastExprNode castExprNode,P state) { return visitDefault(castExprNode,state); }
  public R visit(SuperExprNode superExprNode,P state) { return visitDefault(superExprNode,state); }
  public R visit(ThisExprNode thisExprNode,P state) { return visitDefault(thisExprNode,state); }
  public R visit(MethodInvocationExprNode methodInvocationExprNode,P state) { return visitDefault(methodInvocationExprNode,state); }
  public R visit(ArrayCreationExprNode arrayCreationExprNode,P state) { return visitDefault(arrayCreationExprNode,state); }
  public R visit(NewExprNode newExprNode,P state) { return visitDefault(newExprNode,state); }
  public R visit(LiteralExprNode literalExprNode,P state) { return visitDefault(literalExprNode,state); }
  public R visit(MetaClassExprNode metaClassExprNode,P state) { return visitDefault(metaClassExprNode,state); }
  public R visit(ParenthesisExprNode parenthesisExprNode,P state) { return visitDefault(parenthesisExprNode,state); }
  public R visit(KeyOfExprNode keyOfExprNode,P state) { return visitDefault(keyOfExprNode,state); }

  public R visitDefault(SyntaxNode s,P state) {return null; }

  public R reduce(R result1,R result2){
      return result2;
  }

  public static class Nothing{
    private Nothing(){}
  }

  public static final Nothing NOTHING = new Nothing();
}
