package com.study.springbootshiro.service;

import java.util.Set;

import com.study.springbootshiro.entity.SysUser;

public interface SysUserService {
    /**
     * 删除
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insert(SysUser record);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insertSelective(SysUser record);

    /**
     * 通过id查询
     * @param id
     * @return SysUser
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 获取用户信息
     * @param user
     * @return SysUser
     */
	SysUser getUser(SysUser user);

    /**
     * 通过id查询权限
     * @param id
     * @return Set<String>
     */
	Set<String> findPermissionsByUserId(Integer id);

    /**
     * 通过username 查询
     * @param name
     * @return SysUser
     */
	SysUser findByUserName(String name);
}