package com.blog.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.bean.Comment;
import com.blog.bean.CommentVO;
import com.blog.bean.User;
import com.blog.biz.CommentBiz;
@WebServlet("/comment.action")
public class CommentServlet extends BaseServlet{
	CommentBiz biz=new CommentBiz();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("Add".equals(op)) {
			doAdd(request,response);
		}else if("findcomment".equals(op)) {
			doFindComment(request,response);
		}
	}

	
	/**
	 * 查询所有的评论
	 * @param request
	 * @param response
	 */
	private void doFindComment(HttpServletRequest request, HttpServletResponse response) {
		try {
			CommentVO t=parseRequest(request, CommentVO.class);
			List<CommentVO> list =biz.findByAno(t);
			if(list!=null) {
				toPrintJson(response, list);
			}else {
				toPrintJson(response, 0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * 添加评论
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			Comment t=parseRequest(request, Comment.class);
			HttpSession session = request.getSession();
			User user=(User) session.getAttribute("user");
			t.setU_no(user.getU_no());
			int i=biz.AddComment(t);
			toPrintJson(response, i);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
