<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/head.jsp"%>
<center>
	<h2>分类显示</h2>
	<c:set var = "url" value = "${pageContext.request.contextPath}/us/sort"/>
	
	<from action = "${url}" method = "post"> 
	请选择类别:
	<select name = "tpye">
	<option value = "" <c:if test = "${empty param.type}"> selected </c:if>>全部类别</option>
	<option value = "1" <c:if test = "${param.type == 1}"> selected </c:if>>图片</option>
	<option value = "2" <c:if test = "${param.type == 2}"> selected </c:if>>flash</option>
	<option value = "3" <c:if test = "${param.type == 3}"> selected </c:if>>音乐</option>
	<option value = "4" <c:if test = "${param.type == 4}"> selected </c:if>>视频</option>
	<option value = "5" <c:if test = "${param.type == 5}"> selected </c:if>>其他</option>
	</select>
	<input type = "submit" value = "查看">
	</from>
	
	
	<c:forEach var = "file" items = "${pageBean.data}">
	
	<table border width = "70%">
	
	<tr>
	<td width = "15%">文件说明</td>
	<td><a href = "${pageContext.request.contextPath}/dls?id = ${file.id}">${file.name }</a></td>
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