package com.ssafy.board.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.mapper.BoardMapper;
import com.ssafy.board.model.BoardDto;

@Service("serviceboard")
public class BoardServiceImpl implements BoardService{

	BoardMapper mapper;
	public BoardServiceImpl(BoardMapper mapper){
		this.mapper = mapper;
	}
	
	@Value("${file.path}")
    private String fileDir;
	
	@Override
	public int writeArticle(MultipartFile file, Map<String,String> map) throws Exception {
		
		BoardDto board = new BoardDto();
		// 원래 파일 이름 추출
        String origName = file.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;


        // 실제로 로컬에 uuid를 파일명으로 저장
        file.transferTo(new File(savedPath));
        
		
        // 게시판 엔티티 생성
        board.setFestivalId(map.get("festivalId"));
        board.setUserId(map.get("userId"));
        board.setCate(map.get("cate"));
        board.setTitle(map.get("title"));
        board.setContent(map.get("content"));
        
        //파일관련
        board.setFileOriName(origName);
        board.setFileSaveName(savedName);
        board.setFilePath(savedPath);
		
        System.out.println(board);
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
