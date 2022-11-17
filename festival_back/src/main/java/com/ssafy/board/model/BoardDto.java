package com.ssafy.board.model;

public class BoardDto {
	private String boardId;
	private String title;
	private String content;
	private String userId;
	private String festivalId;
	private String registerTime;
	
	private String commentId;
	private String commentContent;
	private String commentUserId;
	private String commentRegisterTime;
	
	public BoardDto(String boardId, String title, String content, String userId, String festivalId, String registerTime,
			String commentId, String commentContent, String commentUserId, String commentRegisterTime) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.festivalId = festivalId;
		this.registerTime = registerTime;
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.commentUserId = commentUserId;
		this.commentRegisterTime = commentRegisterTime;
	}
	
	public BoardDto() {
		super();
	}
	public BoardDto(String boardId, String title, String content, String userId, String festivalId,
			String registerTime) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.festivalId = festivalId;
		this.registerTime = registerTime;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getCommentRegisterTime() {
		return commentRegisterTime;
	}

	public void setCommentRegisterTime(String commentRegisterTime) {
		this.commentRegisterTime = commentRegisterTime;
	}

	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", festivalId=" + festivalId + ", registerTime=" + registerTime + ", commentId=" + commentId
				+ ", commentContent=" + commentContent + ", commentUserId=" + commentUserId + ", commentRegisterTime="
				+ commentRegisterTime + "]";
	}
}
