package com.blog.bean;

import java.io.Serializable;

public class Type implements Serializable{

	/**
	 * 文章类型表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer t_no;//类型编号
	private String t_name;//类型名称
	private String temp;
	public Integer getT_no() {
		return t_no;
	}
	public void setT_no(Integer t_no) {
		this.t_no = t_no;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
