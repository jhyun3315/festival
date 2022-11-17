package com.ssafy.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;


@Mapper
public interface BoardMapper {
	int writeArticle(BoardDto board) throws Exception;
	List<BoardDto> listArticle(Map<String,Object> map) throws Exception;
	int totalArticleCount() throws Exception;
	List<BoardDto> getArticleDetail(int boardId) throws Exception;
	BoardDto getArticle(int boardId) throws Exception;
	void modifyArticle(BoardDto board) throws Exception;
	void deleteArticle(int boardId) throws Exception;
}
