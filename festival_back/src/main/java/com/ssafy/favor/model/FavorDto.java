package com.ssafy.favor.model;


public class FavorDto {
	private String userId;
	private String festivalId;
	
	private String festivalName;
	private String festivalContent;
	private String startDate;
	private String endDate;
	private String phoneNumber;
	private String homepage;
	private String lat;
	private String lng;
	private String address;
	private String originImage;
	private String thumbnailImage;
	private String orgName;
	
	public FavorDto() {
		super();
	}

	public FavorDto(String userId, String festivalId, String festivalName, String festivalContent, String startDate,
			String endDate, String phoneNumber, String homepage, String lat, String lng, String address,
			String originImage, String thumbnailImage, String orgName) {
		super();
		this.userId = userId;
		this.festivalId = festivalId;
		this.festivalName = festivalName;
		this.festivalContent = festivalContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.phoneNumber = phoneNumber;
		this.homepage = homepage;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
		this.originImage = originImage;
		this.thumbnailImage = thumbnailImage;
		this.orgName = orgName;
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

	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}

	public String getFestivalContent() {
		return festivalContent;
	}

	public void setFestivalContent(String festivalContent) {
		this.festivalContent = festivalContent;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOriginImage() {
		return originImage;
	}

	public void setOriginImage(String originImage) {
		this.originImage = originImage;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "FavorDto [userId=" + userId + ", festivalId=" + festivalId + ", festivalName=" + festivalName
				+ ", festivalContent=" + festivalContent + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", phoneNumber=" + phoneNumber + ", homepage=" + homepage + ", lat=" + lat + ", lng=" + lng
				+ ", address=" + address + ", originImage=" + originImage + ", thumbnailImage=" + thumbnailImage
				+ ", orgName=" + orgName + "]";
	}

	
	
	
}
