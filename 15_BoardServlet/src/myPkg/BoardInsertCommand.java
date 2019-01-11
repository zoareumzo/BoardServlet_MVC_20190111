package myPkg;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardInsertCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("BoardSelectCommand");
		BoardDao dao = BoardDao.getInstance();
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		
		BoardBean article = new BoardBean(writer, subject, email, content, passwd); //BoardBean�� �̰͸� ���� ó���ϴ� �����ڸ� ������ֱ�
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setIp(request.getRemoteAddr());
		
		dao.insertArticle(article);
		
	}

}
