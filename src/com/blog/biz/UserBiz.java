package com.blog.biz;

import java.util.List;

import com.blog.bean.User;
import com.blog.dao.UserDAO;

public class UserBiz {

	UserDAO dao = new UserDAO();
	
	
	/**
	 * 注册用户
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public int add(User t) throws Exception {
		return dao.add(t);
	}
	
	
	/**
	 * 根据条件查询用户
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<User> findByTrem(User t) throws Exception{
		return dao.findByTrem(t);
	}
	
	
	/**
	 * 用户登录
	 * @param u
	 * @return
	 * @throws Exception
	 */
	public User userlogin(User u) throws Exception {
		u.setType(0);	//1-管理员		0-用户
		List<User> list = dao.findByNickName(u);
		if (null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * 管理员登录
	 * @param u
	 * @return
	 * @throws Exception
	 */
	public User Adminlogin(User u) throws Exception {
		u.setType(1);	//1-管理员		0-用户
		List<User> list = dao.findByTrem(u);
		if (null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 找到作者(type为1的用户)
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public User findAuthor(User  t) throws Exception {
		User bean=new User();
		bean.setU_no(t.getU_no());
		bean.setType(t.getType());
		List<User> list = dao.findByTrem(bean);
		if (null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
