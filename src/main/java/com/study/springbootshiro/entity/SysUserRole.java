package com.study.springbootshiro.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole implements Serializable{
	private static final long serialVersionUID = -1826423546700582768L;

	private Integer id;

    private Integer userId;

    private Integer roleId;

}