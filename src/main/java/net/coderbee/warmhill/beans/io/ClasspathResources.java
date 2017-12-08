package net.coderbee.warmhill.beans.io;

import java.io.InputStream;

/**
 * 表示类路径下的资源。
 *
 * @author coderbee on 2017/12/8.
 */
public class ClasspathResources implements Resources {
	private InputStream stream;

	public ClasspathResources(String path) {
		stream = this.getClass().getClassLoader().getResourceAsStream(path);
	}

	@Override
	public InputStream getStream() {
		return stream;
	}

	@Override
	public void close() throws Exception {
		stream.close();
	}
}
