package com.oracle.gdms.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.AreaModel;
import com.oracle.gdms.service.AreaService;
import com.oracle.gdms.service.impl.AreaServiceImpl;

/**
 * Servlet implementation class ProvList
 */
@WebServlet("/admin/user/logout.php")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ǿ�����ٵ�ǰ�û���session
		request.getSession().invalidate();
		
		response.sendRedirect("../../index.jsp");  // �ض���index.jsp
	}

}
