package net.coderbee.warmhill.beans;

import java.util.List;

/**
 * bean 工厂
 *
 * @author coderbee on 2017/12/7.
 */
public interface BeanFactory {

	/**
	 * 获取指定 bean 。
	 * @param beanId beanId
	 * @param <T> 目标 bean 类型
	 * @return bean 实例
	 */
	<T> T getBean(String beanId);

	/**
	 * 获取实现了指定类型的所有 Bean 实例。
	 * @param requiredType 目标 bean 类型
	 * @param <T> 目标 bean 类型
	 * @return 实现了指定类型的 bean 实例列表
	 */
	<T> List<T> getBeans(Class<T> requiredType);

}
