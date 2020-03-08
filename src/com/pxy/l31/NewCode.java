package com.pxy.l31;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 算数验证码
 * 
 * @author 十方上下
 * 
 */
public class NewCode extends HttpServlet {

	private Random random = new Random(); // 随机数对象
	private int width = 140; // 宽度
	private int height = 30; // 高度
	private int fontsize = 18; // 字体大小
	private String str = "+-*/";
	private int value = -1; // 保存计算结果

	private String randCode() {
		int one = random.nextInt(100);
		int two = random.nextInt(100)+1;
		char op = str.charAt(random.nextInt(str.length()));
		switch (op) {
		case '+':
			value = one+two;
			break;
		case '-':
			value = one-two;
			break;
		case '*':
			value = one*two;
			break;
		case '/':
			value = one/two;
			break;
		}
		return ""+one+op+two+"=?";
	}
	//返回随机颜色
	private Color randColor(){
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r,g,b);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.创建画板
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//2.创建画笔
		Graphics2D pen = img.createGraphics();
		//3.生成随机内容
		String code = randCode();
		request.getSession().setAttribute("valiCode", value);
		//4.绘制内容
		//  4.1设置绘制区域
		pen.fillRect(0, 0, width, height);
		//  4.2设置字体
		pen.setFont(new Font("微软雅黑", Font.BOLD, fontsize+random.nextInt(5)));
		//  4.3按顺序逐个绘制字符
		for (int i = 0; i < code.length(); i++) {
			pen.setColor(randColor());
			//绘制字符
			pen.drawString(code.charAt(i)+"", 5+i*fontsize, (fontsize+height)/2+random.nextInt(5));
		}
		//  4.4绘制噪音线
		for(int i=0; i<2; i++){
			pen.setColor(randColor());
			pen.setStroke(new BasicStroke(3));
			pen.drawLine(random.nextInt(width/2), random.nextInt(height),
					random.nextInt(width), random.nextInt(height));
		}
		
		//5.存为图片并发送
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(img, "png", out);
		out.flush();
		out.close();
	}

}
