package com.ssafy.favor.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favor.model.FavorDto;
import com.ssafy.favor.service.FavorService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.user.model.UserDto;

@RestController
@RequestMapping("/favor")
public class FavorController {

	private JwtService jwtService;
	private FavorService service;
	
	FavorController(FavorService service,JwtService jwtService){
		this.service = service;
		this.jwtService= jwtService;
	}
	
	@GetMapping()
	private ResponseEntity<?> favorList(HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				List<FavorDto> favorList = service.favorList(userId);
				
				System.out.println(favorList);
				
				map.put("favorlist",favorList);
				map.put("status", "ok");
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);	
			}catch (Exception e) {
				// TODO: handle exception
				map.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			}
		}else {
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.UNAUTHORIZED);	
		}

	}
	
	@PostMapping()
	private ResponseEntity<?> favorAdd(@RequestBody Map<String,String> map, HttpServletRequest request){
		Map<String, Object> res = new HashMap<String, Object>();
		String festivalId = map.get("festivalId");
		System.out.println(festivalId);
//		code = code.replaceAll("^\"|\"$", "");
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				int count = service.favorAdd(userId, festivalId);
				res.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				res.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
			}
		}else {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.UNAUTHORIZED);	
		}
	}
	
	@DeleteMapping("/{festivalId}")
	private ResponseEntity<?> favorDelete( @PathVariable("festivalId") String festivalId, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				service.favorDelete(userId, festivalId);	
				map.put("status", "ok");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				map.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			}
		}else {
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.UNAUTHORIZED);	
		}
		
	}
	
	

	
	
}
