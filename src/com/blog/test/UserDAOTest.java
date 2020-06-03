package com.blog.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.blog.bean.User;
import com.blog.dao.UserDAO;

class UserDAOTest {
	
	UserDAO dao = new UserDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAdd() throws Exception {
		User user = new User();
		user.setNickname("xj");
		user.setPwd("a");
		
		dao.add(user);
	}

	@Test
	void testFindByTrem() {
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testDelete() {
	}

}
