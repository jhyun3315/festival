package com.ssafy.board.service;

import java.util.List;

import com.ssafy.board.model.BoardDto;

public interface BoardService {
	int writeArticle(BoardDto board) throws Exception;
	List<BoardDto> listArticle(int page, int viewPage) throws Exception;
	int totalArticleCount() throws Exception;
	List<BoardDto> getArticleDetail(int boardId) throws Exception;
	BoardDto getArticle(int boardId) throws Exception;
	void modifyArticle(BoardDto board) throws Exception;
	void deleteArticle(int boardId) throws Exception;
}
