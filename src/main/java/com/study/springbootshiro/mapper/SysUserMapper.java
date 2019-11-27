package com.study.springbootshiro.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.study.springbootshiro.entity.SysUser;


@Mapper
public interface SysUserMapper {
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
	 * 通过username password 查询
	 * @param user
	 * @return SysUser
	 */
	@Select(value = "select id,user_name userName,pass_word passWord,user_enable userEnable from sys_user where user_name= #{userName} and pass_word = #{passWord}")
	SysUser getUser(SysUser user);

	/**
	 * 根据id 查询权限名
	 * @param id
	 * @return Set<String>
	 */
	@Select(value = "SELECT p.res_url FROM sys_user u, sys_role r, sys_resources p, sys_user_role ur,	sys_role_resources rp WHERE	u.id = #{id} AND u.id = ur.user_id AND r.id = ur.role_id AND r.id = rp.role_id AND p.id = rp.resources_id")
	Set<String> findPermissionsByUserId(Integer id);

	/**
	 * 通过username 查询
	 * @param userName
	 * @return SysUser
	 */
	@Select(value = "select id,user_name userName,pass_word passWord,user_enable userEnable,salt from sys_user where user_name= #{userName}")
	SysUser findByUserName(String userName);
}