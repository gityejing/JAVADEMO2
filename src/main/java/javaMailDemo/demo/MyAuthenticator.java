package javaMailDemo.demo;

import javax.mail.Authenticator;

public class MyAuthenticator extends Authenticator {
	private String userName;
	private String password;
	
	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
