package com.pxy.l42;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class NewCode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置属性集
		Properties pros = new Properties();
		pros.put("kaptcha.border", "no");
		pros.put("kaptcha.textproducer.font.color", "red");
		pros.put("kaptcha.image.width", "80");
		pros.put("kaptcha.image.height", "30");
		pros.put("kaptcha.textproducer.char.string", "0123456789");
		pros.put("kaptcha.textproducer.char.length", "4");
		pros.put("kaptcha.textproducer.char.space", "5");
		pros.put("kaptcha.textproducer.font.size", "24");
		pros.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		pros.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
		Config config = new Config(pros);
		//创建kaptcha对象
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		kaptcha.setConfig(config);
		//生成验证码并保存到Session
		String code = kaptcha.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
		//生成图片
		BufferedImage img = kaptcha.createImage(code);
		//返回图片
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(img, "png", sos);
		sos.flush();
		sos.close();
	}

}
