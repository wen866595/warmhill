package net.coderbee.warmhill.beans.config;

import net.coderbee.warmhill.beans.PropertyValues;

/**
 * bean 的定义信息。
 *
 * @author coderbee on 2017/12/7.
 */
public class BeanDefinition {
	private String beanId;
	private Class<?> type;
	private PropertyValues propertyValues;
	private Object bean;

	public BeanDefinition(String beanId, Class<?> type, PropertyValues propertyValues) {
		this.beanId = beanId;
		this.type = type;
		this.propertyValues = propertyValues;
	}

	public String getBeanId() {
		return beanId;
	}

	public Class<?> getType() {
		return type;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

}
