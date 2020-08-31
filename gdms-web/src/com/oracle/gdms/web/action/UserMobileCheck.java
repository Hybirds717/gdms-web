package com.oracle.gdms.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.UserService;
import com.oracle.gdms.service.impl.UserServiceImpl;


@WebServlet("/admin/user/mobile.php")
public class UserMobileCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");	// 响应的结果类型
		ResponseEntity ent = new ResponseEntity();					// 准备一个向客户端返回的结果对象
		JSONObject json = new JSONObject();							// JSON对象
		PrintWriter out = response.getWriter();						// 向客户端输出的流对象
		
		
		String mobile = request.getParameter("mobile");	// 从请求中得到要检查的手机号
		if ( mobile == null ) {
			ent.setCode(1002);
			ent.setMessage("手机号码缺失。");
		} else {
//			UserService service = new UserServiceImpl();
			UserService service = (UserService) Factory.getInstance().getDao("User.service.impl");
			boolean has = service.hasMobile(mobile);
			
			ent.setCode( has ? 1003 : 0);
			ent.setMessage(has ? "<span style='color:red'>该手机号已被注册，请更改</span>" : "<span style='color:green'>恭喜，手机号码可用");
		}
		
		json.put("data", ent);
		out.print(json.toJSONString());// 把JSON结果输出到客户端
		
	}

}












