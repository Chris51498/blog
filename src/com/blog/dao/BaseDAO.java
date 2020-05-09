package com.blog.dao;
/**
 * 数据库基本操作的接口
 * @author xj
 *
 */

import java.util.List;

public interface BaseDAO<T> {

	
	/**
	 * 添加操作
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int add(T t) throws Exception;
	
	
	/**
	 * 根据条件查询
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<T> findByTrem(T t) throws Exception;
	
	
	/**
	 * 修改操作
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int update(T t) throws Exception;
	
	
	/**
	 * 删除操作	物理删除	逻辑删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int delete(T t) throws Exception;
}
