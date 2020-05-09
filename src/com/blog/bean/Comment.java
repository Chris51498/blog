package com.blog.bean;

import java.io.Serializable;

public class Comment implements Serializable{

	/**
	 * 文章评论表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;

	private Integer c_no;//评论编号
	private Integer a_no;//文章编号
	private Integer u_no;//用户编号
	private String c_content;//评论内容
	private String c_time;//评论时间
	private String temp;
	public Integer getC_no() {
		return c_no;
	}
	public void setC_no(Integer c_no) {
		this.c_no = c_no;
	}
	public Integer getA_no() {
		return a_no;
	}
	public void setA_no(Integer a_no) {
		this.a_no = a_no;
	}
	public Integer getU_no() {
		return u_no;
	}
	public void setU_no(Integer u_no) {
		this.u_no = u_no;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_time() {
		return c_time;
	}
	public void setC_time(String c_time) {
		this.c_time = c_time;
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
