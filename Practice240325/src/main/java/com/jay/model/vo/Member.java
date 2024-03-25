package com.jay.model.vo;

public class Member {
	private String userId;
	private String userPassword;
	private String userName;
	private String gender;
	private String[] genre;
	
	public Member() {
		super();
	}

	public Member(String userId, String userPassword, String userName, String gender, String[] genre) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.gender = gender;
		this.genre = genre;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", gender="
				+ gender + ", genre=" + genre + "]";
	}
	
	

}
