package com.ssafy.festival.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.festival.model.FestivalDto;


@Mapper
public interface FestivalMapper {
	int writeFestival(FestivalDto festival) throws Exception;
	List<FestivalDto> listFestival(Map<String,Object> map) throws Exception;
	int totalFestivalCount() throws Exception;
	FestivalDto getFestival(String festivalId) throws Exception;
	void deleteFestival(String festivalId) throws Exception;
}
