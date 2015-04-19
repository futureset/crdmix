package com.crdmix.bdd.configuration;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.AbstractStepsFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringStepsFactory extends AbstractStepsFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	private final List<Class<?>> stepTypes;

	public SpringStepsFactory(Configuration configuration,List<Class<?>> stepTypes) {
		super(configuration);
		this.stepTypes = stepTypes;
	}

	@Override
	public Object createInstanceOfType(Class<?> type) {
		return applicationContext.getBean(type);
	}

	@Override
	protected List<Class<?>> stepsTypes() {
		return this.stepTypes;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
				this.applicationContext = applicationContext;
		
	}

}
