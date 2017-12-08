package net.coderbee.warmhill.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * bean 的属性列表
 *
 * @author coderbee on 2017/12/8.
 */
public class PropertyValues {
	private List<PropertyValue> list = new LinkedList<>();

	public void add(String name, Object value) {
		list.add(new PropertyValue(name, value));
	}

	public List<PropertyValue> getList() {
		return list;
	}
}
