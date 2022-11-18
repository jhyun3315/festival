package com.ssafy.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.comment.model.CommentDto;


@Mapper
public interface CommentMapper {
	int writeComment(CommentDto comment) throws Exception;
	List<CommentDto> listComment(Map<String,Object> map) throws Exception;
	int totalCommentCount() throws Exception;
	void deleteComment(String commentId) throws Exception;
	CommentDto getComment(String commentId) throws Exception;
}
