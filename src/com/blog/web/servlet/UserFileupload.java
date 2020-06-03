package com.blog.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.bean.User;
import com.blog.biz.UserBiz;
import com.blog.util.UserUploadUtil;

@WebServlet("/userFileUpload.action")
public class UserFileupload extends BaseServlet{

	UserBiz biz = new UserBiz();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//解析请求对象
			User bean = UserUploadUtil.parseRequest(request, User.class);
			int i = biz.add(bean);
			toPrintJson(response, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
