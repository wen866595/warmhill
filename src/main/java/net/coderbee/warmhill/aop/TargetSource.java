package net.coderbee.warmhill.aop;

/**
 * 代理源。用于生成代理对象的信息。
 *
 * @author coderbee on 2017/12/19.
 */
public class TargetSource {

	private Class<?> targetType;
	private Class<?>[] interfaces;
	private Object target;

	public TargetSource(Object target, Class<?> targetType, Class<?>[] interfaces) {
		this.target = target;
		this.targetType = targetType;
		this.interfaces = interfaces;
	}

	public Class<?> getTargetType() {
		return targetType;
	}

	public void setTargetType(Class<?> targetType) {
		this.targetType = targetType;
	}

	public Class<?>[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Class<?>[] interfaces) {
		this.interfaces = interfaces;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
}
