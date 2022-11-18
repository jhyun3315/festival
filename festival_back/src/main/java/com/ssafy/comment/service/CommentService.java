package com.ssafy.comment.service;

import java.util.List;

import com.ssafy.comment.model.CommentDto;

public interface CommentService {
	int writeComment(CommentDto comment) throws Exception;
	List<CommentDto> listComment(String boardId, int page, int viewPage) throws Exception;
	void deleteComment(String commentId) throws Exception;
	int totalCommentCount() throws Exception;
	CommentDto getComment(String commentId) throws Exception;
}
