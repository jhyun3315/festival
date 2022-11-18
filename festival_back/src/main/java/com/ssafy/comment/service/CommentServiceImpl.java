package com.ssafy.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.comment.mapper.CommentMapper;
import com.ssafy.comment.model.CommentDto;

@Service("servicecomment")
public class CommentServiceImpl implements CommentService{

	CommentMapper mapper;
	public CommentServiceImpl(CommentMapper mapper){
		this.mapper = mapper;
	}
	@Override
	public int writeComment(CommentDto comment) throws Exception {
		return mapper.writeComment(comment);
	}

	@Override
	public List<CommentDto> listComment(String boardId, int page, int viewPage) throws Exception {
		int start = (page-1)*10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId",boardId);
		map.put("start", start);
		map.put("viewPage", viewPage);
		System.out.println(map);
		return mapper.listComment(map);
	}

	@Override
	public int totalCommentCount() throws Exception {
		return mapper.totalCommentCount();
	}
	@Override
	public void deleteComment(String commentId) throws Exception {
		mapper.deleteComment(commentId);
	}
	@Override
	public CommentDto getComment(String commentId) throws Exception {
		return mapper.getComment(commentId);
	}
}
