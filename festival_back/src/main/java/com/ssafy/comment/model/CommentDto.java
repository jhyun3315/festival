package com.ssafy.comment.model;

public class CommentDto {
	private String commentId;
	private String content;
	private String userId;
	private String boardId;
	private String registerTime;
	
	public CommentDto() {
		super();
	}

	public CommentDto(String commentId, String content, String userId, String boardId, String registerTime) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.userId = userId;
		this.boardId = boardId;
		this.registerTime = registerTime;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "CommentDto [commentId=" + commentId + ", content=" + content + ", userId=" + userId + ", boardId="
				+ boardId + ", registerTime=" + registerTime + "]";
	}
}
