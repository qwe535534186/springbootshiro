package com.study.springbootshiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springbootshiro.entity.SysUser;
import com.study.springbootshiro.mapper.SysUserMapper;
import com.study.springbootshiro.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper dao;
	
	@Override
    public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(SysUser record) {
		return dao.insert(record);
	}

	@Override
    public int insertSelective(SysUser record) {
		return dao.insertSelective(record);
	}

	@Override
    public SysUser selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(SysUser record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(SysUser record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
    public SysUser getUser(SysUser user) {
		return dao.getUser(user);
	}

	@Override
    public Set<String> findPermissionsByUserId(Integer id) {
		return dao.findPermissionsByUserId(id);
	}

	@Override
    public SysUser findByUserName(String name) {
		return dao.findByUserName(name);
	}

}
