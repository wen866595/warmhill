package net.coderbee.warmhill.aop;

/**
 * @author coderbee on 2017/12/13.
 */
public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();

}
