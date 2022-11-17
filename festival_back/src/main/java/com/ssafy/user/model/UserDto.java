package com.ssafy.user.model;

//import io.swagger.annotations.ApiModel; 
//@ApiModel(value = "UserDto (회원정보)", description = "아이디, 비번, 이름.을 가진   Domain Class")

public class UserDto {
	
	private String userId;
	private String userPw;
	private String userName;
	private String email;
	private String phoneNumber; 
 
	public UserDto() {
	}
	

	public UserDto(String userId, String userPw, String userName, String email, String phoneNumber) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserPw() {
		return userPw;
	}



	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	

}
