package com.ssafy.festival.service;

import java.util.List;
import java.util.Map;

import com.ssafy.festival.model.FestivalDto;

public interface FestivalService {
	int writeFestival(FestivalDto festival) throws Exception;
	List<FestivalDto> listFestival(Map<String,Object> map) throws Exception;
	int totalFestivalCount() throws Exception;
	FestivalDto getFestival(String festivalId) throws Exception;
	void deleteFestival(String festivalId) throws Exception;
}
