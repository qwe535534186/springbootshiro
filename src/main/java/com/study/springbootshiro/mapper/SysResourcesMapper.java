package com.study.springbootshiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.springbootshiro.entity.SysResources;

@Mapper
public interface SysResourcesMapper {
    /**
     * 通過id 删除
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
     * @return int
     */
    int insertSelective(SysResources record);

    /**
     * 通过id查询
     * @param id
     * @return SysResources
     */
    SysResources selectByPrimaryKey(Integer id);

    /**
     * 通过id 详细修改
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SysResources record);

    /**
     * 通过id修改
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SysResources record);
}