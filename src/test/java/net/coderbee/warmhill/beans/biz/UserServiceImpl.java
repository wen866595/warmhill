package net.coderbee.warmhill.beans.biz;

/**
 * @author coderbee on 2017/12/8.
 */
public class UserServiceImpl implements UserService {
	private String who;
	private EchoService echoService;

	@Override
	public String sayHello(String msg) {
		return who + " say hello " + msg;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public void setEchoService(EchoService echoService) {
		this.echoService = echoService;
	}
}
