package net.coderbee.warmhill.beans.factory;

import net.coderbee.warmhill.beans.BeanCreationException;
import net.coderbee.warmhill.beans.BeanFactory;
import net.coderbee.warmhill.beans.BeanPostProcessor;
import net.coderbee.warmhill.beans.PropertyValues;
import net.coderbee.warmhill.beans.config.BeanDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 抽象的 bean 工厂
 *
 * @author coderbee on 2017/12/8.
 */
public class AbstractBeanFactory implements BeanFactory {
	// 之所以用 List 维持 beanId 是为了保持 bean 的声明顺序。
	protected List<String> beanIds = new ArrayList<>();

	// Map 是为了提升检索效率
	protected Map<String, BeanDefinition> definitionMap = new HashMap<>();

	protected List<BeanPostProcessor> processors = new ArrayList<>();

	public AbstractBeanFactory() {
	}

	public void registerBeanDefinition(BeanDefinition bdf) {
		definitionMap.put(bdf.getBeanId(), bdf);
		beanIds.add(bdf.getBeanId());
	}

	public void refresh() {
		// 创建所有的 BeanPostProcessor 实例
		initBeanpostProcess();

		// 创建其他的 bean 实例
		initRemain();
	}

	private void initRemain() {
		beanIds.forEach(beanId -> getBean(beanId));
	}

	private void initBeanpostProcess() {
		processors = getBeans(BeanPostProcessor.class);
	}

	@Override
	public <T> T getBean(String beanId) {
		BeanDefinition bdf = definitionMap.get(beanId);
		Object bean = bdf.getBean();
		if (bean == null) {
			// 1. 创建原始类型的实例
			bean = createBean(bdf);

			// 2. 注入依赖的属性
			applyProperties(bean, bdf.getPropertyValues());

			// 3. 加工
			bean = processBean(bean, bdf);

			// 4. 作为最终的结果 bean
			bdf.setBean(bean);
		}
		return (T) bean;
	}

	protected void applyProperties(Object bean, PropertyValues propertyValues) {
	}

	// 对原始的 bean 实例进行加工，返回加工后的对象
	private Object processBean(Object bean, BeanDefinition bdf) {
		for (BeanPostProcessor beanPostProcessor : processors) {
			bean = beanPostProcessor.postProcessBeforInit(bean, bdf.getBeanId());
		}

		// TODO 调用 bean 的初始化方法。

		for (BeanPostProcessor beanPostProcessor : processors) {
			bean = beanPostProcessor.postProcessAfterInit(bean, bdf.getBeanId());
		}

		return bean;

	}

	// 创建原始的 bean 实例
	protected Object createBean(BeanDefinition bdf) {
		try {
			Object o = bdf.getType().newInstance();
			return o;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BeanCreationException("create bean of " + bdf.getType() + " error .", e);
		}
	}

	/**
	 * 获取实现了给定类型的所有 bean 实例。
	 *
	 * @param requiredType 目标 bean 类型
	 * @param <T>
	 * @return 满足条件的实例列表
	 */
	@Override
	public <T> List<T> getBeans(Class<T> requiredType) {
		// 按 bean 的声明顺序获取实例
		List<Object> beans = beanIds.stream()
				.map(beanId -> definitionMap.get(beanId))
				.filter(bdf -> requiredType.isAssignableFrom(bdf.getType()))
				.map(bdf -> getBean(bdf.getBeanId()))
				.collect(Collectors.toList());
		return (List<T>) beans;
	}
}
