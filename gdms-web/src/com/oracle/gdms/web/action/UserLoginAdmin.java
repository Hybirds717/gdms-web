package com.oracle.gdms.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.gdms.entity.UserModel;
import com.oracle.gdms.service.UserService;
import com.oracle.gdms.service.impl.UserServiceImpl;
import com.oracle.gdms.util.GDMSUtil;

@WebServlet("/admin/user/login.php")
public class UserLoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �����ύ�����Ĳ����������Ժ��װ��һ��UserModelʵ��
		String account = request.getParameter("name");
		String password = request.getParameter("password");
		String code = request.getParameter("code");	
		
		// �õ���������session����ȷ����֤��
		HttpSession session = request.getSession();				// �Ȼ�ȡ��ǰ�Ự
		String yzm = (String) session.getAttribute("code");		// �ӻỰ��ȡ����ȷ����֤��
		// �ù�����֤����Ч������
		session.removeAttribute("code");
		
		if ( yzm == null || !yzm.equalsIgnoreCase(code) ) {
			request.setAttribute("login_msg", "��֤����Ч");
			// ִ��ת��
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
			return;
		}
		
//		System.out.println("account=" + account);
//		System.out.println("password=" + password);
		
		UserModel user = new UserModel();
				
		user.setAccount(account);
		try {
			user.setLogpwd(GDMSUtil.getMD5(password.getBytes()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println("name=" + user.getAccount());
		System.out.println("pawd=" + user.getLogpwd());
		
		// ����ҵ���߼���Ķ���
//		UserService userservice = new UserServiceImpl();
		UserService userservice = (UserService) Factory.getInstance().getDao("User.service.impl");
		
		UserModel loginUser = userservice.login(user);
		if ( loginUser != null ) {
			session.setAttribute("loginUser", loginUser);	// ��¼�ɹ��Ͱ��û��������Ự��
			response.sendRedirect("../main.jsp");  			// �ض�����ҳ��admin/main.jsp
		} else {
			request.setAttribute("login_msg", "�û�����������Ч");// ִ��ת��
			request.getRequestDispatcher("../../index.jsp").forward(request, response);
		}
	}
}





