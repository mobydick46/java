<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比邻科技医疗信息系统登录</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/plugins/jquery.textbox.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function load(){
		$.post("${pageContext.request.contextPath}/basicattribute/load.do",
				function(result) {
					if (null != result.data) {
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 100px;background-color: #f0f0f0; overflow: hidden">
		<div style="margin-left:20px">
			<h1>
			</br>
				<em>比邻科技医疗信息系统</em>
			</h1>
		</div>
	</div>
	
	<div region="south" style="height: 40px; padding: 5px;background-color: #f0f0f0" align="center">
		  友情链接&nbsp;<a href="http://www.billion.net.cn/page/html/company.php">关于比邻</a>
			    &nbsp;<a href="http://www.billion.net.cn/page/services/contact.php">联系我们</a>
			    &nbsp;<a href="http://billion.net.cn/product/">更多产品</a>&nbsp;
			    <a href="http://billion.net.cn">@版权所有 比邻科技</a>
	</div>
	
	<div region="center" style="overflow: hidden;background-color: #EEEEE;">
		<div align="center" class="bigfont">
			<div style="padding: 130px 280px;">
				<form id="adminlogin" method="post" name="adminlogin"
					action="${pageContext.request.contextPath}/onlineuser/login.do">
					<table cellpadding="5">
						<tr>
							<td align="right">账户</td>
							<td><input type="text" id="loginid" name="loginid" 
								class="easyui-validatebox"/>
							</td>
						</tr>
						<tr>
							<td align="right">密码</td>
							<td><input id="password" name="password" type="password"
								class="easyui-validatebox"/>
							</td>
						</tr>
						<tr>
							<td align="right">角色</td>
							<td><input class="easyui-combobox" id="roleid"
								name="roleid" /> 
							</td>
						</tr>
					</table>
				</form>
				</br>
				<div "text-align: center" align="center" class="bigfont">
					<a href="javascript:load()" class="easyui-linkbutton"
						iconCls="icon-user" plain="true">登录</a>
					<a href="javascript:reset()" class="easyui-linkbutton"
						iconCls="icon-reset" plain="true">重置</a> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	if ('${errorMsg}' != '') {
		$.messager.alert("系统提示", '${errorMsg}');
	}
</script>