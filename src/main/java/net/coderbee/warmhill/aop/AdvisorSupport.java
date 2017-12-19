package net.coderbee.warmhill.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author coderbee on 2017/12/19.
 */
public class AdvisorSupport {
	private TargetSource targetSource;
	private MethodInterceptor interceptor;
	private MethodMatcher methodMatcher;

	public TargetSource getTargetSource() {
		return targetSource;
	}

	public void setTargetSource(TargetSource targetSource) {
		this.targetSource = targetSource;
	}

	public MethodInterceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(MethodInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}
}
