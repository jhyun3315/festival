package com.ssafy.favor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favor.model.FavorDto;


@Mapper
public interface FavorMapper {
	public void favorDelete(Map<String, Object> map) throws Exception;
	public int favorAdd(Map<String, Object> map) throws Exception;
	public List<FavorDto> favorList(String userId) throws Exception;
}
