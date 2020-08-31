package com.oracle.gdms.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.service.impl.GoodsServiceImpl;
import com.oracle.gdms.util.GDMSUtil;


@WebServlet("/admin/goods/search.php")
public class GoodsSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kw = request.getParameter("keywords");	// �������еõ�Ҫ�����Ĺؼ���
		
		if ( kw != null && !kw.isEmpty() ) {
			
			GoodsService service = new GoodsServiceImpl();
			List<GoodsModel> list = service.findByKeywords(kw);
			
			String path = this.getServletContext().getRealPath("/WEB-INF");	// ȡ��WEB-INF�ľ���·��
			File file = new File(path + "/doc");
			if ( !file.exists() ) {
				file.mkdir();			// ����ļ������û�У��ʹ�����
			}
			String filename = GDMSUtil.getCurrentDatetime() + ".xls";  // Ŀ����excel�ĵ��ļ���
			file = new File(path + "/doc/" + filename);
			
			FileOutputStream fos = new FileOutputStream(file);		   // �ļ������
			
			Workbook book = new HSSFWorkbook();				// ���ڴ��д�����һ��������
			
			Sheet sheet = book.createSheet();				// �ڹ������д���һ��������
			
			CellStyle style = book.createCellStyle();   	// ����һ����ʽ����
			
//			style.setFillForegroundColor(HSSFColor.BLUE.index);
//			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = book.createFont();
			font.setFontName("����");
			font.setColor(HSSFColor.WHITE.index);
			font.setFontHeight((short) 200);
			style.setFont(font);
			
			style.setBorderBottom(CellStyle.BORDER_DOTTED); 	//�ײ��߿�
			style.setBottomBorderColor(HSSFColor.YELLOW.index);	//�ײ��߿���ɫ
	 
			style.setBorderLeft(CellStyle.BORDER_DOTTED); 		//��߿�
			style.setLeftBorderColor(HSSFColor.RED.index);		//��߿���ɫ

			
			int[] kuan = {3000, 5000, 4000, 3000, 3000, 2000, 2000, 3000, 4000, 3000, 5000, 4000, 3000, 3000, 2000, 2000};
			for ( int a = 0; a < kuan.length; a ++ ) {
				sheet.setColumnWidth(a, kuan[a]);			// Ϊÿһ��ָ�����
			}
			
			int r = 2, c = 0;								// �ӵ�3�У���1�п�ʼ
			Row row = sheet.createRow(r ++);				// ����һ���ж���
			
			Cell cell = row.createCell(c ++);				// ����һ����Ԫ�����
			cell.setCellValue("��Ʒ���");						// ָ����Ԫ�������
			cell.setCellStyle(style);
			cell = row.createCell(c ++);
			cell.setCellValue("��Ʒ����");	
			cell = row.createCell(c ++);
			cell.setCellValue("����ͺ�");	
			cell = row.createCell(c ++);
			cell.setCellValue("�۸�");	
			cell = row.createCell(c ++);
			cell.setCellValue("�������");	
			cell = row.createCell(c ++);
			cell.setCellValue("�ۿ�");	
			cell = row.createCell(c ++);
			cell.setCellValue("��λ");	
			cell = row.createCell(c ++);
			cell.setCellValue("��ɫ");	
			cell = row.createCell(c ++);
			cell.setCellValue("�ߴ�");	
			cell = row.createCell(c ++);
			cell.setCellValue("����");	
			cell = row.createCell(c ++);
			cell.setCellValue("����");	
			cell = row.createCell(c ++);
			cell.setCellValue("��������");	
			cell = row.createCell(c ++);
			cell.setCellValue("��Ч��");	
			cell = row.createCell(c ++);
			cell.setCellValue("��ͼ");	
			cell = row.createCell(c ++);
			cell.setCellValue("״̬");	
			cell = row.createCell(c ++);
			cell.setCellValue("�Ƿ�����");	
			for ( GoodsModel g : list ) {
				row = sheet.createRow(r ++);
				c = 0;
				cell = row.createCell(c ++);
				cell.setCellValue(g.getRowflag());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getName());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getSpec());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getPrice());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getAmount());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getAgio());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getUnit());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getColor());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getSize());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getWeight());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getArea());
				cell = row.createCell(c ++);
				int i = g.getMakedate().toLocaleString().indexOf(" ");
				cell.setCellValue(g.getMakedate().toLocaleString().substring(0, i));
				cell = row.createCell(c ++);
				cell.setCellValue(g.getExpirydate());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getPhoto());
				cell = row.createCell(c ++);
				cell.setCellValue(g.getStatus());
				cell = row.createCell(c ++);
				cell.setCellValue(g.isPush() ? "������" : "δ����");
			}
			
			book.write(fos);							// ���ڴ��й����������ݣ�д����fos����
			fos.close();
			
			try {
				download(response, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * �ṩ�����ļ��Ĺ���
	 * @param response
	 * @param targetFile
	 * @throws Exception
	 */
	private void download(HttpServletResponse response, File targetFile) throws Exception {
		response.reset();
		response.setContentType("APPLICATION/OCTET-STREAM");	// �����������Ӧ���ʱ�����
		//���ļ�������ת��(��ֹ��������)
		String name = targetFile.getName();
		String targetFileName = new String(name.getBytes("GB2312"), "iso8859-1");
		String fileName = response.encodeURL(targetFileName); //���ضԻ�������ʾ�������ļ���
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		ServletOutputStream out = response.getOutputStream();
		InputStream inStream = new FileInputStream(targetFile);
		
		byte[] b = new byte[8192]; 
		int len; 
		while((len = inStream.read(b)) >0){
			out.write(b,0,len);
		}
		response.setStatus( HttpServletResponse.SC_OK ); 
		response.flushBuffer();
		out.flush();
		out.close();
		inStream.close();
		targetFile.delete();// ���ؽ���ʱ��ɾ��Դ�ļ�
	}

}












