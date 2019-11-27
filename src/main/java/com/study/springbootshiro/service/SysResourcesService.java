package com.study.springbootshiro.service;

import com.study.springbootshiro.entity.SysResources;

public interface SysResourcesService {

    /**
     * 根据id 删除
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insert(SysResources record);

    /**
     * 新增
     * @param record
     * @return 成功条数
     */
    int insertSelective(SysResources record);

    /**
     * 根据id 查询
     * @param id
     * @return SysResources
     */
    SysResources selectByPrimaryKey(Integer id);

    /**
     * 根据id 修改
     * @param record
     * @return 成功条数
     */
    int updateByPrimaryKeySelective(SysResources record);

    /**
     * 修改
     * @param record
     * @return 成功条数
     */
    int updateByPrimaryKey(SysResources record);
}