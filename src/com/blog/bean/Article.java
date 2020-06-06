package com.blog.bean;

import java.io.Serializable;

public class Article implements Serializable{

	/**
	 * 文章信息表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer a_no;//文章编号
	private Integer u_no;//用户编号
	private String t_no;//文章类型编号
	private String a_time;//文章发表时间
	private String title;//文章标题
	private Integer a_num;//文章浏览次数
	private String a_content;//文章内容
	private String a_pic;//文章图片
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
	public String getT_no() {
		return t_no;
	}
	public void setT_no(String t_no) {
		this.t_no = t_no;
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
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
	public String getA_pic() {
		return a_pic;
	}
	public void setA_pic(String a_pic) {
		this.a_pic = a_pic;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	@Override
	public String toString() {
		return "Article [a_no=" + a_no + ", u_no=" + u_no + ", t_no=" + t_no + ", a_time=" + a_time + ", title=" + title
				+ ", a_num=" + a_num + ", a_content=" + a_content + ", a_pic=" + a_pic + ", temp=" + temp + "]";
	}
	
	
	
	
	
}
