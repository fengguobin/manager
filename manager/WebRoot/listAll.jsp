<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/head.jsp"%>
<center>
	<h2>xxxx</h2>
	<c:set var="url" value="${pageContext.request.contextPath}/us/listAll" />
	<c:forEach var="file" items="${pageBean.data}">
	<table border width = "70%">
	
	<tr>
	<td>文件说明</td>
	<td width = "70%"><a href = "${pageContext.request.contextPath}/dls?id = ${file.id}">${file.name}</a></td>
	<td width = "15%">文件大小</td>
	<td>${file.size}字节</td>
	</tr>
	
	<tr>
	<td width = "15%">更新日期</td>
	<td width = "45%">${file.lastModfied}</td>
	<td width = "15%">下载次数</td>
	<td>${file.hits}</td>
	</tr>
	
	<tr>
	<td width = "15%">详细描述</td>
	<td colspan = "3">${file.descpription }</td>
	</tr>
	
	</table>
	</c:forEach>
	
	每页${pageBean.pageSize}行 共${pageBean.totalRows }行 页数${pageBean.curPage}/${pageBean.totalPage}
	
	
	<c:choose>
	<c:when test = "${pageBean.curpage == 1}">首页   上一页</c:when>
	<c:otherwise>
	<a href = "${url}?page = 1">首页</a>
	<a href = "${url}?page = ${pageBean.curPage - 1}">上一页</a>
	</c:otherwise>
	
	<c:when test = "${pageBean.curPage >= pageBean.totalPages}">下页   尾页</c:when>
	<c:otherwise>
	<a href = "${url}?page = ${pageBean.curPage + 1}">下一页</a>
	<a href = "${url}?page = ${pageBean.total}">尾页</a>
	</c:otherwise>
	
	
	</c:choose>
</center>