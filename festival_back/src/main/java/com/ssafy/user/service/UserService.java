package com.ssafy.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
 
import com.ssafy.user.model.UserDto;

public interface UserService {
	int idCheck(String userId) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	UserDto getUser(String userId) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String userid) throws Exception;
	
	/*token*/
	public void saveRefreshToken(String userid, String refreshToken) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(String userid) throws SQLException;
}
