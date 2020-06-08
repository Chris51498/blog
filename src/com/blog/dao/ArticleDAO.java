package com.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.blog.bean.Article;
import com.blog.commons.DbHelper;

public class ArticleDAO implements BaseDAO<Article>{
	DbHelper db=new DbHelper();

	@Override
	public int add(Article t) throws Exception {
		String sql="insert into article values(null,1,?,now(),?,0,?,null,null) ";
		return db.update(sql, t.getT_no(),t.getTitle(),t.getA_content());
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


	/**
	 * 查询所有文章	弃用
	 * @return
	 * @throws Exception
	 */
	public List<Article> findAll() throws Exception{
		String sql = "select a_no,u_no,t_no,a_time,title,a_num,a_content,a_pic,temp from article order by a_time desc";
		return db.findMutipl(sql, null, Article.class);
	}
	

	/**
	 * 根据文章类型查看
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<Article> findByType(Article t) throws Exception{
		StringBuffer sb = new StringBuffer();
		List<Object> params =null;
		sb.append(" select a_no,u_no,t_no,a_time,title,a_num,a_content,a_pic,temp from article where 1=1 ");
		if (null!=t) {
			params=new ArrayList<>();
			if (null!=t.getT_no()) {
				sb.append(" and t_no = ? ");
				params.add(t.getT_no());
			}
		}
		sb.append(" order by a_time desc ");
		System.out.println(sb.toString());
		return db.findMutipl(sb.toString(), params, Article.class);
	}
	
	
	/**
	 * 查询每类文章数量
	 * @return
	 * @throws Exception 
	 */
	public List<Article> findTypeCount() throws Exception{
		String sql = "select t_no ,count(a_no) as count from article group by t_no order by t_no asc";
		return db.findMutipl(sql, null, Article.class);
	}
	
}
