package com.oracle.gdms.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.web.action.Factory;
import com.sun.research.ws.wadl.Response;

@Path("/huazhao")
public class TestRest {
	@Path("/hello")
	@GET
	public void hello() {
		System.out.println("hello world");
	}
	
	
	
	//需求，设计一个方法，调用它，就可以将某条商品推送出去
	//@PathParam    测试时地址栏输入:/push/one/4
	//@QueryParam   测试时地址栏输入:/push/one?goodsid=4
	//@FormParam
	@Path("/push/one/{goodsid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushOne( @PathParam("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
	
	
	
	
	
	
	
	
	@Path("/push/two")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushTwo( @QueryParam("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
	
	
	
	
	
	
	
	
	
	@Path("/push/three")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushThree( @FormParam("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
	
	
	
	
	
	
	
	
	@Path("/push/four")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) //传入的参数是JSON格式
	public GoodsModel pushFour(GoodsEntity g) { //GoodsEntity是形参，必须满足两个条件，有xmlrootElement注解,实现序列化接口
		System.out.println("参数接收：");
		System.out.println(g.getGoodsid());
		System.out.println(g.getName());  //没有得到数据，因为GoodsEntity.java里没有定义sex
		
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		GoodsModel goods = service.findOne(g.getGoodsid());
		return goods;
	}
	
	
	
	
	
	
	@Path("/push/five")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) //传入的参数是JSON格式
	public GoodsModel pushFive(String param) { 
//		public GoodsModel pushFive(Map<String,String> map)
//		System.out.println("参数接收：");
//		System.out.println(map.get("goodsid"));
//		System.out.println(map.get("name")); 
//		System.out.println(map.get("sex")); 
//		
//		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
//		GoodsModel goods = service.findOne(Integer.parseInt(map.get("goodsid")));
//		return goods;
		
		System.out.println("参数接收：");
		System.out.println(param);
		
		JSONObject j = JSONObject.parseObject(param);
		System.out.println("id="+j.getString("goodsid"));
		System.out.println("name="+j.getString("name"));
		System.out.println("sex="+j.getString("sex"));
		
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		GoodsModel goods = service.findOne(Integer.parseInt(j.getString("goodsid")));
		return goods;
		
	}
	
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/push/goods/bytype")
	public List<GoodsModel> pushGoodsByType(GoodsType type){
		List<GoodsModel> list = null;
		
		GoodsService service = (GoodsService) Factory.getInstance().getDao("goods.service.impl");
		list = service.findByType(type.getGtid());
		System.out.println("size=" + list.size());
		
		return list;
	}
	
	
	
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/push/goods/one")
	public ResponseEntity pushGoods(String jsonstr){
		JSONObject j = JSONObject.parseObject(jsonstr);
		GoodsEntity goods = JSONObject.parseObject(j.getString("goods"), GoodsEntity.class);
		System.out.println("goodsid="+goods.getGoodsid());
		System.out.println("name="+goods.getName());
		System.out.println("area="+goods.getArea());
		ResponseEntity rs = new ResponseEntity();
		rs.setCode(0);
		rs.setMessage("推送成功");
		rs.setData(null);
		return rs;
	}

}
