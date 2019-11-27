package com.study.springbootshiro.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleResources implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1986774825061618645L;

	private Integer id;

    private Integer roleId;

    private Integer resourcesId;

}