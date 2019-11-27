package com.study.springbootshiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.springbootshiro.entity.SysUserRole;
@Mapper
public interface SysUserRoleMapper {
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
    int insert(SysUserRole record);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insertSelective(SysUserRole record);

    /**
     * 根据id查询
     * @param id
     * @return SysUserRole
     */
    SysUserRole selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SysUserRole record);

    /**
     * 修改
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SysUserRole record);
}