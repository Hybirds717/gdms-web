package com.oracle.gdms.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.service.impl.GoodsServiceImpl;


@WebServlet("/admin/goods/list.php")
public class GoodsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GoodsService service = new GoodsServiceImpl();
		
		String pageNumber = request.getParameter("pn");	// �������еõ�ҳ�����
		String kw = request.getParameter("keywords");	// �������еõ�Ҫ�����Ĺؼ���
		
		if ( pageNumber == null ) {
			pageNumber = "1";
		}
		int p = Integer.parseInt(pageNumber);		// ���ַ���תΪ����
		
		PageModel<GoodsModel> data = null;
		if ( kw != null && !kw.trim().isEmpty() ) {		// �йؼ���������ʱ
			request.setAttribute("keywords", kw);
			data = service.findByKeywords(kw, p, PageModel.ROWS);
		} else {
			data = service.findByPage(p, PageModel.ROWS);
		}
		// �ѽ���͸�ǰ��չʾ
		request.setAttribute("model", data);
		request.getRequestDispatcher("../jsp/goods/list.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}












