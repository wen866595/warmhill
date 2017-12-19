package net.coderbee.warmhill.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author coderbee on 2017/12/19.
 */
public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("start invoke " + invocation.getMethod().getName());

		Object result = invocation.proceed();

		System.out.println("end invoke " + invocation.getMethod().getName() + " used time millils :" + (System.currentTimeMillis() - start));

		return result;
	}

}
