package net.coderbee.warmhill.aop;

/**
 * @author coderbee on 2017/12/13.
 */
public interface ClassFilter {

	boolean isMatch(Class<?> targetClass);

}
