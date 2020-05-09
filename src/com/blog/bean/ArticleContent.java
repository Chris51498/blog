package com.blog.bean;

import java.io.Serializable;


public class ArticleContent implements Serializable{
	/**
	 * 文章内容表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;
	private Integer ac_no;//文章内容编号
	private Integer a_no;//文章编号
	private String ac_content;//文章内弄
	private String ac_pic;//文章图片
	private String temp;
	public Integer getAc_no() {
		return ac_no;
	}
	public void setAc_no(Integer ac_no) {
		this.ac_no = ac_no;
	}
	public Integer getA_no() {
		return a_no;
	}
	public void setA_no(Integer a_no) {
		this.a_no = a_no;
	}
	public String getAc_content() {
		return ac_content;
	}
	public void setAc_content(String ac_content) {
		this.ac_content = ac_content;
	}
	public String getAc_pic() {
		return ac_pic;
	}
	public void setAc_pic(String ac_pic) {
		this.ac_pic = ac_pic;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	
}
