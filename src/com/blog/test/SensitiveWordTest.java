package com.blog.test;


import org.junit.jupiter.api.Test;

import com.blog.util.SensitiveWord;

class SensitiveWordTest {
	
	SensitiveWord sw = new SensitiveWord();

	@Test
	void testCheckWord() {
		sw.checkWord("sb 傻逼 哈哈哈哈哈哈");
	}

}
