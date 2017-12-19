package net.coderbee.warmhill.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author coderbee on 2017/12/19.
 */
public class AnotherInterceptor implements MethodInterceptor {

	@Override public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("another interceptor start, call " + invocation.getMethod().getName());
		Object result = invocation.proceed();
		System.out.println("another interceptor end, call " + invocation.getMethod().getName());
		return result;
	}

}
