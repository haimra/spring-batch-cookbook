package org.raman.springframwork.classic.strategy;

import java.util.Map;

public class StrategyLocator {
	private Map<String, ? extends Strategy> strategyMap;

	public Strategy lookup(String strategy) {
		return strategyMap.get(strategy);
	}

	public void setStrategyMap(Map<String, ? extends Strategy> strategyMap) {
		this.strategyMap = strategyMap;
	}

}
