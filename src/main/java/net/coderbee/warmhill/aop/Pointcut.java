package net.coderbee.warmhill.aop;

/**
 * @author coderbee on 2017/12/13.
 */
public interface Pointcut {

	ClassFilter getClassFilter();

	MethodMatcher getMethodMatcher();

}
