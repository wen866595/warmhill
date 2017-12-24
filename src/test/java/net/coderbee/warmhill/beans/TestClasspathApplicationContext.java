package net.coderbee.warmhill.beans;

import net.coderbee.warmhill.beans.biz.UserService;
import net.coderbee.warmhill.beans.context.ClasspathApplicationContext;
import org.junit.Test;

/**
 * @author coderbee on 2017/12/9.
 */
public class TestClasspathApplicationContext {

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
		ClasspathApplicationContext context = new ClasspathApplicationContext(xmlPath);

		// 4. 获取 bean 实例并调用 bean 的方法
		UserService userService = context.getBean("userService");
		System.out.println("\n\n");
		System.out.println(userService.sayHello("world"));
	}

}
