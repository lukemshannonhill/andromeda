/**
 * Andromeda, a galaxy extension language.
 * Copyright (C) J. 'gex' Finis @2010 (gekko_tgh@gmx.de, sc2mod.com)
 * See doc/license.txt for license information.
 */
package com.sc2mod.andromeda.parser;

import com.sc2mod.andromeda.parsing.SourceReader;
import com.sc2mod.andromeda.parsing.SourceFileInfo;
import com.sc2mod.andromeda.parsing.InclusionType;
import com.sc2mod.andromeda.notifications.InternalProgramError;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.ProblemId;
import com.sc2mod.andromeda.parsing.Symbol;
%%

%public
%class AndromedaScanner
%implements sym, Scanner
%unicode
%function nextToken

%char


%type Symbol
//%cup
//%cupdebug

%init{
curFile = ((SourceReader)in).getFileId();
curInclusionType = ((SourceReader)in).getInclusionType();
%init}

%{
  StringBuffer string = new StringBuffer();
  
  protected int curFile;
  protected InclusionType curInclusionType;
  
  private Symbol processInclude(InclusionType inclusionType, boolean isImport){
  	if(curInclusionType == InclusionType.NATIVE){
  		inclusionType = InclusionType.NATIVE;
  	}
  	int includeToken;
  	String s;
  	if(isImport){
		includeToken = IMPORT_START; 	
		s = yytext();
  	} else {
  		s = null;
  		includeToken = INCLUDE_START;
  	}
  	SourceReader ar = this.zzReader.getSourceEnvironment().getReaderFromInclude(yytext(), yychar|curFile, (yylength()+yychar)|curFile,inclusionType,isImport);
  	if(ar == null) return null;
  	yypushStream(ar);
  	return symbol(includeToken,new SourceFileInfo(curFile,inclusionType,s));
  }

  
  private Symbol symbol(int type) {
    return new Symbol(type, yychar|curFile, (yylength()+yychar)|curFile);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yychar|curFile, (yylength()+yychar)|curFile, value);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

IncludeFile = [ \t\f]*\"[a-zA-Z0-9_\.\/-]+\"
LibIncludeFile = [ \t\f]*\<[a-zA-Z0-9_\.\/-]+\>
LibImportFile = [ \t\f]+[a-zA-Z0-9_\.]+[ \t\f]*\;

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "abstract"                     { return symbol(ABSTRACT); }
  "bool"               	         { return symbol(BOOLEAN); }
  "break"                        { return symbol(BREAK); }
  "byte"                         { return symbol(BYTE); }
  "case"                         { return symbol(CASE); }
  "catch"                        { return symbol(CATCH); }
  "char"                         { return symbol(CHAR); }
  "class"                        { return symbol(CLASS); }
  "struct"                       { return symbol(STRUCT); }
  "const"                        { return symbol(CONST); }
  "package"						 { return symbol(PACKAGE); }
  "continue"                     { return symbol(CONTINUE); }
  "uses"						 { return symbol(USES); }
  "is"							 { return symbol(IS); }
  "iskey"						 { return symbol(ISKEY); }
  "keyof"						 { return symbol(KEYOF); }
  "setinstancelimit"			 { return symbol(SETINSTANCELIMIT); }
  "do"                           { return symbol(DO); }
//  "double"                       { return symbol(DOUBLE); }
  "delete"						 { return symbol(DELETE); }
  "else"                         { return symbol(ELSE); }
  "enrich"						 { return symbol(ENRICH); }
  "extends"                      { return symbol(EXTENDS); }
  "extension"					 { return symbol(EXTENSION); }
  "final"                        { return symbol(FINAL); }
  "finally"                      { return symbol(FINALLY); }
//  "float"                        { return symbol(FLOAT); }
  "for"                          { return symbol(FOR); }
  "default"                      { return symbol(DEFAULT); }
  "implements"                   { return symbol(IMPLEMENTS); }
  "import"                       { return symbol(IMPORT); }
  "instanceof"                   { return symbol(INSTANCEOF); }
  "int"                          { return symbol(INT); }
  "fixed"                        { return symbol(FIXED); }
  "interface"                    { return symbol(INTERFACE); }
  "long"                         { return symbol(LONG); }
  "native"                       { return symbol(NATIVE); }
  "new"                          { return symbol(NEW); }
//  "goto"                         { return symbol(GOTO); }
  "if"                           { return symbol(IF); }
  "inline"						 { return symbol(INLINE); }
  ".inline"						 { return symbol(DOTINLINE); }
  "internal"					 { return symbol(INTERNAL); }
  "public"                       { return symbol(PUBLIC); }
  "short"                        { return symbol(SHORT); }
  "super"                        { return symbol(SUPER); }
  "switch"                       { return symbol(SWITCH); }
//  "synchronized"                 { return symbol(SYNCHRONIZED); }
  "private"                      { return symbol(PRIVATE); }
  "protected"                    { return symbol(PROTECTED); }
  "transient"                    { return symbol(TRANSIENT); }
  "return"                       { return symbol(RETURN); }
  "void"                         { return symbol(VOID); }
  "static"                       { return symbol(STATIC); }
  "while"                        { return symbol(WHILE); }
  "this"                         { return symbol(THIS); }
  "throw"                        { return symbol(THROW); }
  "throws"                       { return symbol(THROWS); }
  "try"                          { return symbol(TRY); }
//  "volatile"                     { return symbol(VOLATILE); }
//  "strictfp"                     { return symbol(STRICTFP); }
  "override"					 { return symbol(OVERRIDE); }
  "typedef"						 { return symbol(TYPEDEF); }
  "function"					 { return symbol(FUNCTION); }
  
  /* boolean literals */
  "true"                         { return symbol(BOOLEAN_LITERAL, new Boolean(true)); }
  "false"                        { return symbol(BOOLEAN_LITERAL, new Boolean(false)); }
  
  /* null literal */
  "null"                         { return symbol(NULL_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  "->"							 { return symbol(ARROW); }
  
  /* operators */
  "@"							 { return symbol(AT); }
  "="                            { return symbol(EQ); }
  ">"                            { return symbol(GT); }
  "<"                            { return symbol(LT); }
  "!"                            { return symbol(NOT); }
  "!!"                            { return symbol(NOTNOT); }  
  "~"                            { return symbol(COMP); }
  "?"                            { return symbol(QUESTION); }
  ":"                            { return symbol(COLON); }
  ".."							 { return symbol(DOTDOT); }
  "=>"							 { return symbol(ARROW); }
  "=="                           { return symbol(EQEQ); }
  "<="                           { return symbol(LTEQ); }
  ">="                           { return symbol(GTEQ); }
  "!="                           { return symbol(NOTEQ); }
  "&&"                           { return symbol(ANDAND); }
  "||"                           { return symbol(OROR); }
  "++"                           { return symbol(PLUSPLUS); }
  "--"                           { return symbol(MINUSMINUS); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(MULT); }
  "/"                            { return symbol(DIV); }
  "&"                            { return symbol(AND); }
  "|"                            { return symbol(OR); }
  "^"                            { return symbol(XOR); }
  "%"                            { return symbol(MOD); }
  "<<"                           { return symbol(LSHIFT); }
  ">>"                           { return symbol(RSHIFT); }
  ">>>"                          { return symbol(URSHIFT); }
  "+="                           { return symbol(PLUSEQ); }
  "-="                           { return symbol(MINUSEQ); }
  "*="                           { return symbol(MULTEQ); }
  "/="                           { return symbol(DIVEQ); }
  "&="                           { return symbol(ANDEQ); }
  "|="                           { return symbol(OREQ); }
  "^="                           { return symbol(XOREQ); }
  "%="                           { return symbol(MODEQ); }
  "<<="                          { return symbol(LSHIFTEQ); }
  ">>="                          { return symbol(RSHIFTEQ); }
  ">>>="                         { return symbol(URSHIFTEQ); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer((int) parseLong(0, yylength(), 8))); }  
  {OctLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }  
  
  /* include */
  "include"{IncludeFile}   { Symbol s = processInclude(InclusionType.INCLUDE,false);
  							 if(s != null) return s; }
  "include"{LibIncludeFile} { Symbol s = processInclude(InclusionType.LIBRARY,false);
  							 if(s != null) return s; }
  "import"{LibImportFile} { Symbol s = processInclude(InclusionType.LIBRARY,true);
  							 if(s != null) return s; }

	<<EOF>>        { if (yymoreStreams()){ yypopStream(); return symbol(INCLUDE_END);}else return symbol(EOF); }
  
 
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            {   	  throw Problem.ofType(ProblemId.SYNTAX_ILLEGAL_ESCAPE_SEQUENCE).at(curFile+yychar,curFile+yychar+yylength())
				        	  				.details(yytext())
				        	  				.raiseUnrecoverable(); }
  {LineTerminator}               { throw Problem.ofType(ProblemId.SYNTAX_UNTERMINATED_STRING).at(curFile+yychar-string.length()-1,curFile+yychar)
								  	  			.raiseUnrecoverable();
								 }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character(yytext().charAt(0))); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\b'));}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\t'));}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\n'));}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\f'));}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\r'));}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\"'));}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\''));}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, new Character('\\')); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(CHARACTER_LITERAL, new Character((char)val)); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
.|\n                             { throw Problem.ofType(ProblemId.SYNTAX_ILLEGAL_CHARACTER).at(curFile+yychar,curFile+yychar+yylength())
				        	  			.details(yytext())
				        	  			.raiseUnrecoverable(); }
<<EOF>>                          { return symbol(EOF); }
