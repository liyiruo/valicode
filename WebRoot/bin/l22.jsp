<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>2.1随机验证码</title>
		<style type="text/css">
			.code_a{
				color: #0000ff;
				font-size: 12px;
				text-decoration: none;
				cursor: pointer;
			}
			#cvs{
				cursor: pointer;
			}
		</style>
		<script src="js/l22.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var valicode;
		function changeCode(){
			var cvs = document.getElementById("cvs");
			valicode = drawcode(cvs);
		}
		function valiCode(){
			var code = document.getElementById("inCode").value;
			if(code.toLowerCase() == valicode.toLowerCase()){
				return true;
			}
			else{
				document.getElementById("err").innerHTML = "输入的验证码错误！";
				changeCode();
				return false;
			}
		}
		window.onload = changeCode;
	</script>
  </head>
  
  <body>
  	<form action="index.jsp" method="post">
  		<label>验证码：</label>
  		<input type="text" id="inCode" name="inCode" />
  		<canvas id="cvs" onclick="changeCode()"></canvas>
  		<a class="code_a" onclick="changeCode()">换一张</a><br />
  		<input type="submit" value="登录" onclick="return valiCode()" />
  	</form>
  	<div style="color:red" id="err"></div>
  </body>
</html>
