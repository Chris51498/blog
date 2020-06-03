package com.blog.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.blog.bean.User;
import com.blog.biz.UserBiz;

class UserBizTest {
	UserBiz biz = new UserBiz();


	@Test
	void testAdd() throws Exception {
		User user = new User();
		user.setNickname("xj");
		user.setPwd("a");
		
		biz.add(user);
	}

	@Test
	void testFindByTrem() {
		fail("Not yet implemented");
	}

}
