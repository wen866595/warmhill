package net.coderbee.warmhill.beans;

import net.coderbee.warmhill.beans.biz.UserService;
import net.coderbee.warmhill.beans.config.BeanDefinition;
import net.coderbee.warmhill.beans.factory.AutoCapableBeanFactory;
import net.coderbee.warmhill.beans.io.ClasspathResources;
import net.coderbee.warmhill.beans.io.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.List;

/**
 * @author coderbee on 2017/12/9.
 */
public class TestAutoCapableBeanFactory {

	@Test
	public void test() {
		ClasspathResources resources = new ClasspathResources("beans-definition.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resources);
		List<BeanDefinition> definitionList = beanDefinitionReader.load();
		AutoCapableBeanFactory beanFactory = new AutoCapableBeanFactory();

		definitionList.forEach(beanDefinition -> beanFactory.registerBeanDefinition(beanDefinition));
		UserService userService = beanFactory.getBean("userService");
		System.out.println(userService.sayHello("world"));
	}

}
