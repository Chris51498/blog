package com.blog.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.bean.User;
import com.blog.biz.UserBiz;

@WebServlet("/user.action")
public class UserServlet extends BaseServlet{

	UserBiz biz = new UserBiz();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("login".equals(op)) {
			doLogin(request,response);
		}else if("find".equals(op)) {
			doFind(request,response);
		}else if("findPage".equals(op)){
			doPage(request,response);
		}else if("register".equals(op)){
			doRegister(request,response);
		}
	}

	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		try {
			User bean = this.parseRequest(request, User.class);
			List<User> list = biz.findByTrem(bean);
			toPrintJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void doPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 用户注册（不用）	注册时发送userFileUpload.action
	 * @param request
	 * @param response
	 */
	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			User bean = this.parseRequest(request, User.class);
			int i = biz.add(bean);
			toPrintJson(response, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			User bean = this.parseRequest(request, User.class);
			User user = biz.userlogin(bean);
			HttpSession session = request.getSession();
			
			
			
			String yzm = request.getParameter("yzm");	//用户填写的验证码
			String validateCode = (String) session.getAttribute("validateCode");	//生成的验证码
			
			if (!yzm.equalsIgnoreCase(validateCode)) {
				toPrintJson(response, -1);
			}else if(user != null){
				//用户登录后，存到session
				session.setAttribute("user", user);
				toPrintJson(response, 1);
			}else {
				toPrintJson(response, 0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
}
