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
		
		if(file!=null) {			
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
			 //파일관련
	        board.setFileOriName(origName);
	        board.setFileSaveName(savedName);
	        board.setFilePath(savedPath);
		}else {
			//파일 입력을 안하면 기본이미지
			board.setFileOriName("default.png");
	        board.setFileSaveName("default.png");
	        board.setFilePath(fileDir+"default.png");
		}
        
		
        // 게시판 엔티티 생성
        board.setFestivalId(map.get("festivalId"));
        board.setUserId(map.get("userId"));
        board.setCate(map.get("cate"));
        board.setTitle(map.get("title"));
        board.setContent(map.get("content"));
        
       
		
        System.out.println(board);
		return mapper.writeArticle(board);

	}


	@Override
	public List<BoardDto> listArticle(int page, int viewPage,String cate,String festivalId) throws Exception {
		int start = (page-1)*viewPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("viewPage", viewPage);
		map.put("cate",cate);
		map.put("festivalId", festivalId);
		return mapper.listArticle(map);
	}

	@Override
	public int totalArticleCount(String cate,String festivalId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate", cate);
		map.put("festivalId", festivalId);
		return mapper.totalArticleCount(map);
	}

	@Override
	public BoardDto getArticle(int boardId) throws Exception {
		return mapper.getArticle(boardId);
	}

	@Override
	public void modifyArticle(MultipartFile file,BoardDto board) throws Exception {
		
		if(file!=null) {//새로운 파일로 교체	
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
			 //파일관련
	        board.setFileOriName(origName);
	        board.setFileSaveName(savedName);
	        board.setFilePath(savedPath);
		}
		
		
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
