package com.blog.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//import oracle.sql.BLOB;


public class DbHelper {
	//private Connection conn;
	//private PreparedStatement pstmt;
	//private ResultSet rs;
	// 加载驱动
	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		Connection conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),
				MyProperties.getInstance());
		return conn;
	}

	/**
	 * 关闭所有资源
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 返回多条记录的查询操作 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public  List<Map<String, Object>> findMutipl(String sql,List<Object> params) 
			throws Exception{
		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map =null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn =getConn();
			pstmt =conn.prepareStatement(sql);
			setParamsList(pstmt, params);
			rs = pstmt.executeQuery();
			//获取所有列名
			List<String> columnNames =getColumnNames(rs);
			while(rs.next()){
				map = new HashMap<String,Object>();
				//循环列
				for(String name:columnNames){
					Object obj = rs.getObject(name);//获取值 
					if(null ==obj){
						continue;
					}
					//获取值得数据类型
					String typeName =obj.getClass().getName();
					if("oracle.sql.BLOB".equals(typeName)){
//						BLOB blob =(BLOB) rs.getBlob(name);
//						InputStream  in =blob.getBinaryStream();
//						byte []bt = new byte[(int)blob.length()];
//						in.read(bt);
//						//字节数组存入map中
//						map.put(name, bt);
//						in.close();
					}else{
						map.put(name, rs.getObject(name));
					}
				}
				//添加list集合
				list.add(map);
			}
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 返回单条记录   select * from table_name where id =? 
	 * @param sql
	 * @param params   参数   添加参数的顺序必须和?一致  
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> findSingle(String sql,List<Object> params) throws Exception{
		Map<String, Object> map =null;
		 ResultSet rs = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		try{
			conn =getConn();
			pstmt =conn.prepareStatement(sql);
			//设置参数 
			setParamsList(pstmt, params);
			//执行查询操作
			rs =pstmt.executeQuery();
			//获取所有列名
			List<String>  columnNames = getColumnNames(rs);
			if(rs.next()){
				//创建Map对象 
				map = new HashMap<String,Object>();
				//循环所有列
				for(String name:columnNames){
					//获取值
					Object obj = rs.getObject(name);
					//判断值是否为空 
					if(null ==obj){
						continue;
					}
					//获取值的类型名称 
					String typeName = obj.getClass().getName();
					//System.out.println(typeName);
					if("oracle.sql.BLOB".equals(typeName)){
//						//图片
//						BLOB blob =(BLOB) rs.getBlob(name);  //BLOB处理必须在连接未关闭之前进行  
//						InputStream  in =blob.getBinaryStream();
//						byte []bt = new byte[(int)blob.length()];
//						in.read(bt);
//						//字节数组存入map中
//						map.put(name, bt);
//						in.close();
					}else{
						map.put(name, rs.getObject(name));
					}
				}
				
			}
			
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return map;
	}
	
	//获取所有列
	public List<String> getColumnNames(ResultSet rs) throws SQLException{
		List<String>  columnNames =new ArrayList<String>();
		//获取ResultSetMetaData 
		ResultSetMetaData data =rs.getMetaData();//获取此 ResultSet 对象的列的编号、类型和属性。
		int count =data.getColumnCount();
		//获取结果集所有列名 
		for(int i =1;i<=count;i++){
			 //添加到list集合
			//columnNames.add(data.getColumnName(i));
			columnNames.add(data.getColumnLabel(i));
		}
		return columnNames;
	}
		
	//设置参数  list集合  
	public void setParamsList(PreparedStatement pstmt,List<Object> params) throws SQLException{
		if(null ==params  || params.isEmpty()){
			return ;
		}
		for(int i =0;i<params.size();i++){
			pstmt.setObject(i+1, params.get(i));
		}
	}
	
	/**
	 * 多条sql语句更新操作  批处理   
	 * @param sqls  多条sql语句
	 * @param params  多条sql语句对应的参数  
	 * @return
	 * @throws SQLException 
	 */
	public int  update(List<String> sqls,List<List<Object>> params) throws SQLException{
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn =getConn();// 事务自动提交 
			//事务设置为手动提交 
			conn.setAutoCommit(false);
			//循环sql语句 
			if(null ==sqls || sqls.isEmpty()){
				return result;
			}
			
			for(int i =0;i<sqls.size();i++){
				//获取sql语句 
				String sql =sqls.get(i);
				//获取预编译对象
				pstmt = conn.prepareStatement(sql);
				//设置参数 
				setParamsList(pstmt, params.get(i)); //获取第i个小list设置参数 
				result = pstmt.executeUpdate();
				if(result<=0){ //此sql语句未执行成功 
					conn.rollback(); //设置事务回滚 
					return result;
				}
			}
			//事务提交
			conn.commit();
		}catch(SQLException e){
			//当发生异常后，也需要对其进行回滚操作
			conn.rollback(); //设置事务回滚 
		}finally{
			//还原conn事务状态 
			conn.setAutoCommit(true);
			closeAll(conn, pstmt, null);
		}
		return result;
	}
	
	/**
	 * 单条更新语句 增删改操作
	 * @param sql sql语句
	 * @param params插入的参数 不定长数组   调用此方法时参数的顺序必须和?顺序一致  
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {
		int result = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = this.getConn();// 获取连接
			pstmt = conn.prepareStatement(sql);
			setParamsObject(pstmt, params);// 设置参数
			//执行更新操作
			result =pstmt.executeUpdate();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	//设置参数 
	private void setParamsObject(PreparedStatement pstmt, Object... params) throws SQLException {
		// 循环数组
		if (null == params || params.length <= 0) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}
	}
	
	/**
	 * 聚合函数 sum  count avg min max 
	 * select  sum(*) from tb_name  where ....
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public double   getPolymer(String sql,List<Object> params) throws SQLException{
		//System.out.println(sql);
		double result =0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn =getConn();
			pstmt =conn.prepareStatement(sql);
			//设置参数  
			setParamsList(pstmt, params);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
	
	
	/**
	 * 封装对象 返回单条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public <T> T find(String sql,List<Object> params,Class<T> cls) throws InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException {
		T t = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn  = getConn();
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			setParamsList(pstmt, params);
			
			//执行查询操作
			rs = pstmt.executeQuery();
			
			//获取所有的方法
			Method[] methods = cls.getDeclaredMethods();
			
			//获取结果集列名
			List<String> columnNames = getColumnNames(rs);
			
			if(rs.next()) {
				//创建对象
				t = cls.newInstance();	//User user  = new User();调用无参构造函数
				
				//循环method
				for(Method m:methods) {
					for(String name:columnNames) {
						if(("set" + name).equalsIgnoreCase(m.getName())) {
							//获取该方法的形参的类型
							String paramterType = m.getParameterTypes()[0].getName();
							//System.out.println("3:" + paramterType);
							
							if("int".equals(paramterType) || "java.lang.Integer".equals(paramterType)) {
								m.invoke(t, rs.getInt(name));
							}else if("double".equals(paramterType) || "java.lang.Double".equals(paramterType)) {
								m.invoke(t, rs.getDouble(name));
							}else if("float".equals(paramterType) || "java.lang.Float".equals(paramterType)) {
								m.invoke(t, rs.getFloat(name));
							}else if("java.lang.String".equals(paramterType)) {
								m.invoke(t, rs.getString(name));
							}//如果实体类中还有其他类型还可以补充
							
							break;	//跳出方法的循环
						}
					}
				}
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return t;
	}
	
	
	/**
	 * 返回多条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T> List<T> findMutipl( String sql,List<Object> params,Class<T> cls) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T t = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<T> list = new ArrayList<>();
		try {
			conn  = getConn();
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			setParamsList(pstmt, params);
			
			//执行查询操作
			rs = pstmt.executeQuery();
			
			//获取所有的方法
			Method[] methods = cls.getDeclaredMethods();
			
			//获取结果集列名
			List<String> columnNames = getColumnNames(rs);
			
			while(rs.next()){
				//创建对象
				t = cls.newInstance();
				
				//循环method
				for(Method m:methods) {
					for(String name:columnNames) {
						if(("set" + name).equalsIgnoreCase(m.getName())) {
							//获取该方法的形参的类型
							String paramterType = m.getParameterTypes()[0].getName();
							//System.out.println("3:" + paramterType);
							
							if("int".equals(paramterType) || "java.lang.Integer".equals(paramterType)) {
								m.invoke(t, rs.getInt(name));
							}else if("double".equals(paramterType) || "java.lang.Double".equals(paramterType)) {
								m.invoke(t, rs.getDouble(name));
							}else if("float".equals(paramterType) || "java.lang.Float".equals(paramterType)) {
								m.invoke(t, rs.getFloat(name));
							}else if("java.lang.String".equals(paramterType)) {
								m.invoke(t, rs.getString(name));
							}//如果实体类中还有其他类型还可以补充
							
							break;	//跳出方法的循环
						}
					}
				}
				//所有字段的值都添加到对象中后，将该对象添加List集合中
				list.add(t);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}	
}
