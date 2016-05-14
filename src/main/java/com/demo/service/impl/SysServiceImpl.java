package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SysPermissionCustomMapper;
import com.demo.dao.SysUserMapper;
import com.demo.exception.CustomException;
import com.demo.model.ActiveUser;
import com.demo.model.SysPermission;
import com.demo.model.SysUser;
import com.demo.model.SysUserExample;
import com.demo.service.SysService;
import com.demo.util.MD5;

@Service
public class SysServiceImpl implements SysService {
	
	@Autowired
	private SysPermissionCustomMapper sysPermissionCustomMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;


	@Override
	public List<SysPermission> findSysPermissionList(String userid)
			throws Exception {
		
		return sysPermissionCustomMapper.findMenuListByUserId(userid);
	}


	@Override
	public ActiveUser authenticate(String usercode, String password)
			throws Exception {
		
		//账号和密码非空校验
		if (usercode == null || password == null) {
			throw new CustomException("账号密码不能为空!");
		}
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsercodeEqualTo(usercode);
		List<SysUser> userList = sysUserMapper.selectByExample(sysUserExample);
		if(userList == null || userList.size()<=0){
			throw new CustomException("账号不存在!");
		}
		SysUser sysUser = userList.get(0);
		//密码 
		String password_fromdb = sysUser.getPassword();
		
		//输入 密码 和数据库密码 比较
		if(!password_fromdb.equalsIgnoreCase(new MD5().getMD5ofStr(password))){
			throw new CustomException("账号或密码 错误 !");
		}
		//认证通过，返回用户身份
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsername(sysUser.getUsername());
		activeUser.setUsercode(sysUser.getUsercode());
		
		//菜单列表
		List<SysPermission> menus = sysPermissionCustomMapper.findMenuListByUserId(sysUser.getId());
		activeUser.setMenus(menus);
		//权限列表
		List<SysPermission> permissions = sysPermissionCustomMapper.findPermissionListByUserId(sysUser.getId());
		activeUser.setPermissions(permissions);
		
		return activeUser;
	}

}
