package com.study.springbootshiro.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5876813970903394884L;

	private Integer id;

    private String roleDesc;

}