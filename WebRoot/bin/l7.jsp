<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>demo</title>
</head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script language="javascript">
	function get_mobile_code(){
        $.post('sms', {mobile:jQuery.trim($('#mobile').val())}, function(msg) {
//          alert(jQuery.trim(unescape(msg)));
			if(msg.indexOf('提交成功')>=0){
				RemainTime();
				$("#err").text("");
			}else{
//				location.reload();
				$("#err").text("验证码错误！");
			}
        });
	};
	var iTime = 59;
	var Account;
	function RemainTime(){
		document.getElementById('zphone').disabled = true;
		var iSecond,sSecond="",sTime="";
		if (iTime >= 0){
			iSecond = parseInt(iTime%60);
			iMinute = parseInt(iTime/60)
			if (iSecond >= 0){
				if(iMinute>0){
					sSecond = iMinute + "分" + iSecond + "秒";
				}else{
					sSecond = iSecond + "秒";
				}
			}
			sTime=sSecond;
			if(iTime==0){
				clearTimeout(Account);
				sTime='获取手机验证码';
				iTime = 59;
				document.getElementById('zphone').disabled = false;
			}else{
				Account = setTimeout("RemainTime()",1000);
				iTime=iTime-1;
			}
		}else{
			sTime='没有倒计时';
		}
		document.getElementById('zphone').value = sTime;
	}
</script>
<body>
<form action="vc7" method="post" name="formUser" onSubmit="return register();">
	<table width="100%"  border="0" align="left" cellpadding="5" cellspacing="3">
		<tr>
			<td align="right">手机<td>
		<input id="mobile" name="mobile" type="text" size="25" class="inputBg" /><span style="color:#FF0000"> *</span>
        </tr>
		<tr>
			<td align="right">手机验证码</td>
			<td align="left">
				<input type="text" id="mobile_code" name="mobile_code" class="inputBg" size="25" />
				<input id="zphone" type="button" value=" 获取手机验证码 " style="width: 120px"  onClick="get_mobile_code()">
			</td>
		</tr>
		<tr>
			<td> </td>
			<td>
				<input type="submit" value="登录" id="btnOK" />
			</td>
		</tr>
		<tr>
			<td> </td>
			<td>
				<div id="err" style="color: red;">${err }</div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>