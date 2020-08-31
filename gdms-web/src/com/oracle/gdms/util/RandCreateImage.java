package com.oracle.gdms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

//����ķ������������һ��ͼ�����֤��
public class RandCreateImage {
//    private static final String CODE_LIST = "AB��CDEF����G�з�HIJ����KLMNO�ɻ�PQRSTUVWX��ҹYZabcdefg��hij����klmn��opqrstu��vwxyz1234����56789��0";
    private static final String CODE_LIST = "AAAAAAAAA";   
    private OutputStream response = null;
    private static final int HEIGHT = 35;//ͼ���
    private static final int FONT_NUM = 4;//��֤�ַ�����
    private int width = 0;
    private int iNum = 0;
    private String codeList = "";//�ַ��б�
    private boolean drawBgFlag = false;//��������

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
    
    //�˷������ڲ���һ����֤�벢��response��������л���һ��ͼ��
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
        //���������ֺ�
        g.setFont(new Font("����", Font.BOLD, 26));
        
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
        	//��response������������дͼ��
            ImageIO.write(image, "JPEG", response );
        }catch(IOException e){
//            e.printStackTrace();//�����쳣���¼��־��
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


