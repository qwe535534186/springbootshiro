package com.study.springbootshiro.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.study.springbootshiro.entity.SysRole;

@Mapper
public interface SysRoleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	@Select(value = "SELECT	role_desc FROM sys_user u, sys_role r, sys_user_role ur WHERE u.id = #{id} AND u.id = ur.user_id AND r.id = ur.role_id")
	Set<String> findRoleNameByUserId(Integer id);
}