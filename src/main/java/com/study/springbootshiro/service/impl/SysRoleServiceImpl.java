package com.study.springbootshiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springbootshiro.entity.SysRole;
import com.study.springbootshiro.mapper.SysRoleMapper;
import com.study.springbootshiro.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper dao;
	
	@Override
    public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(SysRole record) {
		return dao.insert(record);
	}

	@Override
    public int insertSelective(SysRole record) {
		return dao.insertSelective(record);
	}

	@Override
    public SysRole selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(SysRole record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(SysRole record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
    public Set<String> findRoleNameByUserId(Integer id) {
		return dao.findRoleNameByUserId(id);
	}

}
