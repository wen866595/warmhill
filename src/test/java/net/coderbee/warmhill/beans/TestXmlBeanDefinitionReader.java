package net.coderbee.warmhill.beans;

import net.coderbee.warmhill.beans.config.BeanDefinition;
import net.coderbee.warmhill.beans.io.ClasspathResources;
import net.coderbee.warmhill.beans.io.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.List;

/**
 * @author coderbee on 2017/12/8.
 */
public class TestXmlBeanDefinitionReader {

	@Test
	public void testXmlBeanDefinitionReader() {
		ClasspathResources resources = new ClasspathResources("beans-definition.xml");
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(resources);
		List<BeanDefinition> definitionList = beanDefinitionReader.load();
		System.out.println("" + definitionList.size());
	}

}
