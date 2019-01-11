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

		BoardBean article = new BoardBean(num, writer, subject, email, content, passwd); //BoardBean�� �̰͸� ���� ó���ϴ� �����ڸ� ������ֱ�

		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setIp(request.getRemoteAddr());

		int cnt = dao.updateArticle(article);

		response.setContentType("text/html; charset=UTF-8");

		if(cnt != 1){
			PrintWriter out = null;
			try {
				out= response.getWriter(); //out: web������ ����ٸ�
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("<script>alert('��� ��ġ ����');history.go(-1);</script>"); //����(updateForm)���� ���ư���

			request.setAttribute("match", "false"); //Controller���� �����ġ���� �ʴ� ����� �˱� ���ؼ� => match=false

		}
		else{
			request.setAttribute("match", "true"); // match = true
		}

	}
}
