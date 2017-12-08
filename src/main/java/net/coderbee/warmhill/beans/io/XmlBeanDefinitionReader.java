package net.coderbee.warmhill.beans.io;

import net.coderbee.util.StringUtil;
import net.coderbee.warmhill.beans.*;
import net.coderbee.warmhill.beans.config.BeanDefinition;
import net.coderbee.warmhill.beans.config.BeanDefinitionReader;
import net.coderbee.warmhill.beans.config.BeanReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 从 XML 配置文件读取 bean 定义的加载器。
 *
 * @author coderbee on 2017/12/8.
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
	private Resources resources;

	public XmlBeanDefinitionReader(Resources resources) {
		this.resources = resources;
	}

	@Override
	public List<BeanDefinition> load() {
		try {
			try(InputStream inputStream = resources.getStream()) {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(inputStream);

				Element root = document.getDocumentElement();
				NodeList beans = root.getElementsByTagName("bean");

				List<BeanDefinition> definitions = new ArrayList<>(beans.getLength());
				for (int i = 0; i < beans.getLength(); i++) {
					Element bean = (Element) beans.item(i);
					BeanDefinition definition = parseBeanDefinition(bean);
					definitions.add(definition);
				}

				return definitions;
			}
		} catch (Exception e) {
			throw new BeanCreationException("", e);
		}
	}

	private BeanDefinition parseBeanDefinition(Element bean) throws ClassNotFoundException {
		String id = bean.getAttribute("id");
		String classString = bean.getAttribute("class");
		if (StringUtil.isBlank(id) || StringUtil.isBlank(classString)) {
			throw new BeanCreationException("bean attribute 'id' and 'class' cann't be empty .");
		}

		PropertyValues propertyValues = parseProperties(bean);

		return new BeanDefinition(id, Class.forName(classString), propertyValues);
	}

	private PropertyValues parseProperties(Element bean) {
		PropertyValues propertyValues = new PropertyValues();
		NodeList properites = bean.getElementsByTagName("property");
		for (int j = 0; j < properites.getLength(); j++) {
			Element prop =  (Element) properites.item(j);
			String name = prop.getAttribute("name");
			String value = prop.getAttribute("value");
			Object val = value;
			if (StringUtil.isBlank(value)) {
				String ref = prop.getAttribute("ref");
				if (StringUtil.isBlank(ref)) {
					throw new BeanCreationException("property[" + name+"] must have not empty value or " +
							"ref ." );
				}
				val = new BeanReference(ref);
			}
			propertyValues.add(name, val);
		}
		return propertyValues;
	}
}
