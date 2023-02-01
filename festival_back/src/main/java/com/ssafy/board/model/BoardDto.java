package com.ssafy.board.model;

public class BoardDto {
	private String boardId;
	private String title;
	private String content;
	private String userId;
	private String festivalId;
	private String registerTime;
	private String cate;//말머리
	
	//파일관련
	private String fileOriName;
	private String fileSaveName;
	private String filePath;
	
	private String commentId;
	private String commentContent;
	private String commentUserId;
	private String commentRegisterTime;
	
	
	public BoardDto(String boardId, String title, String content, String userId, String festivalId, String registerTime,
			String cate, String fileOriName, String fileSaveName, String filePath, String commentId,
			String commentContent, String commentUserId, String commentRegisterTime) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.festivalId = festivalId;
		this.registerTime = registerTime;
		this.cate = cate;
		this.fileOriName = fileOriName;
		this.fileSaveName = fileSaveName;
		this.filePath = filePath;
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.commentUserId = commentUserId;
		this.commentRegisterTime = commentRegisterTime;
	}

	public BoardDto() {
		super();
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

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	
	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", festivalId=" + festivalId + ", registerTime=" + registerTime + ", cate=" + cate + ", fileOriName="
				+ fileOriName + ", fileSaveName=" + fileSaveName + ", filePath=" + filePath + ", commentId=" + commentId
				+ ", commentContent=" + commentContent + ", commentUserId=" + commentUserId + ", commentRegisterTime="
				+ commentRegisterTime + "]";
	}

	
	
	
}
