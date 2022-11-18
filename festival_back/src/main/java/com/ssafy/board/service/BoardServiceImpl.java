package com.ssafy.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.mapper.BoardMapper;
import com.ssafy.board.model.BoardDto;

@Service("serviceboard")
public class BoardServiceImpl implements BoardService{

	BoardMapper mapper;
	public BoardServiceImpl(BoardMapper mapper){
		this.mapper = mapper;
	}
	@Override
	public int writeArticle(BoardDto board) throws Exception {
		return mapper.writeArticle(board);
	}

	@Override
	public List<BoardDto> listArticle(int page, int viewPage) throws Exception {
		System.out.println("리스트 서비스임");
		int start = (page-1)*10;
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(start);
		System.out.println(viewPage);
		map.put("start", start);
		map.put("viewPage", viewPage);
		System.out.println(map);
		return mapper.listArticle(map);
	}

	@Override
	public int totalArticleCount() throws Exception {
		return mapper.totalArticleCount();
	}

	@Override
	public BoardDto getArticle(int boardId) throws Exception {
		return mapper.getArticle(boardId);
	}

	@Override
	public void modifyArticle(BoardDto board) throws Exception {
		mapper.modifyArticle(board);
	}

	@Override
	public void deleteArticle(int boardId) throws Exception {
		mapper.deleteArticle(boardId);
	}
	@Override
	public List<BoardDto> getArticleDetail(int boardId) throws Exception {
		return mapper.getArticleDetail(boardId);
	}

	
}
