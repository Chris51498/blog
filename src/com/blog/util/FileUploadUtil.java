package com.blog.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传的工具类
 * @author xj
 *
 */
public class FileUploadUtil {
	
	private static final String IMAGEPATH="../fresh_images/";
	private static final String CHARSET="UTF-8";
	
	/**
	 * 文件上传
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T> T parseRequest(HttpServletRequest request,Class<T> cls) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//解析请求对象
		List<FileItem> items = upload.parseRequest(request);
		
		//创建T对象
		T t = cls.newInstance();
		Method[] methods = cls.getDeclaredMethods();
		
		//循环文件项
		for(FileItem item:items) {
			if (item.isFormField()) {	//判断是否为普通表单类型
				
				//获取表单元素的name值
				String name= item.getFieldName();
				//获取表单元素的value值
				String value= item.getString(CHARSET);
				
				for(Method m:methods) {
					if (("set"+name).equalsIgnoreCase(m.getName())) {
						String typeName = m.getParameterTypes()[0].getName();	//获取set方法的形参的数据类型
						if("java.lang.Integer".equals(typeName)) {
							m.invoke(t, Integer.parseInt(value));
						}else if("java.lang.Double".equals(typeName)) {
							m.invoke(t, Double.parseDouble(value));
						}else if("java.lang.Float".equals(typeName)) {
							m.invoke(t, Float.parseFloat(value));
						}else if("java.lang.String".equals(typeName)) {
							m.invoke(t, value);
						}
						break;
					}
				}
			}else {
				String fieldName = item.getFieldName();	//获取表单name属性值
				//获取文件名称
				String name = item.getName();
				//文件存储服务器的哪个位置
				String path = request.getServletContext().getRealPath("/");
				//文件重名问题
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString()+""+name;
				//创建文件对象
				File file = new File(path,IMAGEPATH+fileName);	//(String parent,String child)
				//将文件对象写入磁盘中
				item.write(file);
				//获取存储后的文件路径
				String image_path = IMAGEPATH+fileName;
				for(Method m:methods) {
					if (("set"+fieldName).equalsIgnoreCase(m.getName())) {
						m.invoke(t, image_path);
					}
				}
			}
		}
		return t;
	}
}
