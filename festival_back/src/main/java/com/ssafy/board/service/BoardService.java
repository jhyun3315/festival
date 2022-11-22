package com.ssafy.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;

public interface BoardService {
//	int writeArticle(BoardDto board) throws Exception;
	int writeArticle(MultipartFile file, Map<String,String> map) throws Exception;
	List<BoardDto> listArticle(int page, int viewPage,String cate) throws Exception;
	int totalArticleCount(String cate) throws Exception;
	List<BoardDto> getArticleDetail(int boardId) throws Exception;
	BoardDto getArticle(int boardId) throws Exception;
	void modifyArticle(BoardDto board) throws Exception;
	void deleteArticle(int boardId) throws Exception;
}
