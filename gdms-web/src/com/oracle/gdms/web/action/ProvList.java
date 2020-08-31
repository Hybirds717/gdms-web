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
@WebServlet("/admin/area/provlist")
public class ProvList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 接收parentid
		String pid = request.getParameter("parentid");
		int id = Integer.parseInt(pid);
		
		// 向客户端返回一个JSON对象
		response.setContentType("application/json;charset=UTF-8");
		
//		AreaService areaservice = new AreaServiceImpl();
		AreaService areaservice = (AreaService) Factory.getInstance().getDao("prov.service.impl");
		
		List<AreaModel> list = areaservice.findAllProv(id);	// 查询出了所有省份列表
		
		JSONObject j = new JSONObject();
		j.put("data", list);
		
		response.getWriter().print(j.toJSONString());	// 把JSON数据输出到客户端
	}

}
