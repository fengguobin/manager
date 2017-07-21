<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/head.jsp"%>
<center>
	<h2>下载排行</h2>
	<table border width = "50%">
	<tr>
	<th width = "15%">排名</th>
	<th width = "65%">文件说明</th>
	<th width = "20%">下载次数</th>
	</tr>
	
	<c:forEach var = "file" items = "${top}" varStatus = "vs">
	<tr>
	<td align = "center">${vs.count}</td>
	<td align = "center"><a href = "${pageContext.request.contextPath}/us/show?id = ${file.id}">${file.name}</a></td>
	<td align = "center">${file.hits}</td>
	</tr>
	</c:forEach>
	</table>
</center>