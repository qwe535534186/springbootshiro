package com.study.springbootshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springbootshiro.entity.SysRoleResources;
import com.study.springbootshiro.mapper.SysRoleResourcesMapper;
import com.study.springbootshiro.service.SysRoleResourcesService;

@Service
public class SysRoleResourcesServiceImpl implements SysRoleResourcesService {

	@Autowired
	private SysRoleResourcesMapper dao;
	
	@Override
    public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(SysRoleResources record) {
		return dao.insert(record);
	}

	@Override
    public int insertSelective(SysRoleResources record) {
		return dao.insertSelective(record);
	}

	@Override
    public SysRoleResources selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(SysRoleResources record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(SysRoleResources record) {
		return dao.updateByPrimaryKey(record);
	}

}
