package com.pxy.l7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SMSCode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 // 接口类型：互亿无线触发短信接口，支持发送验证码短信、订单通知短信等。
		 // 账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
		 // 注意事项：
		 //（1）调试期间，请使用用系统默认的短信内容：您的验证码是：【变量】。请不要把验证码泄露给其他人。；
		 //（2）请使用APIID（查看APIID请登录用户中心->验证码短信->产品总览->APIID）及 APIkey来调用接口；
		 //（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；

		String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

		int mobile_code = (int)((Math.random()*9+1)*100000); //获取随机数
		request.getSession().setAttribute("valiCode", mobile_code);
		
		String account = UserInfo.account; //查看用户名是登录用户中心->验证码短信->产品总览->APIID
		String password = UserInfo.pwd;  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
		String mobile = request.getParameter("mobile");
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		try {

			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);//允许连接提交信息
			connection.setRequestMethod("POST");//网页提交方式“GET”、“POST”
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("account="+account);
			sb.append("&password="+password);
			sb.append("&mobile="+mobile);
			sb.append("&content="+content);
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

	}

}
