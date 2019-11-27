package com.study.springbootshiro.service;

import com.study.springbootshiro.entity.SysResources;

public interface SysResourcesService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysResources record);

    int insertSelective(SysResources record);

    SysResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResources record);

    int updateByPrimaryKey(SysResources record);
}