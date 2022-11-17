package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.service.BoardService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.util.ParameterCheck;

@RestController
@RequestMapping("/board")
public class BoardController {

	private JwtService jwtService;
	private BoardService service;
	
	BoardController(@Qualifier("serviceboard")  BoardService service,JwtService jwtService){
		this.service = service;
		this.jwtService= jwtService;
	}
	
	//게시판 가져오기
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getarticle(@PathVariable("boardId") String boardId) throws Exception{
	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<BoardDto> board = service.getArticleDetail(Integer.parseInt(boardId));
			System.out.println(board);
	        map.put("board", board);
	        map.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/list/{pgno}")
	public ResponseEntity<?> list(@PathVariable("pgno") String pgno) throws Exception{
		int page = ParameterCheck.notNumberToOne(pgno);
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<BoardDto> list = service.listArticle(page, 10);
			for (int i = 0; i < list.size(); i++) {				
				list.get(i).setBoardId(ParameterCheck.nullToBlank(list.get(i).getBoardId()));
				list.get(i).setTitle(ParameterCheck.nullToBlank(list.get(i).getTitle()));
				list.get(i).setContent(toRN(list.get(i).getContent()));
				list.get(i).setRegisterTime(ParameterCheck.nullToBlank(list.get(i).getRegisterTime()));
			}
	        map.put("boardList", list);
	        map.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}
	}
	
	
	@PostMapping()
	public ResponseEntity<?> write(@RequestBody Map<String,String> map, HttpServletRequest request) throws Exception {
		
		
		Map<String, Object> res = new HashMap<String, Object>();

		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				BoardDto board = new BoardDto();
				board.setTitle(map.get("title"));
				board.setContent(toBR(map.get("content")));
				board.setFestivalId(map.get("festivalId"));
				board.setUserId(userId);
				
				service.writeArticle(board);
				res.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				res.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}
		}else {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.UNAUTHORIZED);	
		}
	}
	
	
	@PutMapping()
	public ResponseEntity<?> modify(@RequestBody Map<String,String> map,HttpServletRequest request) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				String userId = jwtService.getUserId();
				System.out.println(userId);
				if(!userId.equals(map.get("userId"))) {
					res.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
				}
				String boardId =map.get("boardId");
				String title = map.get("title");
				String content = map.get("content");
				
				BoardDto board = new BoardDto();
				board.setBoardId(boardId);
				board.setContent(toBR(content));
				board.setTitle(title);
				board.setUserId(userId);
				
				System.out.println(board);
				service.modifyArticle(board);
				res.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				res.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}
		}else {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.UNAUTHORIZED);	
		}
	}	
	
	@DeleteMapping("/{no}")
	public ResponseEntity<?> delete(@PathVariable("no") String no,HttpServletRequest request) throws NumberFormatException, Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				BoardDto board =  service.getArticle(Integer.parseInt(no));
				if(!userId.equals(board.getUserId())) {
					res.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
				}
				service.deleteArticle(Integer.parseInt(no));
				res.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				res.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}
		}else {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.UNAUTHORIZED);	
		}
	}
	
	private String toBR(String content) {
		String contents = "";
		contents = content.replace("\r\n", "<br>");
		return contents;
	}
	private String toRN(String content) {
		String contents = "";
		contents = content.replace("<br>","\r\n");
		return contents;
	}
}
