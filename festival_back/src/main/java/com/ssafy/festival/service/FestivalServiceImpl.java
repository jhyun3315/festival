package com.ssafy.festival.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.festival.mapper.FestivalMapper;
import com.ssafy.festival.model.FestivalDto;

@Service
public class FestivalServiceImpl implements FestivalService {

	
	private FestivalMapper mapper;
	public FestivalServiceImpl(FestivalMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int writeFestival(FestivalDto festival) throws Exception {
		return mapper.writeFestival(festival);
	}

	@Override
	public List<FestivalDto> listFestival(Map<String, Object> map) throws Exception {
		return mapper.listFestival(map);
	}

	@Override
	public int totalFestivalCount() throws Exception {
		return mapper.totalFestivalCount();
	}

	@Override
	public FestivalDto getFestival(String festivalId) throws Exception {
		return mapper.getFestival(festivalId);
	}

	@Override
	public void deleteFestival(String festivalId) throws Exception {
		mapper.deleteFestival(festivalId);
	}

}
