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
import java.util.List;

import com.sc2mod.andromeda.notifications.Message;
import com.sc2mod.andromeda.notifications.Problem;
import com.sc2mod.andromeda.notifications.SourceLocation;
import com.sc2mod.andromeda.parsing.SourceReader;
import com.sc2mod.andromeda.parsing.CompilationFileManager;
import com.sc2mod.andromeda.syntaxNodes.SyntaxNode;
import com.sc2mod.andromeda.util.Debug;

public abstract class Logger {
	
	private LogLevel logLevel;
	private int level;
	private LogLevel lastLevel;
	
	public void println(LogLevel logLevel, LogFormat logFormat, String message){
		print(logLevel,logFormat,message + "\n");
	}

	public abstract void print(LogLevel logLevel, LogFormat logFormat, String message);
	
	
	public boolean log(LogLevel ll){
		if(ll.getLevel()>=level) return true;
		return false;
	}

	public abstract void printProblem(Problem problem, boolean printStackTraces);
	
	protected String getDefaultProblemString(Problem problem, boolean printStackTraces){
		String prefix = null;
		switch(problem.getSeverity()){
		case ERROR:
		case FATAL_ERROR:
			prefix = "ERROR";
			break;
		case WARNING:
			prefix = "WARNING";
			break;
		default:
			prefix = "INFO";
		}
		SourceLocation[] locations = problem.getLocations();
		String suffix;
		if(locations.length == 1){
			suffix = "at: ";
		} else if(locations.length == 0){
			suffix = "(unlocated)";
		} else {
			suffix = "at (" + locations.length + " locations): ";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append(prefix).append(": ").append(problem.getMessage()).append("\n").append(suffix);
		for(SourceLocation l : locations){
			sb.append(l.getDescriptionString()).append("\n");
		}
		if(printStackTraces){
			sb.append("\nStack Trace:\n");
			sb.append(Debug.formatStackTrace(problem.getStackTrace()));
		}
		return sb.toString();
	}
	
	
}