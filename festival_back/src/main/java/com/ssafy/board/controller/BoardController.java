package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Value("${file.path}")
    private String fileDir;
	
//  이미지 출력
   @GetMapping("/image/{boardId}")
   @ResponseBody
   public Resource downloadImage(@PathVariable("boardId") String boardId) throws Exception{
	   BoardDto board = service.getArticle(Integer.parseInt(boardId));
       return new UrlResource("file:" + board.getFilePath());
   }
   
   @GetMapping("/defaultimage")
   public Resource defaultImage() throws Exception{
       return new UrlResource("file:"+fileDir+"default.png");
   }
	//게시판 가져오기
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getarticle(@PathVariable("boardId") String boardId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<BoardDto> board = service.getArticleDetail(Integer.parseInt(boardId));
	        map.put("board", board);
	        map.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}
	}
	
	
	@GetMapping()
	public ResponseEntity<?> list(@RequestParam Map<String,String> map) throws Exception{
		String pgno = map.get("pgno");//페이지
		String cate = map.get("cate");//말머리

		int viewPage = 4;//페이지당 보여질 갯수
		int page = ParameterCheck.notNumberToOne(pgno);
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			int cnt = service.totalArticleCount(cate,map.get("festivalId"));
			List<BoardDto> list = service.listArticle(page, viewPage,cate,map.get("festivalId"));
			for (int i = 0; i < list.size(); i++) {				
				list.get(i).setBoardId(ParameterCheck.nullToBlank(list.get(i).getBoardId()));
				list.get(i).setTitle(ParameterCheck.nullToBlank(list.get(i).getTitle()));
				list.get(i).setContent(toRN(list.get(i).getContent()));
				list.get(i).setRegisterTime(ParameterCheck.nullToBlank(list.get(i).getRegisterTime()));
			}
			
			res.put("articleCnt", cnt);
			res.put("boardList", list);
			res.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		}
	}
	

   
   
   //게시글 작성
   @PostMapping()
   public ResponseEntity<?> write(@RequestParam(value="file",required = false) MultipartFile file,@RequestParam Map<String,String> map, HttpServletRequest request) throws Exception {
	   System.out.println("?");
		Map<String, Object> res = new HashMap<String, Object>();

		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				map.put("userId",userId);
				service.writeArticle(file,map);
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
	public ResponseEntity<?> modify(@RequestParam(value="file",required = false) MultipartFile file,@RequestParam Map<String,String> map,HttpServletRequest request) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				String userId = jwtService.getUserId();
				if(!userId.equals(map.get("userId"))) {
					res.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
				}
				
				BoardDto board = new BoardDto();
				board.setBoardId(map.get("boardId"));
				board.setTitle(map.get("title"));
				board.setContent(toBR(map.get("content")));
				board.setUserId(userId);
				board.setCate(map.get("cate"));
				
				service.modifyArticle(file,board);
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
