package com.study.springbootshiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.springbootshiro.entity.SysUserRole;
@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}