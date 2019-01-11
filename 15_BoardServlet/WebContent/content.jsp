<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import = "board.BoardDao" %>
<%@ page import = "board.BoardBean" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html> 
<head> 
<title>게시판</title>
<style type="text/css">
body {
    width: 100%;
    text-align : center;
}
table {
    margin: 0 auto;
}
</style>
<link href="style.css" rel="stylesheet" type="text/css">
</head> 
 
<%
  	int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");

    
    SimpleDateFormat sdf = 
         new SimpleDateFormat("yyyy-MM-dd HH:mm");
  
    
	BoardDao dao = BoardDao.getInstance();
    BoardBean article =  dao.getArticle(num);
  
    
    int ref=article.getRef();	
    int re_step=article.getRe_step();
    int re_level=article.getRe_level();
   	System.out.println(num+","+ref+","+re_step+","+re_level);
   	
%>

<body bgcolor="<%=bodyback_c%>">  
<b>글내용 보기</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0"  
		bgcolor="<%=bodyback_c%>" align="center">  
		
  <tr height="30">
    <td align="center" width="125" bgcolor="<%=value_c%>">글번호</td>
    <td align="center" width="125">
    ${article.num }</td>
    <td align="center" width="125" bgcolor="<%=value_c%>">조회수</td>
    <td align="center" width="125">
     ${article.readcount }</td>
  </tr>
  
  
  <tr height="30">
    <td align="center" width="125" bgcolor="<%=value_c%>">작성자</td>
    <td align="center" width="125">
    ${article.writer }</td>
    <td align="center" width="125" bgcolor="<%=value_c%>" >작성일</td>
    <td align="center" width="125">
    	<fmt:formatDate value="${article.reg_date }" type="date"
    	dateStyle="full" pattern="yyyy-MM-dd"/></td>
  </tr>
  
  <tr height="30">
    <td align="center" width="125" bgcolor="<%=value_c%>">글제목</td>
    <td align="center" width="375" colspan="3">
    ${article.subject}</td>
  </tr>
  
  <tr>
    <td align="center" width="125" bgcolor="<%=value_c%>">글내용</td>
    <td align="left" width="375" colspan="3"><pre>
    ${article.content }
    </pre></td>
  </tr>
  
  
  
  <tr height="30">      
    <td colspan="4" bgcolor="<%=value_c%>" align="right" >
     
	  <input type="button" value="글수정" 
       onclick="document.location.href='updateForm.bd?num=${article.num}&pageNum=${param.pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  
	  
	  
	  <input type="button" value="글삭제" 
       onclick="document.location.href=
       'deleteForm.jsp?num=${article.num}&pageNum=${param.pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      
      
      
      <input type="button" value="답글쓰기" 
       onclick="document.location.href=
       'replyForm.jsp?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      
      
       <input type="button" value="글목록" 
       onclick="document.location.href='list.bd?pageNum=${param.pageNum}'">
       <!-- pageNum만 article로 설정하지않고 param으로 넘김 -->
    </td>
  </tr>
</table>    
</form>
</body>
</html>      
