package com.oracle.gdms.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.dao.GoodsDao;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.PageModel;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.GDMSUtil;
import com.oracle.gdms.web.listener.AppListener;

public class GoodsServiceImpl extends BaseService implements GoodsService {

	@Override
	public PageModel<GoodsModel> findByPage(int pageNumber, int rows) {
		GDMSUtil.log("��Ʒ��ҳ��ѯ��ʾ���ÿ�ʼ����");
		PageModel<GoodsModel> obj = new PageModel<GoodsModel>();
		obj.setCurrent(pageNumber); // ��ǰҳ
		
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			
			int count = dao.findCount(null);	// ��ѯ�ܼ�¼����
			int total = count % rows == 0 ? count / rows : count / rows + 1;	// ������ҳ��
			obj.setTotal(total); // ��ҳ��
			
			int offset = (pageNumber - 1) * rows;
			
			Map<String, Object> map = new HashMap<>();
			map.put("offset", offset);
			map.put("rows", rows);
			obj.setData( dao.findByPage(map) );  // ���ݼ�Ҳ�����
			GDMSUtil.log("��Ʒ��ҳ��ѯ��ʾ���ý���");
		} catch (Exception e) {
			e.printStackTrace();
			GDMSUtil.log("��Ʒ��ҳ��ѯ��ʾ����ʱ�����쳣��" + e.toString());
		} finally {
			free();
		}
		 
		return obj;
	}

	@Override
	public String pushGoods(int goodsid) {
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			GoodsModel goods = dao.findById(goodsid);
			JSONObject json = new JSONObject();
			json.put("goods", goods);
			String jsonstr = json.toJSONString();
			
			ResponseEntity result = push(jsonstr); // ִ������,������浽����result��
			if ( result != null && result.getCode() == 0 ) {
				dao.updatePush(goodsid);	// ������ͳɹ����͸������ݱ��ֶ�pushΪ������
				session.commit();
			}
			return result.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return "����ʧ��";
	}

	private ResponseEntity push(String jsonstr) {
//		String url = AppListener.getString("pushurl_self");
		String url = "http://localhost:8080/gdms-web/rest/huazhao/push/goods/one";
		
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonstr, "UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		HttpClient client = new DefaultHttpClient();
		
		try {
			HttpResponse resp = client.execute(post);
			HttpEntity resent = resp.getEntity();
			
			String str = EntityUtils.toString(resent,"UTF-8");
			
			ResponseEntity re = JSONObject.parseObject(str, ResponseEntity.class);
			return re;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int add(GoodsEntity goods) {
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			int c = dao.add(goods);
			session.commit();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return 0;
	}

	@Override
	public PageModel<GoodsModel> findByKeywords(String kw, int pageNumber, int rows) {
		PageModel<GoodsModel> obj = new PageModel<GoodsModel>();
		obj.setCurrent(pageNumber); // ��ǰҳ
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			
			int offset = (pageNumber - 1) * rows;
			Map<String, Object> map = new HashMap<>();
			map.put("offset", offset);
			map.put("rows", rows);
			map.put("kw", kw);
			int count = dao.findCount(map);	// ��ѯ�ܼ�¼����
			int total = count % rows == 0 ? count / rows : count / rows + 1;	// ������ҳ��
			obj.setTotal(total); // ��ҳ��
			
			obj.setData( dao.findByPage(map) );  // ���ݼ�Ҳ�����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		 
		return obj;
	}

	@Override
	public List<GoodsModel> findByKeywords(String kw) {
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			return dao.findByKeywords(kw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return null;
	}

	@Override
	public void delete(String[] gid) {
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			
			Map<String, Object> m = new HashMap<>();
			m.put("gid", gid);
			dao.updateStatus(m);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
	}

	@Override
	public int update(GoodsEntity goods) {
		// TODO �޸���Ʒ
		try {
			session = GDMSUtil.getSession();  //��ûỰ���õ�����
			GoodsDao dao = session.getMapper(GoodsDao.class);//����
			dao.update(goods);//ִ�и���
			session.commit(); //�ύ�����ݿ��̨
			return 1;//û���쳣ʱ������1����Ӱ�������return 0
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  //����Ҫ��һ���£�д��finally��
			free(); //�ͷ�����
		}
		return 0;
	}

	@Override
	public GoodsModel findOne(int goodsid) {
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			GoodsModel goods = dao.findById(goodsid);
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return null;
	}

	@Override
	public List<GoodsModel> findByType(int gtid) {
		// TODO �������ID��ѯ������Ʒ
		List<GoodsModel> list = null;
		try {
			session = GDMSUtil.getSession();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			list = dao.findByType(gtid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return list;
	}


	
//	public static void main(String[] args) {
//		GoodsService ser = new GoodsServiceImpl();
//		PageModel<GoodsModel> p = ser.findByPage(1, 2);
//		System.out.println("��ҳ��=" + p.getTotal());
//		for ( GoodsModel m : p.getData() ) {
//			System.out.print("goodsid=" + m.getGoodsid() + "  name=" + m.getName() );
//			System.out.println("  ���=" + m.getType().getName());
//		}
//	}

}
