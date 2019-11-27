package com.study.springbootshiro.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.study.springbootshiro.entity.SysRole;

@Mapper
public interface SysRoleMapper {
	/**
	 * 根据id 删除
	 * @param id
	 * @return 成功条数
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 新增
	 * @param record
	 * @return 成功条数
	 */
	int insert(SysRole record);

	/**
	 * 新增 - 详细
	 * @param record
	 * @return 成功条数
	 */
	int insertSelective(SysRole record);

	/**
	 * 根据id 查看
	 * @param id
	 * @return SysRole
	 */
	SysRole selectByPrimaryKey(Integer id);

	/**
	 * 根据id 修改
	 * @param record
	 * @return 成功条数
	 */
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * 根据id 修改
	 * @param record
	 * @return 成功条数
	 */
	int updateByPrimaryKey(SysRole record);

	/**
	 * 根据用户id 查询角色名称
	 * @param id
	 * @return
	 */
	@Select(value = "SELECT	role_desc FROM sys_user u, sys_role r, sys_user_role ur WHERE u.id = #{id} AND u.id = ur.user_id AND r.id = ur.role_id")
	Set<String> findRoleNameByUserId(Integer id);
}