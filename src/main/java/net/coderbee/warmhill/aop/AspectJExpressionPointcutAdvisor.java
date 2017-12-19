package net.coderbee.warmhill.aop;

import org.aopalliance.aop.Advice;

/**
 * @author coderbee on 2017/12/19.
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
	private Pointcut pointcut;
	private Advice advice;

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

	public void setPointcut(Pointcut pointcut) {
		this.pointcut = pointcut;
	}
}
