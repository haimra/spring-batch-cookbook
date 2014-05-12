package org.raman.springframwork.classic.strategy;

import org.springframework.stereotype.Component;

@Component
public class StrategyC implements Strategy {

	@Override
	public int execute(int a, int b) {
		return a-b;
	}

}
