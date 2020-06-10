package com.blog.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blog.bean.Comment;
import com.blog.bean.CommentVO;
import com.blog.commons.DbHelper;

public class CommentDAO implements BaseDAO<Comment>{
	DbHelper db=new DbHelper();

	/**
	 *添加评论
	 */
	
	@Override
	public int add(Comment t) throws Exception {
		String sql="insert into `comment`  values(null,?,?,?,now(),NULL);";
		return db.update(sql, t.getA_no(),t.getU_no(),t.getC_content());
	}

	@Override
	public List<Comment> findByTrem(Comment t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Comment t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Comment t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 查询所有的评论信息
	 * @throws Exception 
	 */
	public List<CommentVO> findComment(CommentVO t) throws Exception {
		String sql="select c.*,u.nickname,u.u_pic from `comment` c ,`user` u where c.u_no=u.u_no and a_no=?";
		List<Object>params=new ArrayList<Object>();
		params.add(t.getA_no());
		return db.findMutipl(sql, params, CommentVO.class);
	}

}
