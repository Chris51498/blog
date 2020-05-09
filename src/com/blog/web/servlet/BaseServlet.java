package com.blog.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	/**
	 * 设置编码集
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void setCharacterEncoding(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	
	
	
	/**
	 * 将数据转为Json格式字符串
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void toPrintJson(HttpServletResponse response,Object obj) throws IOException {
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		PrintWriter out = response.getWriter();
		out.print(str);
	}
	
	
	
	
	/**
	 * 解析请求对象
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws Exception {
		T t = null;
		
		//反射实例获取类的所有属性
		Field[] fields = cls.getDeclaredFields();
		
		//获取所有方法
		Method[] methods = cls.getDeclaredMethods();
		
		//创建对象
		t = cls.newInstance();
		
		//循环所有的属性
		for(Field f:fields) {
			String fname = f.getName();
			if("serialVersionUID".equalsIgnoreCase(fname)) {
				continue;
			}
			//根据字段名获取页面传参数
			String value = request.getParameter(fname);
			if(null == value || "".equals(value)) {
				continue;
			}
			
			//调用set方法赋值
			for(Method m:methods) {
				if(("set" + fname).equalsIgnoreCase(m.getName())) {
					//获取该方法的形参的类型
					String typeName = m.getParameterTypes()[0].getName();
					if("java.lang.Integer".equals(typeName)) {
						m.invoke(t, Integer.parseInt(value));
					}else if("java.lang.Double".equals(typeName)) {
						m.invoke(t, Double.parseDouble(value));
					}else if("java.lang.Float".equals(typeName)) {
						m.invoke(t, Float.parseFloat(value));
					}else if("java.lang.String".equals(typeName)) {
						m.invoke(t, value);
					}//如果实体类中还有其他类型还可以补充
					
					break;	//跳出方法的循环
				}
			}
		}
		
		return t;
	}
}
