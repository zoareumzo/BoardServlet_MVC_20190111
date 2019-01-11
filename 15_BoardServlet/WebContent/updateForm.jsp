<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="board.BoardDao"%>
<%@ page import="board.BoardBean"%>
<%@include file="color.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	align: center;
	text-align: center;
}
</style>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body bgcolor="<%=bodyback_c%>">
	<b>글수정(updateForm.jsp)</b>
	<br>
	<form method="post" name="writeform" action="updatePro.bd?pageNum=${param.pageNum}" onsubmit="return writeSave();">
		<table width="400" bgcolor="<%=bodyback_c%>" align="center">
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">이 름</td>
				<td align="left" width="330">
					<input type="text" size="30" maxlength="10" name="writer" value="${article.writer }">
					<input type="hidden" name="num" value="${article.num }"></td> 
					<!-- 숨어서 넘어가는 num값은 article로 -->
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">제 목</td>
				<td align="left" width="330">
					<input type="text" size="50"
					maxlength="50" name="subject" value="${article.subject}"></td>
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">Email</td>
				<td align="left" width="330">
					<input type="text" size="50" maxlength="30" name="email" value="${article.email }"></td>
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">내 용</td>
				<td align="left" width="330">
					<textarea name="content" rows="13" cols="50">${article.content }</textarea></td>
			</tr>
			<tr>
				<td width="70" bgcolor="<%=value_c%>" align="center">비밀번호</td>
				<td align="left" width="330">
					<input type="password" size="10" maxlength="12" name="passwd"></td>
			</tr>
			<tr>
				<td colspan=2 bgcolor="<%=value_c%>" align="center">
					<input type="submit" value="글수정"> 
					<input type="reset" value="다시작성"> 
					<input type="button" value="목록보기" onclick="document.location.href='list.bd?pageNum=${param.pageNum}'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
