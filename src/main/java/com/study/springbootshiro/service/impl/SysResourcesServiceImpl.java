package com.study.springbootshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springbootshiro.entity.SysResources;
import com.study.springbootshiro.mapper.SysResourcesMapper;
import com.study.springbootshiro.service.SysResourcesService;

@Service
public class SysResourcesServiceImpl implements SysResourcesService {

	@Autowired
	private SysResourcesMapper dao;
	
	@Override
    public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(SysResources record) {
		return dao.insert(record);
	}

	@Override
    public int insertSelective(SysResources record) {
		return dao.insertSelective(record);
	}

	@Override
    public SysResources selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(SysResources record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(SysResources record) {
		return dao.updateByPrimaryKey(record);
	}

}
