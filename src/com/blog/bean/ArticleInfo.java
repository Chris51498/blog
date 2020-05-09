package com.blog.bean;

import java.io.Serializable;

public class ArticleInfo implements Serializable{

	/**
	 * 文章信息表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer a_no;//文章编号
	private Integer u_no;//用户编号
	private String t_name;//文章类型名称
	private String a_time;//文章发表时间
	private String title;//文章标题
	private Integer a_num;//文章浏览次数
	private String temp;
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
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getA_time() {
		return a_time;
	}
	public void setA_time(String a_time) {
		this.a_time = a_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getA_num() {
		return a_num;
	}
	public void setA_num(Integer a_num) {
		this.a_num = a_num;
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
