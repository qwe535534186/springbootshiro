package com.study.springbootshiro.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
	private static final long serialVersionUID = -3021095758019316439L;

	private Integer id;

    private String userName;

    private String passWord;

    private Integer userEnable;
    
    private String salt;

}