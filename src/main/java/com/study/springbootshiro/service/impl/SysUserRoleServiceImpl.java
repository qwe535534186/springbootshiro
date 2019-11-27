package com.study.springbootshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springbootshiro.entity.SysUserRole;
import com.study.springbootshiro.mapper.SysUserRoleMapper;
import com.study.springbootshiro.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleMapper dao;
	
	@Override
    public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(SysUserRole record) {
		return dao.insert(record);
	}

	@Override
    public int insertSelective(SysUserRole record) {
		return dao.insertSelective(record);
	}

	@Override
    public SysUserRole selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(SysUserRole record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(SysUserRole record) {
		return dao.updateByPrimaryKey(record);
	}

}
