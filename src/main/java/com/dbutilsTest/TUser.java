package com.dbutilsTest;

import java.util.Date;

public class TUser implements java.io.Serializable {

	// Fields
	private String userId;
	private String userName;
	private String passWord;
	private String userType;
	private String qxzId;
	private Date lasttime;
	private Date ctime;
	
	// Constructors
	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TUser(String userId, String userName, String passWord,
			String userType, String qxzId, Date lasttime, Date ctime) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.userType = userType;
		this.qxzId = qxzId;
		this.lasttime = lasttime;
		this.ctime = ctime;
	}

	// Property accessors
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getQxzId() {
		return this.qxzId;
	}

	public void setQxzId(String qxzId) {
		this.qxzId = qxzId;
	}

	public Date getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}