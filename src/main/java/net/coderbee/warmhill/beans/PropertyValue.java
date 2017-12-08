package net.coderbee.warmhill.beans;

/**
 * 键-值属性对。
 *
 * @author coderbee on 2017/12/8.
 */
public class PropertyValue {
	private String name;
	private Object value;

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
