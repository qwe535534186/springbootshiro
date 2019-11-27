package com.study.springbootshiro.service;

import java.util.Set;

import com.study.springbootshiro.entity.SysRole;

public interface SysRoleService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(SysRole record);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insertSelective(SysRole record);

    /**
     * 通过id查询
     * @param id
     * @return SysRole
     */
    SysRole selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SysRole record);

    /**
     * 通过用户id 查询角色名
     * @param id
     * @return Set<String>
     */
	Set<String> findRoleNameByUserId(Integer id);
}