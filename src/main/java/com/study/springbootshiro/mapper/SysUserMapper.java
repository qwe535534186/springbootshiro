package com.study.springbootshiro.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.study.springbootshiro.entity.SysUser;


@Mapper
public interface SysUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	@Select(value = "select id,user_name userName,pass_word passWord,user_enable userEnable from sys_user where user_name= #{userName} and pass_word = #{passWord}")
	SysUser getUser(SysUser user);

	@Select(value = "SELECT p.res_url FROM sys_user u, sys_role r, sys_resources p, sys_user_role ur,	sys_role_resources rp WHERE	u.id = #{id} AND u.id = ur.user_id AND r.id = ur.role_id AND r.id = rp.role_id AND p.id = rp.resources_id")
	Set<String> findPermissionsByUserId(Integer id);

	@Select(value = "select id,user_name userName,pass_word passWord,user_enable userEnable,salt from sys_user where user_name= #{userName}")
	SysUser findByUserName(String userName);
}