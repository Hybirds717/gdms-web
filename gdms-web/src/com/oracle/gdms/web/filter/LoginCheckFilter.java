package com.oracle.gdms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({ "/admin/goods/*", "/admin/main.jsp" })
public class LoginCheckFilter implements Filter {

	public void destroy() { }

	
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ��鵱ǰ�Ự���Ƿ����loginUser����
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Object o = session.getAttribute("loginUser");
		if ( o == null ) {
			String root = req.getContextPath(); // Ӧ�ó������Ŀվ���·��
			String url = root + "/index.jsp";
			String html = "<script>";
			html += "window.top.location.href='" + url + "'";	// �ͻ���������Ķ��㴰����ת��ַΪurl
			html += "</script>";
			response.getWriter().print(html);
		} else 
			chain.doFilter(request, response);
	}
	
	
	
	public void init(FilterConfig fConfig) throws ServletException { }

}
