package com.blog.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.bean.FileBean;
import com.blog.util.FileUploadUtil;
import com.google.gson.Gson;
@WebServlet("/fileUpload.action")
public class FileUploadServlet extends BaseServlet{
	/**
	 * 文章内照片的上传
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			FileBean bean=FileUploadUtil.parseRequest(request, FileBean.class);
			bean.setSuccess(1);
			bean.setUrl(bean.getUpload());
			//toPrintJson(response, bean);
			Gson gson = new Gson();
			
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("success", 1);
			map.put("message", "上传成功！");
			map.put("url", FileUploadUtil.image_path);
			PrintWriter out = response.getWriter();
			String str = gson.toJson(map);
			out.print(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
