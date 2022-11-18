package com.ssafy.favor.model;


public class FavorDto {
	private String userId;
	private String festivalId;
	
	public FavorDto() {
		super();
	}

	public FavorDto(String userId, String festivalId) {
		super();
		this.userId = userId;
		this.festivalId = festivalId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(String festivalId) {
		this.festivalId = festivalId;
	}

	@Override
	public String toString() {
		return "FavorDto [userId=" + userId + ", festivalId=" + festivalId + "]";
	}	
	
	
	
}
