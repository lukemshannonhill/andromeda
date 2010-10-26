/**
 *  Andromeda, a galaxy extension language.
 *  Copyright (C) 2010 J. 'gex' Finis  (gekko_tgh@gmx.de, sc2mod.com)
 * 
 *  Because of possible Plagiarism, Andromeda is not yet
 *	Open Source. You are not allowed to redistribute the sources
 *	in any form without my permission.
 *  
 */
package com.sc2mod.andromeda.util.logging;

import java.io.PrintStream;

import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.SourceLocation;
import com.sc2mod.andromeda.parsing.CompilationFileManager;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;
import com.sc2mod.andromeda.util.Debug;

public class ConsoleLog extends Logger{
	
	@Override
	public void print(LogLevel logLevel, LogFormat logFormat, String message) {
		System.out.print(message);
	}

	@Override
	public void printProblem(Problem problem, boolean printStackTrace) {
		PrintStream out;
		switch(problem.getSeverity()){
		case ERROR:
		case FATAL_ERROR:
			out = System.err;
			break;
		default:
			out = System.out;
		}
		out.print(getDefaultProblemString(problem, printStackTrace));
	}
}