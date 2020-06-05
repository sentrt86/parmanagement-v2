package com.htc.par.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.htc.par.model.UserMaster;

@Service
public interface IUserMasterService {
	List<UserMaster> getUserByUserName(String username) throws Exception;
	List<UserMaster> getAllUsers() throws Exception;
	public int getNextUserId() throws Exception;
	public String deleteUser(int userId) throws Exception;
	public String createUser(UserMaster userMaster)  throws Exception ;
	public String updateUser(UserMaster userMaster)  throws Exception;

}
