package com.blog.bean;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 用户信息表
	 * Chris
	 */
	private static final long serialVersionUID = 1L;

	private Integer u_no;//用户编号
	private String nickname;//用户昵称
	private String pwd;//用户密码
	private String email;//用户邮箱
	private String profession;//用户职业
	private String intro;//用户介绍
	private String u_pic;//用户头像
	private Integer type;//用户类型   1为博主  0为用户
	private String temp;
	public Integer getU_no() {
		return u_no;
	}
	public void setU_no(Integer u_no) {
		this.u_no = u_no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getU_pic() {
		return u_pic;
	}
	public void setU_pic(String u_pic) {
		this.u_pic = u_pic;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
