package net.coderbee.util;

/**
 * @author coderbee on 2017/12/8.
 */
public class StringUtil {

	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}

	public static boolean isBlank(String string) {
		return isEmpty(string) || string.trim().equals("");
	}

}
