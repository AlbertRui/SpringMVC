<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<!-- 
  		模拟修改操作
  		1.原始数据为：1, tom, 123456, tom@163.com, 12
  		2.密码不能被修改
  		3.表单回显，模拟操作直接在表单填写对应的属性值
  	 -->
  	 
  	<form action="springmvc/testModelAttribute" method="post">
  	 	<input type="hidden" name="id" value="1"/><br>
  	 	username:<input type="text" name="username" value="Tom"/><br>
  	 	email:<input type="text" name="email" value="tom.163.com"/><br>
  	 	age:<input type="text" name="age" value="15"/><br>
  	 	<input type="submit" value="submit"/>
  	</form>
  	<br><br>
  	<form action="springmvc/testPojo" method="post">
  		username:<input type="text" name="username"/><br>
  		password:<input type="password" name="password"/><br>
  		email:<input type="text" name="email"/><br>
  		age:<input type="text" name="age"/><br>
  		city:<input type="text" name="address.city"/><br>
  		province<input type="text" name="address.province"/><br>
  		<input type="submit" value="submit"/>
  	</form>
  	<a href="springmvc/testCookieValue">Test CookieValue</a>
  	<br><br>
  	
  	<a href="springmvc/testSessionAttributes">Test Session Attributes</a>
  	<br><br>
  	
  	<a href="springmvc/testModelAndView">Test ModelAndView</a>
  	<br><br>
  
    <a href="springmvc/testServletAPI">Test Servlet API</a>
  	<br><br> 
  	
    <a href="springmvc/testRequestHeader">Test RequestHeader</a>
  	<br><br>
  	
  	<a href="springmvc/testRequestParam?username=user&age=11">Test RequestParam</a>
  	<br><br>
  	
  	<a href="springmvc/testMap">Test Map</a>
  	<br><br>
  	
  	<form action="springmvc/testMethod" method="post">
  		<input type="submit" value="submit"/>
  	</form>
  	<a href="HelloWorld">Hello, World!</a>
  	<br><br>
  	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
  	<br><br>
  	
  	<a href="springmvc/testAntPath/jfioap/test">Test AntPath</a>
  	<br><br>
  	
  	<a href="springmvc/testPathVariable/1">Test Variable</a>
  	<br><br>
  	
  	<a href="springmvc/testRest/1">Test Rest Get</a>
  	<br><br>
  	
  	<form action="springmvc/testRest/1" method="post">
  		<input type="hidden" name="_method" value="PUT"/>
  		<input type="submit" value="TestRestPut"/>
  	</form>
  	<br><br>
  	
  	<form action="springmvc/testRest/1" method="post">
  	    <input type="hidden" name="_method" value="DELETE"/>
  		<input type="submit" value="TestRestDelete"/>
  	</form>
  	<br><br>
  	
  	<form action="springmvc/testRest/1" method="post">
  		<input type="submit" value="TestRestPost"/>
  	</form>
  </body>
</html>
