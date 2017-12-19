package net.coderbee.warmhill.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author coderbee on 2017/12/19.
 */
public class CglibAopProxy extends AbstractAopProxy {

	public CglibAopProxy(AdvisorSupport advisorSupport) {
		super(advisorSupport);
	}

	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advisorSupport.getTargetSource().getTargetType());
		enhancer.setInterfaces(advisorSupport.getTargetSource().getInterfaces());
		enhancer.setCallback(new DynamicInvocationHandler(advisorSupport));
		return enhancer.create();
	}

	static class DynamicInvocationHandler implements MethodInterceptor {
		private AdvisorSupport advisorSupport;

		public DynamicInvocationHandler(AdvisorSupport advisorSupport) {
			this.advisorSupport = advisorSupport;
		}

		@Override
		public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

			CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(method, advisorSupport
					.getTargetSource().getTarget(), objects, methodProxy);
			if (advisorSupport.getMethodMatcher() != null && advisorSupport.getMethodMatcher().matches(method,
					advisorSupport.getTargetSource().getTargetType())){
				return advisorSupport.getInterceptor().invoke(cglibMethodInvocation);
			}

			return cglibMethodInvocation.proceed();
		}
	}

	static class CglibMethodInvocation extends ReflectiveMethodInvocation {
		private MethodProxy methodProxy;

		public CglibMethodInvocation(Method method, Object target, Object[] args, MethodProxy methodProxy) {
			super(method, target, args);
			this.methodProxy = methodProxy;
		}

		@Override
		public Object proceed() throws Throwable {
			return methodProxy.invoke(target, args);
		}
	}

}
