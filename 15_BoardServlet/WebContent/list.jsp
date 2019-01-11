<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="board.BoardDao"%>
<%@ page import="board.BoardBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="color.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
body {
	width: 100%;
	text-align: center;
}

table {
	margin: 0 auto;
}
</style>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="<%=bodyback_c%>">
	<b>글목록(전체 글:${count})</b>
	<table width="700">
		<tr>
			<td align="right" bgcolor="<%=value_c%>"><a href="writeForm.jsp">글쓰기</a>
			</td>
		</tr>
	</table>


	<c:if test="${count==0}">
		<table width="700">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${count!=0}">

		<table width="700" align="center">
			<tr height="30" bgcolor="<%=value_c%>">
				<td align="center" width="50">번 호</td>
				<td align="center" width="250">제 목</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="50">조 회</td>
				<td align="center" width="100">IP</td>
			</tr>

			<c:set var="number" value="${number +1}" />
			
			<c:forEach items="${al}" var="dto">
				<tr height="30">
					<td align="center" width="50"><c:set var="number"
							value="${number -1}" />
							 ${number }</td>
					<td align="left" width="250">
						<%-- <%int wid=0;%> --%>
						<c:set var="wid" value="0"/>
						<%-- wid에 0을 넣겠다 --%>
						
						 
	      <c:if test="${dto.re_level >0 }"> 
							<c:set var="wid" value="${20*dto.re_level }"/> <!-- 답글 들여쓰기  -->
							<img src="images/level.gif" width="${wid }" height="16"> 
							<img src="images/re.gif">
						</c:if> 
						
						<c:if test="${dto.re_level <=0 }"> <!--원글이기 때문에 re.gif 들여쓰기가 없음 -->


							<img src="images/level.gif" width="${wid }" height="16">
						</c:if> <a
						href="content.bd?num=${dto.getNum()}&pageNum=${pageNum}"> <!-- 내용보고나서 보고있던 페이지로 넘어오기 위해서 pageNum을 같이 넘긴다 -->
							${dto.getSubject()} <!-- 제목을 클릭하면 상세보기.bd 요청 -->
					</a> 
					<c:if test="${dto.getReadcount() >=10}">
					<img src="images/hot.gif" border="0" height="16">
					</c:if>

					</td>


					<td align="center" width="100">
					<a href="mailto:"${dto.email }>${dto.writer }</a>
					</td>

					<td align="center" width="150">${dto.reg_date }</td>
					<td align="center" width="50">${dto.readcount }</td>
					<td align="center" width="100">${dto.ip }</td>
				</tr>
				</c:forEach>
				<!-- for -->
		</table>
		</c:if>
		<!-- else -->



	<!-- 페이지 설정 -->	
    <c:if test="${count > 0 }">
        <c:if test="${startPage >5 }">
        
		<a href="list.bd?pageNum=${starPage -5 }">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="list.bd?pageNum=${i}">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount }">
	
		<a href="list.bd?pageNum=${startPage + 5 }">[다음]</a>
		</c:if>
		</c:if>
 
</body>
</html>

