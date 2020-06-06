package com.blog.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.bean.Article;
import com.blog.biz.ArticleBiz;
import com.blog.util.LogUtil;

@WebServlet("/article.action")
public class ArticleServlet extends BaseServlet {
	ArticleBiz biz = new ArticleBiz();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 一张表的所有请求都在一个servlet处理----如何区分不同的操作---->从页面上传过来的op
		String op = request.getParameter("op");
		if ("find".equals(op)) {
			doFind(request, response);
		} else if("findByID".equals(op)){
			doFindByID(request, response);
		}
	}

	/**
	 * 查看文章详情
	 * @param request
	 * @param response
	 */
	private void doFindByID(HttpServletRequest request, HttpServletResponse response) {
		try {
			Article t=parseRequest(request, Article.class);
			Article article=biz.findByID(t);
			request.setAttribute("article", article);
			System.out.println(article);
			toPrintJson(response, article);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * 首页显示最近的六篇文章
	 * 
	 * @param request
	 * @param response
	 */
	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> map = biz.findRetArt();
			toPrintJson(response, map);
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage());
			e.printStackTrace();
		}

	}

}