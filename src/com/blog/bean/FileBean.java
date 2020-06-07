package com.blog.bean;

import java.io.Serializable;

import javax.management.loading.PrivateClassLoader;

public class FileBean implements Serializable{

	
	private String fileName; //文件名
	private String upload;//文件上传的路径  表单 传过来的字段名
	private String url;
	private Integer success;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "FileBean [fileName=" + fileName + ", upload=" + upload + ", url=" + url + ", success=" + success + "]";
	}

	
	
}
