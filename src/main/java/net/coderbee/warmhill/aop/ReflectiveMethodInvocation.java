package net.coderbee.warmhill.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author coderbee on 2017/12/19.
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
	protected Object target;
	protected Method method;
	protected Object[] args;

	public ReflectiveMethodInvocation(Method method, Object target, Object[] args) {
		this.method = method;
		this.target = target;
		this.args = args;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public Object[] getArguments() {
		return args;
	}

	@Override
	public Object proceed() throws Throwable {
		return method.invoke(target, args);
	}

	@Override
	public Object getThis() {
		return target;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return null;
	}
}
