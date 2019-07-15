package com.example.demo.model;

import java.io.Serializable;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	public Users() {
		super();
	}

	/**
	 * 唯一标示
	 */
	private Long id;
	/**
	 * 编码
	 */
	private short userSex;
	/**
	 * 名称
	 */
	private String nickName;

	private String userName;

	private String passWord;

	public Users(short userSex, String nickName, String userName, String passWord) {
		super();
		this.userSex = userSex;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getUserSex() {
		return userSex;
	}

	public void setUserSex(short userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
