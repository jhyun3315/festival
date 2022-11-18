package com.ssafy.favor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.favor.mapper.FavorMapper;
import com.ssafy.favor.model.FavorDto;

@Service
public class FavorServiceImpl implements FavorService{

	FavorMapper mapper;
	FavorServiceImpl(FavorMapper mapper){
		this.mapper = mapper;
	}
	
	
	@Override
	public void favorDelete(String userId, String festivalId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId",userId);
		map.put("festivalId", festivalId);
		
		mapper.favorDelete(map);
	}


	@Override
	public int favorAdd(String userId, String festivalId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("festivalId", festivalId);
		return mapper.favorAdd(map);
	}

	@Override
	public List<FavorDto> favorList(String userId) throws Exception {
		return mapper.favorList(userId);
	}
	
}
