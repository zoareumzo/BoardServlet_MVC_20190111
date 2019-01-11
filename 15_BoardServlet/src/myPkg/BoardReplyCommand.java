package myPkg;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardReplyCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("BoardDeleteProCommand");
		BoardDao dao = BoardDao.getInstance();
		
		System.out.println("num:" + request.getParameter("num"));
		System.out.println("ref:" + request.getParameter("ref"));
		System.out.println("writer:" + request.getParameter("writer"));
		
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		Timestamp reg_date = new Timestamp(System.currentTimeMillis());
		String ip = request.getRemoteAddr();
		
		BoardBean article = new BoardBean(num, writer, subject, email, content, passwd, reg_date, ip,ref, re_step, re_level);
		
		dao.replyArticle(article);
				
	}

}
