package com.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.blog.bean.User;
import com.blog.commons.DbHelper;

public class UserDAO implements BaseDAO<User>{
	DbHelper db = new DbHelper();

	/**
	 * 添加用户
	 */
	@Override
	public int add(User t) throws Exception {
		String sql = "insert into user values(null,?,MD5(?),?,?,?,?,0,null)";
		return db.update(sql, t.getNickname(),t.getPwd(),t.getEmail(),t.getProfession(),t.getIntro(),t.getU_pic());
	}

	@Override
	public List<User> findByTrem(User t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select u_no,nickname,pwd,email,profession,intro,u_pic,type from user where 1=1 ");
		List<Object> params = null;
		if (null!=t) {
			params = new ArrayList<Object>();
			if (null!=t.getU_no()) {
				sb.append(" and u_no = ? ");
				params.add(t.getU_no());
			}
			if (null!=t.getNickname()) {
				sb.append(" and nickname = ? ");
				params.add(t.getNickname());
			}
			if (null!=t.getPwd()) {
				sb.append(" and pwd = MD5(?) ");
				params.add(t.getPwd());
			}
			if (null!=t.getEmail()) {
				sb.append(" and email = ? ");
				params.add(t.getEmail());
			}
			if (null!=t.getType()) {
				sb.append(" and type = ? ");
				params.add(t.getType());
			}
		}
		return db.findMutipl(sb.toString(), params, User.class);
				
	}

	@Override
	public int update(User t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
