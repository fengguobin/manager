<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/head.jsp"%>
<center>
	<h2>文件详细信息</h2>
	<table border width = "70%">
	<tr>
	<td width = "15%">文件说明</td>
	<td width = "45%">${file.name}</td>
	<td width = "15%">文件大小</td>
	<td>${file.size}字节</td>
	</tr>
	
	<tr>
	<td width = "15%">更新日期</td>
	<td width = "45%">${file.lastModified}</td>
	<td width = "15%">下载次数</td>
	<td>${file.hits}</td>
	</tr>
	
	<tr>
	<td width = "15%">详细描述</td>
	<td colspan = "3">${file.description}</td>
	</tr>
	
	<tr>
	<td colspon = "4" align = "centet">
	<a href = "${pageContext.request.contextPath}/dls?id = ${file.id}">下载</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href = "${pageContext.request.contextPath}/us.top">返回</a>
	</td>
	</tr>
	</table>
</center>