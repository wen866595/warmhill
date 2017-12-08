package net.coderbee.warmhill.beans.io;

import java.io.InputStream;

/**
 * @author coderbee on 2017/12/8.
 */
public interface Resources extends AutoCloseable {

	InputStream getStream();

}
