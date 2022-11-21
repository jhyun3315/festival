package com.ssafy.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

import com.ssafy.user.model.UserDto;
import com.ssafy.user.service.UserService;
import com.ssafy.jwt.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 

@RestController
@RequestMapping("/user")
public class UserController {
//	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private JwtService jwtService;
	private final UserService userService;
	

	@Autowired
	public UserController(UserService userService,JwtService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}
		
	
	//회원가입
	@PostMapping()
	public ResponseEntity<?> join(@RequestBody UserDto userDto, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.joinUser(userDto);
			map.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}
	}
	
	//마이 페이지
	@GetMapping()
	public ResponseEntity<?> mypage(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {			
				String userId = jwtService.getUserId();
				UserDto userDto = userService.getUser(userId);
				if(userDto !=null) {
					map.put("status", "ok");
					map.put("user", userDto);
					return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
				}else {
					System.out.println("내 유저가 아님");
					map.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);	
				}
			}catch (Exception e) {
				System.out.println("실패");
				map.put("status", "fail");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);	
			}
		}else {
			System.out.println("인증만료");
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.UNAUTHORIZED);	
		}
	}

	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Map<String,String> map,HttpServletRequest request) throws Exception {
		UserDto user = new UserDto();
		user.setUserId(map.get("userId"));
		user.setUserPw(map.get("userPw"));
		user.setUserName(map.get("userName"));
		user.setEmail(map.get("email"));
		user.setPhoneNumber(map.get("phoneNumber"));
		
		Map<String, Object> res = new HashMap<String, Object>();
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				if(!userId.equals(map.get("userId"))) {
					res.put("status", "fail");
					return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
				}
				
				userService.updateUser(user);
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
	
	@DeleteMapping()
	public ResponseEntity<?> delete(HttpServletRequest request) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {			
			try {
				String userId = jwtService.getUserId();
				userService.deleteUser(userId);
				res.put("status", "ok");
				status = HttpStatus.ACCEPTED;
			}catch (Exception e) {
				res.put("status", "fail");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			res.put("status", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(res,status);

	}
	
	
	
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		int cnt = userService.idCheck(userId);
		return cnt + "";
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody HashMap<String, String> map) {		
		try {
			UserDto userDto = userService.loginUser(map);
			System.out.println(userDto);
			if(userDto != null) {
				System.out.println("유저 확인 완료");
				String accessToken = jwtService.createAccessToken("userid", userDto.getUserId());// key, data
				System.out.println("토큰생성1: "+accessToken);
				String refreshToken = jwtService.createRefreshToken("userid", userDto.getUserId());// key, data
				System.out.println("토큰생성2: "+refreshToken);
				userService.saveRefreshToken(userDto.getUserId(), refreshToken);
				
				System.out.println("rf토큰 저장");
				
		        Map<String, Object> res = new HashMap<>();
		        res.put("status", "ok");
		        res.put("access-token", accessToken);
		        res.put("refresh-token", refreshToken);
				return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK); 
				
			} else {
				System.out.println("유저 없음");
				 Map<String, Object> res = new HashMap<>();
			     res.put("status", "fail"); 
				return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);   
			}
		} catch (Exception e) {
			System.out.println("잘못된 톰큰");
			e.printStackTrace(); 
			Map<String, Object> res = new HashMap<>();
		    res.put("status", "fail"); 
			return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);    
		}
	}
	
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String userId, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
//			String token = request.getHeader("refresh-token");
//			System.out.println(token);
			System.out.println("가자");
//			String userId = jwtService.getUserId();
			System.out.println("로그인 확인"+userId);
			userService.deleteRefreshToken(userId);
			res.put("status", "ok");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			res.put("status", "fail");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(res, status);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		Map<String, Object> res = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getUserId());
				res.put("access-token", accessToken);
				res.put("status", "ok");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(res, status);
	}
	
	//?
	@GetMapping("/list")
	public String list() {
		return "redirect:/assets/list.html";
	}
	
	
	 
}
