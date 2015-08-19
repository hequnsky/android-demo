<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>修改用户资料</h1>
	<form action="uploadUserInfo.do" method="post" enctype="multipart/form-data">
	选择文件：<input type="file" name="file"/><br>
	id:<input type="text" name="id">
	昵称:<input type="text" name="name">
	城市:<input type="text" name="city">
	年龄:<input type="text" name="age">
	性别:<input type="text" name="sex">
	地址:<input type="text" name="address">
		<input type="submit" value="提交"/>
	</form>
</body>
</html>