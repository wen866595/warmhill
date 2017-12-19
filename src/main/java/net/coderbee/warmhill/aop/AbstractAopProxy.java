package net.coderbee.warmhill.aop;

/**
 * @author coderbee on 2017/12/19.
 */
public abstract class AbstractAopProxy implements AopProxy {
	protected AdvisorSupport advisorSupport;

	public AbstractAopProxy(AdvisorSupport advisorSupport) {
		this.advisorSupport = advisorSupport;
	}

}
