package com.study.springbootshiro.service;

import com.study.springbootshiro.entity.SysRoleResources;

public interface SysRoleResourcesService {

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
    int insert(SysRoleResources record);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insertSelective(SysRoleResources record);

    /**
     * 查询
     * @param id
     * @return SysRoleResources
     */
    SysRoleResources selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SysRoleResources record);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SysRoleResources record);
}