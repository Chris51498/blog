package com.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.blog.bean.Article;
import com.blog.commons.DbHelper;

public class ArticleDAO implements BaseDAO<Article>{
	DbHelper db=new DbHelper();

	@Override
	public int add(Article t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Article> findByTrem(Article t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int update(Article t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Article t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 首页查询最近的六条文章
	 * @return
	 * @throws Exception
	 */
	public List<Article> findRetArt() throws Exception{
		String sql="select a_no,u_no,t_no,DATE_FORMAT(a_time,'%Y-%m-%d %H:%i') as a_time,title,a_num,substring(a_content,1,100) as a_content from "
				+ "article order by a_no desc limit 6";
		return db.findMutipl(sql, null, Article.class);
	}
	
	
	/**
	 * 根据a_no 查询
	 */
	
	public Article findByID(Article t) throws Exception {
		String sql="select a_content from article where a_no=? ";
		List<Object>params=new ArrayList<Object>();
		params.add(t.getA_no());
		return  db.find(sql, params, Article.class);
	}



	

}
