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
	public void testAutoCapable() {
		test("beans-definition.xml");
	}

	@Test
	public void testBeanPostProcerror() {
		test("ioc.xml");
	}

	private void test(String xmlPath) {
		ClasspathResources resources = new ClasspathResources(xmlPath);
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resources);
		List<BeanDefinition> definitionList = beanDefinitionReader.load();
		AutoCapableBeanFactory beanFactory = new AutoCapableBeanFactory();

		definitionList.forEach(beanDefinition -> beanFactory.registerBeanDefinition(beanDefinition));
		beanFactory.refresh();

		UserService userService = beanFactory.getBean("userService");
		System.out.println(userService.sayHello("world"));
	}

}
