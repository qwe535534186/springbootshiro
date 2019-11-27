package com.study.springbootshiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.springbootshiro.entity.SysRoleResources;
@Mapper
public interface SysRoleResourcesMapper {
    /**
     *  删除
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
     * 根据id查询
     * @param id
     * @return
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