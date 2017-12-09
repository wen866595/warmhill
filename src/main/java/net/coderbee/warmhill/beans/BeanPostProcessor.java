package net.coderbee.warmhill.beans;

/**
 * bean 处理器。用于在 bean 的初始化前后进行处理，返回处理后的 bean 。
 *
 * @author coderbee on 2017/12/8.
 */
public interface BeanPostProcessor {

	Object postProcessBeforInit(Object bean, String beanId);

	Object postProcessAfterInit(Object bean, String beanId);

}
