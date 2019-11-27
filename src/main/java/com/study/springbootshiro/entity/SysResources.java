package com.study.springbootshiro.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResources implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 827122849368383853L;

	private Integer id;

    private String userName;

    private String resUrl;

    private Integer userType;

    private Integer parentId;

    private Integer userSort;

}