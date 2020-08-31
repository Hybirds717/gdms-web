package com.oracle.gdms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

//此类的方法可随机生成一个图像和验证码
public class RandCreateImage {
//    private static final String CODE_LIST = "AB惑CDEF祖镇G中风HIJ杨翔KLMNO荷晃PQRSTUVWX作夜YZabcdefg还hij国延klmn久opqrstu爱vwxyz1234雨星56789烈0";
    private static final String CODE_LIST = "AAAAAAAAA";   
    private OutputStream response = null;
    private static final int HEIGHT = 35;//图像高
    private static final int FONT_NUM = 4;//验证字符数量
    private int width = 0;
    private int iNum = 0;
    private String codeList = "";//字符列表
    private boolean drawBgFlag = false;//画背景否

    private int rBg = 0;
    private int gBg = 0;
    private int bBg = 0;
    
    public RandCreateImage(OutputStream response) {
        this.response = response;
        this.width = 13 * FONT_NUM + 42;
        this.iNum = FONT_NUM;
        this.codeList = CODE_LIST;
    }
    
    public RandCreateImage(OutputStream response,int iNum,String codeList) {
        this.response = response;
        this.width = 13 * iNum + 22;
        this.iNum = iNum;
        this.codeList = codeList;        
    } 
    
    //此方法用于产生一个验证码并向response的输出流中画出一个图像
    public String createRandImage(){
        BufferedImage image = new BufferedImage(width, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        
        Graphics g = image.getGraphics();
        Random random = new Random();        
        if ( drawBgFlag ){
            g.setColor(new Color(rBg,gBg,bBg));
            g.fillRect(0, 0, width, HEIGHT);
        }else{
            g.setColor(getRandColor(200, 250)); 
            g.fillRect(0, 0, width, HEIGHT);            
            for (int i = 0; i < 355; i++) {
                g.setColor(getRandColor(140, 200));
                int x = random.nextInt(width);
                int y = random.nextInt(HEIGHT);
                int xl = random.nextInt(22);
                int yl = random.nextInt(22);
                g.drawLine(x, y, x + xl, y + yl); 
            }
        }
        //设置字体字号
        g.setFont(new Font("宋体", Font.BOLD, 26));
        
        String sRand="";
        for (int i=0;i<iNum;i++){
            int rand=random.nextInt(codeList.length());
            String strRand=codeList.substring(rand,rand+1);
            sRand+=strRand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(strRand,13*i+13,28); 
        }
        g.dispose();
        try{
        	//向response对象的输出流中写图像
            ImageIO.write(image, "JPEG", response );
        }catch(IOException e){
//            e.printStackTrace();//处理异常或记录日志处
        }
        return sRand;
    }
    
    public void setBgColor(int r,int g,int b){
        drawBgFlag = true;
        this.rBg = r;
        this.gBg = g;
        this.bBg = b;
    }
    private Color getRandColor(int fc, int bc) { 
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}


