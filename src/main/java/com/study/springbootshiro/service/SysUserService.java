package com.study.springbootshiro.service;

import java.util.Set;

import com.study.springbootshiro.entity.SysUser;

public interface SysUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser getUser(SysUser user);

	Set<String> findPermissionsByUserId(Integer id);

	SysUser findByUserName(String name);
}