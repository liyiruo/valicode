package com.pxy.l42;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValiCode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String valiCode = request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString().toLowerCase();
		String inCode = request.getParameter("inCode").toLowerCase();
		if(inCode.equals(valiCode)){
			response.sendRedirect("index.jsp");
		}
		else{
			request.getSession().setAttribute("err", "验证码输入错误！");
			String url = request.getHeader("Referer");
			response.sendRedirect(url);
		}
	}

}
