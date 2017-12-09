package net.coderbee.warmhill.beans.context;

import net.coderbee.warmhill.beans.BeanFactory;
import net.coderbee.warmhill.beans.io.Resources;

import java.util.List;

/**
 * @author coderbee on 2017/12/8.
 */
public class AbstractApplicationContext implements ApplicationContext {
	protected Resources resources;
	protected BeanFactory beanFactory;

	@Override
	public <T> T getBean(String beanId) {
		return beanFactory.getBean(beanId);
	}

	@Override
	public <T> List<T> getBeans(Class<T> requiredType) {
		return beanFactory.getBeans(requiredType);
	}
}
