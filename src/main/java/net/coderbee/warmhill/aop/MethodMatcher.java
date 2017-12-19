package net.coderbee.warmhill.aop;

import java.lang.reflect.Method;

/**
 * @author coderbee on 2017/12/13.
 */
public interface MethodMatcher {

	boolean matches(Method method, Class<?> targetClass);

}
