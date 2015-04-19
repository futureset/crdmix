package com.crdmix.bdd.configuration;

import java.util.Arrays;

import org.jbehave.core.embedder.Embedder;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.crdmix.bdd.Story;

public class JBehaveRule implements MethodRule {

	private final Embedder embedder;
	private String path;
	public JBehaveRule(Embedder embedder,String path) {
		this.embedder = embedder;
		this.path = path;
		
	}
	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		if (target instanceof Story) {
			return new Statement() {
				
				@Override
				public void evaluate() throws Throwable {
					embedder.runStoriesAsPaths(Arrays.asList(path+target.getClass().getSimpleName()+".story"));
				}
			};
		}
		return base;
	}

}
