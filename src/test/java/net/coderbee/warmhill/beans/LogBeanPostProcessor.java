package net.coderbee.warmhill.beans;

/**
 * @author coderbee on 2017/12/10.
 */
public class LogBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforInit(Object bean, String beanId) {
		System.out.println("postProcessBeforInit for bean:" + beanId);
		return bean;
	}

	@Override
	public Object postProcessAfterInit(Object bean, String beanId) {
		System.out.println("postProcessAfterInit for bean:" + beanId);
		return bean;
	}
}
