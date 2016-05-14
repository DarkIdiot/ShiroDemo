package com.demo.service;

import java.util.List;

import com.demo.model.ActiveUser;
import com.demo.model.SysPermission;


public interface SysService {
	
	//根据用户id获取权限 
	public List<SysPermission> findSysPermissionList(String userid) throws Exception; 
	
	/**
	 * 
	 * <p>Title: authenticate</p>
	 * <p>Description:用户认证 </p>
	 * @param usercode 用户账号
	 * @param password 用户密码 
	 * @return ActiveUser 用户身份信息
	 * @throws Exception
	 */
	public ActiveUser authenticate(String usercode, String password)throws Exception; 

}
