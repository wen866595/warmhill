package net.coderbee.warmhill.beans.config;

import java.util.List;

/**
 * bean 定义加载器。
 *
 * @author coderbee on 2017/12/8.
 */
public interface BeanDefinitionReader {

	List<BeanDefinition> load();

}
