<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="color.jsp"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<style type="text/css">
body{
	align:center;
	text-align: center;
}
</style>

<script type="text/javascript">
    
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
		alert("비밀번호를 입력하십시요.");
		document.delForm.passwd.focus();
		return false;
 	}
  }    
     
</script>
</head>

<body bgcolor="<%=bodyback_c%>">
<b>글삭제</b>
<br>
<form method="POST" name="delForm" action="deletePro.bd?pageNum=${param.pageNum }" 
   	onsubmit="return deleteSave()"> 
 
 <table align="center"  width="360">
  <tr height="30">
     <td align=center  bgcolor="<%=value_c%>">
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  
  <tr height="30">
     <td align=center >비밀번호 :   
       <input type="password" name="passwd" size="8" maxlength="12">
	   <input type="hidden" name="num" value="${param.num }"></td>
 </tr>
 
 <tr height="30">
    <td align=center bgcolor="<%=value_c%>">
      <input type="submit" value="글삭제" >
      <input type="button" value="글목록" 
       onclick="document.location.href='list.bd?pageNum=${param.pageNum }'">     
   </td>
 </tr>  
</table> 
</form>
</body>
</html> 
