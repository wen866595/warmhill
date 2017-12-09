package net.coderbee.warmhill.beans.factory;

import net.coderbee.warmhill.beans.BeanCreationException;
import net.coderbee.warmhill.beans.PropertyValue;
import net.coderbee.warmhill.beans.PropertyValues;
import net.coderbee.warmhill.beans.config.BeanReference;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自动注入属性的 bean 工厂。
 *
 * @author coderbee on 2017/12/8.
 */
public class AutoCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected void applyProperties(Object bean, PropertyValues propertyValues) {
		if (BeanFactoryAware.class.isAssignableFrom(bean.getClass())) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}

		for (PropertyValue propertyValue : propertyValues.getList()) {
			String name = propertyValue.getName();
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				value = getBean(((BeanReference) value).getBeanId());
			}

			String setter = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
			try {
				Method setMethod = bean.getClass().getDeclaredMethod(setter, value.getClass());
				setMethod.setAccessible(true);
				setMethod.invoke(bean, value);

			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				try {
					Field field = bean.getClass().getDeclaredField(name);
					field.setAccessible(true);
					field.set(bean, value);
				} catch (NoSuchFieldException | IllegalAccessException e1) {
					throw new BeanCreationException("set property " + name + " error");
				}
			}
		}
	}

}
