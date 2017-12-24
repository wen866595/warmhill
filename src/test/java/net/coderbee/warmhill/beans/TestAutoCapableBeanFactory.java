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

	@Test
	public void testAop() {
		test("ioc-aop.xml");
	}

	private void test(String xmlPath) {
		// 1. 创建一个 类路径下的资源
		ClasspathResources resources = new ClasspathResources(xmlPath);

		// 2. 创建一个 bean 定义加载器并加装 bean 定义
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resources);
		List<BeanDefinition> definitionList = beanDefinitionReader.load();

		// 3. 创建 bean 工厂、注册 bean 定义、初始化所有的 bean
		AutoCapableBeanFactory beanFactory = new AutoCapableBeanFactory();
		definitionList.forEach(beanDefinition -> beanFactory.registerBeanDefinition(beanDefinition));
		beanFactory.refresh();

		// 4. 获取 bean 实例并调用 bean 的方法
		UserService userService = beanFactory.getBean("userService");
		System.out.println("\n\n");
		System.out.println(userService.sayHello("world"));
	}

}
