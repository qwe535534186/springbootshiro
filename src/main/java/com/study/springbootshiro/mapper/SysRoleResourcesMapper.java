package com.study.springbootshiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.springbootshiro.entity.SysRoleResources;
@Mapper
public interface SysRoleResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResources record);

    int insertSelective(SysRoleResources record);

    SysRoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleResources record);

    int updateByPrimaryKey(SysRoleResources record);
}