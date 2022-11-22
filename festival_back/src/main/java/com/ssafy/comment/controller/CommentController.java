package com.ssafy.comment.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.service.CommentService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.util.ParameterCheck;

@RestController
@RequestMapping("/comment")
public class CommentController {

	private JwtService jwtService;
	private CommentService service;
	
	CommentController(@Qualifier("servicecomment")  CommentService service,JwtService jwtService){
		this.service = service;
		this.jwtService= jwtService;
	}
		
	
	@GetMapping("/{boardId}")
	public ResponseEntity<?> list(@PathVariable("boardId") String boardId) throws Exception{
		int page=1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<CommentDto> list = service.listComment(boardId,page, 10);
			for (int i = 0; i < list.size(); i++) {				
				list.get(i).setBoardId(list.get(i).getBoardId());
				list.get(i).setUserId(list.get(i).getUserId());
				list.get(i).setContent(list.get(i).getContent());
				list.get(i).setRegisterTime(list.get(i).getRegisterTime());
			}
	        map.put("commentlist", list);
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
				CommentDto comment = new CommentDto();
				comment.setBoardId(map.get("boardId"));
				comment.setUserId(userId);		
				comment.setContent(map.get("content"));
				System.out.println(comment);
				service.writeComment(comment);
				res.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				res.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}
		}else {
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.UNAUTHORIZED);	
		}
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> delete(@PathVariable("commentId") String commentId, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				CommentDto comment = service.getComment(commentId);
				if(!comment.getUserId().equals(jwtService.getUserId())){
					map.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
				}
				service.deleteComment(commentId);
		        map.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			}
		}else {
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.UNAUTHORIZED);	
		}
	}
	
}
