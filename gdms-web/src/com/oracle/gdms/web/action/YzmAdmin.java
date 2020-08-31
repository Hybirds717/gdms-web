package com.oracle.gdms.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.gdms.util.RandCreateImage;

@WebServlet("/admin/user/yzm/code.png")
public class YzmAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache_Control", "no-cache"); //把结果设置为不缓存
		response.setDateHeader("Hxpires", 0);
		HttpSession session = request.getSession();
		RandCreateImage rci = new RandCreateImage(response.getOutputStream());
		String code = rci.createRandImage();
//		System.out.println("code=" + code);
		session.setAttribute("code", code);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
