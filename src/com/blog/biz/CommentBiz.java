package com.blog.biz;

import java.util.List;

import com.blog.bean.Comment;
import com.blog.bean.CommentVO;
import com.blog.dao.CommentDAO;

public class CommentBiz {
	CommentDAO dao=new CommentDAO();
	
	/**
	 * 添加评论
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int AddComment(Comment t) throws Exception {
		
		return dao.add(t);
	}

	/**
	 * 查询所有的评论信息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<CommentVO>  findByAno(CommentVO t) throws Exception {
		
		return dao.findComment(t);
	}
}
