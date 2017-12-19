package net.coderbee.warmhill.aop;

/**
 * @author coderbee on 2017/12/19.
 */
public class ProxyFactory extends AdvisorSupport implements AopProxy {
	
	@Override
	public Object getProxy() {
		return new CglibAopProxy(this).getProxy();
	}
}
