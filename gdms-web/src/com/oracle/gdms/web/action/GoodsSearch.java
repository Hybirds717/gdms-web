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
		
		String kw = request.getParameter("keywords");	// 从请求中得到要搜索的关键词
		
		if ( kw != null && !kw.isEmpty() ) {
			
			GoodsService service = new GoodsServiceImpl();
			List<GoodsModel> list = service.findByKeywords(kw);
			
			String path = this.getServletContext().getRealPath("/WEB-INF");	// 取得WEB-INF的绝对路径
			File file = new File(path + "/doc");
			if ( !file.exists() ) {
				file.mkdir();			// 这个文件夹如果没有，就创建它
			}
			String filename = GDMSUtil.getCurrentDatetime() + ".xls";  // 目标新excel文档文件名
			file = new File(path + "/doc/" + filename);
			
			FileOutputStream fos = new FileOutputStream(file);		   // 文件输出流
			
			Workbook book = new HSSFWorkbook();				// 在内存中创建了一个工作薄
			
			Sheet sheet = book.createSheet();				// 在工作薄中创建一个工作表
			
			CellStyle style = book.createCellStyle();   	// 创建一个样式对象
			
//			style.setFillForegroundColor(HSSFColor.BLUE.index);
//			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = book.createFont();
			font.setFontName("黑体");
			font.setColor(HSSFColor.WHITE.index);
			font.setFontHeight((short) 200);
			style.setFont(font);
			
			style.setBorderBottom(CellStyle.BORDER_DOTTED); 	//底部边框
			style.setBottomBorderColor(HSSFColor.YELLOW.index);	//底部边框颜色
	 
			style.setBorderLeft(CellStyle.BORDER_DOTTED); 		//左边框
			style.setLeftBorderColor(HSSFColor.RED.index);		//左边框颜色

			
			int[] kuan = {3000, 5000, 4000, 3000, 3000, 2000, 2000, 3000, 4000, 3000, 5000, 4000, 3000, 3000, 2000, 2000};
			for ( int a = 0; a < kuan.length; a ++ ) {
				sheet.setColumnWidth(a, kuan[a]);			// 为每一列指定宽度
			}
			
			int r = 2, c = 0;								// 从第3行，第1列开始
			Row row = sheet.createRow(r ++);				// 创建一个行对象
			
			Cell cell = row.createCell(c ++);				// 创建一个单元格对象
			cell.setCellValue("商品编号");						// 指定单元格的内容
			cell.setCellStyle(style);
			cell = row.createCell(c ++);
			cell.setCellValue("商品名称");	
			cell = row.createCell(c ++);
			cell.setCellValue("规格型号");	
			cell = row.createCell(c ++);
			cell.setCellValue("价格");	
			cell = row.createCell(c ++);
			cell.setCellValue("库存数量");	
			cell = row.createCell(c ++);
			cell.setCellValue("折扣");	
			cell = row.createCell(c ++);
			cell.setCellValue("单位");	
			cell = row.createCell(c ++);
			cell.setCellValue("颜色");	
			cell = row.createCell(c ++);
			cell.setCellValue("尺寸");	
			cell = row.createCell(c ++);
			cell.setCellValue("重量");	
			cell = row.createCell(c ++);
			cell.setCellValue("产地");	
			cell = row.createCell(c ++);
			cell.setCellValue("生产日期");	
			cell = row.createCell(c ++);
			cell.setCellValue("有效期");	
			cell = row.createCell(c ++);
			cell.setCellValue("主图");	
			cell = row.createCell(c ++);
			cell.setCellValue("状态");	
			cell = row.createCell(c ++);
			cell.setCellValue("是否推送");	
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
				cell.setCellValue(g.isPush() ? "已推送" : "未推送");
			}
			
			book.write(fos);							// 把内存中工作薄的数据，写出到fos流中
			fos.close();
			
			try {
				download(response, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 提供下载文件的功能
	 * @param response
	 * @param targetFile
	 * @throws Exception
	 */
	private void download(HttpServletResponse response, File targetFile) throws Exception {
		response.reset();
		response.setContentType("APPLICATION/OCTET-STREAM");	// 设置浏览器响应结果时的类别
		//把文件名进行转码(防止中文乱码)
		String name = targetFile.getName();
		String targetFileName = new String(name.getBytes("GB2312"), "iso8859-1");
		String fileName = response.encodeURL(targetFileName); //下载对话框中显示出来的文件名
		
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
		targetFile.delete();// 下载结束时，删除源文件
	}

}












