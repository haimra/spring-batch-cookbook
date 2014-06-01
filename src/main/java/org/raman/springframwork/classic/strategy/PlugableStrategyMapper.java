package org.raman.springframwork.classic.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PlugableStrategyMapper implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private StrategyLocator strategyLocator;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
		Map<String, Strategy> beansOfTypeStrategy = applicationContext.getBeansOfType(Strategy.class);
		strategyLocator.setStrategyMap(beansOfTypeStrategy);		
	}

}
