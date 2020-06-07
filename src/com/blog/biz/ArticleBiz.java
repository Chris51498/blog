package com.blog.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blog.bean.Article;
import com.blog.dao.ArticleDAO;

public class ArticleBiz {
	ArticleDAO dao=new ArticleDAO();
	/**
	 * 首页文章查询
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findRetArt() throws Exception{
		Map<String , Object>map=new HashMap<String, Object>();
		List<Article> list=dao.findRetArt();
		map.put("articles", list);
		return map;
	}
	
	
	/**
	 * 查看文章详情
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Article findByID(Article t) throws Exception {
		Article article=dao.findByID(t);
		return article;
	}
	
	
	/**
	 * 查看所有文章
	 * @return
	 * @throws Exception
	 */
	public List<Article> findAll() throws Exception{
		return dao.findAll();
	}
	
	
	/**
	 * 根据分类查询文章
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<Article> findByType(Article t) throws Exception{
		return dao.findByType(t);
	}

}
