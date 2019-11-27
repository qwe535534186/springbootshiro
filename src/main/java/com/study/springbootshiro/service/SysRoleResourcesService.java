package com.study.springbootshiro.service;

import com.study.springbootshiro.entity.SysRoleResources;

public interface SysRoleResourcesService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResources record);

    int insertSelective(SysRoleResources record);

    SysRoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleResources record);

    int updateByPrimaryKey(SysRoleResources record);
}