package net.coderbee.warmhill.beans;

/**
 * @author coderbee on 2017/12/8.
 */
public class BeanCreationException extends RuntimeException {

	public BeanCreationException(String msg) {
		super(msg);
	}

	public BeanCreationException(Throwable cause) {
		super(cause);
	}

	public BeanCreationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
