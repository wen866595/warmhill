package net.coderbee.warmhill.aop;

import net.coderbee.warmhill.beans.BeanFactory;
import net.coderbee.warmhill.beans.BeanPostProcessor;
import net.coderbee.warmhill.beans.factory.BeanFactoryAware;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collections;
import java.util.List;

/**
 * @author coderbee on 2017/12/19.
 */
public class AspectJAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
	private BeanFactory beanFactory;

	@Override
	public Object postProcessBeforInit(Object bean, String beanId) {
		return bean;
	}

	@Override
	public Object postProcessAfterInit(Object bean, String beanId) {
		if (Advice.class.isAssignableFrom(bean.getClass())
				|| Advisor.class.isAssignableFrom(bean.getClass())
				|| MethodInterceptor.class.isAssignableFrom(bean.getClass())
				|| Pointcut.class.isAssignableFrom(bean.getClass())) {
			return bean;
		}

		Class<?> beanClass = bean.getClass();
		List<AspectJExpressionPointcutAdvisor> pointcutAdvisors = beanFactory.getBeans(AspectJExpressionPointcutAdvisor.class);
		Collections.reverse(pointcutAdvisors);

		for (AspectJExpressionPointcutAdvisor pointcutAdvisor : pointcutAdvisors) {
			if (pointcutAdvisor.getPointcut().getClassFilter().isMatch(beanClass)) {
				ProxyFactory proxyFactory = new ProxyFactory();
				proxyFactory.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());
				proxyFactory.setInterceptor((MethodInterceptor) pointcutAdvisor.getAdvice());

				TargetSource targetSource = new TargetSource(bean, beanClass, beanClass.getInterfaces());
				proxyFactory.setTargetSource(targetSource);

				bean = proxyFactory.getProxy();
			}
		}

		return bean;
	}


	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}
