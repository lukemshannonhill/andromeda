package com.sc2mod.andromeda.semAnalysis;

import java.util.ArrayList;
import java.util.List;

import com.sc2mod.andromeda.environment.types.Type;
import com.sc2mod.andromeda.syntaxNodes.InstanceLimitSetterNode;
import com.sc2mod.andromeda.util.Pair;

/**
 * This class is a container for data that is generated in one step
 * of the semantic analysis and used in another step, but not outside the semantics analysis.
 * 
 * @author gex
 *
 */
public class TransientAnalysisData {

	private List<Pair<InstanceLimitSetterNode, Type>> instanceLimits = new ArrayList<Pair<InstanceLimitSetterNode,Type>>();

	public List<Pair<InstanceLimitSetterNode, Type>> getInstanceLimits() {
		return instanceLimits;
	}
}