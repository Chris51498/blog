package com.blog.test;

import java.util.List;

import org.junit.Test;

import com.blog.bean.Article;
import com.blog.biz.ArticleBiz;

public class ArticleBizTest {
	
	ArticleBiz biz=new ArticleBiz();
	
	@Test
	public void testAddArt() throws Exception {
		Article t=new Article();
		t.setT_no("1");
		t.setTitle("我是个好人");
		t.setA_content("1111");
		int i =biz.addArt(t);
		System.out.println(i);
	}
	

}
