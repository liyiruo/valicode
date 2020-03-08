<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>5.1jQuery滑块验证码</title>
    <link rel="stylesheet" type="text/css" href="css/verify.css"/>
    <script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/verify.js" type="text/javascript" charset="utf-8"></script>
		<style type="text/css">
			#btnOK{
				border: none;
				color: white;
				padding: 5px 10px;
				text-align: center;
				display: inline-block;
				font-size: 16px;
			}
		</style>
  </head>
  
  <body>
    <form method="post" action="index.jsp">
    	<div>
    		<label>验证：</label>
    		<div id="slider"></div>
    	</div>
    	<div>
    		<input type="submit" value="登录" id="btnOK" disabled="disabled" />
    	</div>
    	<script type="text/javascript">
    			$('#slider').slideVerify({
						type : 1,		//类型
						vOffset : 5,	//误差量，根据需求自行调整
						barSize : {
							width : '300px',
							height : '40px',
						},
						ready : function() {
						},
						success : function() {
							$("#btnOK").prop("disabled", "");
							$("#btnOK").css("background-color", "#008cba");
						},
						error : function() {
				//		        	alert('验证失败！');
						}
						
					});

    	</script>
    </form>
  </body>
</html>
