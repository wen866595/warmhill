package net.coderbee.warmhill.beans.biz;

/**
 * @author coderbee on 2017/12/8.
 */
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String msg) {
		return "[ " + msg + "]";
	}
	
}
