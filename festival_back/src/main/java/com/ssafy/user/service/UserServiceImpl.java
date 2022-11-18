package com.ssafy.user.service;
 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.mapper.UserMapper;
import com.ssafy.user.model.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	private UserMapper usermapper;
	
	@Autowired
	private UserServiceImpl(UserMapper usermapper) {
		this.usermapper = usermapper;
	}
	
	@Override
	public int idCheck(String id) throws Exception {  
		return usermapper.idCheck(id);
	} 

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception { 
		System.out.println(map);
		return usermapper.loginUser(map);
	}


	@Override
	public UserDto getUser(String userId) throws Exception { 
		return usermapper.getUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		usermapper.updateUser(userDto);		
	}

	@Override
	public void deleteUser(String userid) throws Exception {
		usermapper.deleteUser(userid);		
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
		System.out.println("하이");
		usermapper.joinUser(userDto);		
	}

	/* token */
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		usermapper.saveRefreshToken(map);
		
	}

	@Override
	public Object getRefreshToken(String userid) throws SQLException {
		return usermapper.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		usermapper.deleteRefreshToken(map);
	}

}
