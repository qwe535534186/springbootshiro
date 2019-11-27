package com.study.springbootshiro.service;

import java.util.Set;

import com.study.springbootshiro.entity.SysRole;

public interface SysRoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	Set<String> findRoleNameByUserId(Integer id);
}