package myPkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardUpdateProCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardUpdateProCommand");

		BoardDao dao = BoardDao.getInstance();

		int num=Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");

		BoardBean article = new BoardBean(num, writer, subject, email, content, passwd); //BoardBean에 이것만 따로 처리하는 생성자를 만들어주기

		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setIp(request.getRemoteAddr());

		int cnt = dao.updateArticle(article);

		response.setContentType("text/html; charset=UTF-8");

		if(cnt != 1){
			PrintWriter out = null;
			try {
				out= response.getWriter(); //out: web브라우저 연결다리
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("<script>alert('비번 일치 안함');history.go(-1);</script>"); //이전(updateForm)으로 돌아가라

			request.setAttribute("match", "false"); //Controller에서 비번일치하지 않는 사실을 알기 위해서 => match=false

		}
		else{
			request.setAttribute("match", "true"); // match = true
		}

	}
}
