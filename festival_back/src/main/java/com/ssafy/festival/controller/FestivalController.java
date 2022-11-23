package com.ssafy.festival.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.festival.model.FestivalDto;
import com.ssafy.festival.service.FestivalService;
import com.ssafy.jwt.service.JwtService;
import com.ssafy.util.ParameterCheck;

@RestController
@RequestMapping("/festival")
public class FestivalController {

	private JwtService jwtService;
	private FestivalService service;
	
	FestivalController(FestivalService service,JwtService jwtService){
		this.service = service;
		this.jwtService= jwtService;
	}
	
	//축제 불러오기
	@GetMapping("/{festivalId}")
	public ResponseEntity<?> getarticle(@PathVariable("festivalId") String festivalId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FestivalDto festival = service.getFestival(festivalId);
			System.out.println(festival);
	        map.put("festival", festival);
	        map.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}
	}
	
	
	//축제 리스트
	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String,Object> map) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			
			//현재 날짜 이후 모든 축제
				//every
			//map.put("every", true);//현재 날짜 이후 축제들 불러오기			
			//해당 지역의 축제
				//area:지역명
			//진행중인 축제
				//now:true
			System.out.println(map);
			if(map.get("start")!=null) {
				map.put("start", Integer.parseInt((String)map.get("start")));				
				map.put("spp", Integer.parseInt((String)map.get("spp")));
			}
			List<FestivalDto> list = service.listFestival(map);
			res.put("festivalList", list);
			res.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		}
	}
	
	
	@PostMapping()
	public ResponseEntity<?> write(@RequestBody FestivalDto festival, HttpServletRequest request) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			service.writeFestival(festival);
			res.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		}catch (Exception e) {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{festivalId}")
	public ResponseEntity<?> delete(@PathVariable("festivalId") String festivalId,HttpServletRequest request) throws NumberFormatException, Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			service.deleteFestival(festivalId);
			res.put("status", "ok");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
		}catch (Exception e) {
			res.put("status", "fail");
			return new ResponseEntity<Map<String, Object>>(res,HttpStatus.OK);
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
