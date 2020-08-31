package com.oracle.gdms.web.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.service.GoodsService;

/**
 * Servlet implementation class GoodsUpdate
 */
@WebServlet("/admin/goods/update.php")
public class GoodsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接受ajax传来的参数
		request.setCharacterEncoding("UTF-8");
		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		String spec = request.getParameter("spec");
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");
		
		//把数据封装为一个Goods对象，传给业务层
		GoodsEntity goods = new GoodsEntity();
		goods.setGoodsid(Integer.parseInt(goodsid));  //转换为整型
		goods.setName(name);
		goods.setSpec(spec);
		
		int i = price.indexOf("￥");
		price = price.substring(i+1);
		goods.setPrice(Float.parseFloat(price));      //转换为整型
		goods.setAmount(Float.parseFloat(amount));    //转换为整型
		
		
		
//		System.out.println(goodsid);
//		System.out.println(name);
//		System.out.println(spec);
//		System.out.println(price);
//		System.out.println(amount);
		
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		int count = service.update(goods); //返回更新的结果（受影响的行数）
		response.setContentType("application/json;charset=utf-8");
		JSONObject j = new JSONObject();
		if ( count > 0 ) {
			j.put("code", 0);
			j.put("msg", "修改成功");
		}else {
			j.put("code", 10882);
			j.put("msg", "商品修改失败");
		}
		response.getWriter().print(j.toJSONString());
	}
}
