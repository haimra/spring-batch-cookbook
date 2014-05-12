package org.raman.springframwork.classic.strategy;

import java.util.Map;

public class StrategyLocator {
	private Map<StrategyEnum, ? extends Strategy> strategyMap;

	public Strategy lookup(String strategy) {
		StrategyEnum strategyEnum = StrategyEnum.valueOf(strategy);
		return strategyMap.get(strategyEnum);
	}

	public void setStrategyMap(Map<StrategyEnum, ? extends Strategy> strategyMap) {
		this.strategyMap = strategyMap;
	}

}
