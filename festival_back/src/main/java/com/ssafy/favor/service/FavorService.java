package com.ssafy.favor.service;

import java.util.List;

import com.ssafy.favor.model.FavorDto;

public interface FavorService {
	public void favorDelete(String userId, String dongCode) throws Exception;
	public int favorAdd(String userId, String dongCode) throws Exception;
	public List<FavorDto> favorList(String userId) throws Exception;

}
