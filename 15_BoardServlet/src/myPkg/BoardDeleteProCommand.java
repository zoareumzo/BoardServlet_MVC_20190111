package myPkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDao;

public class BoardDeleteProCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("BoardDeleteProCommand");
		BoardDao dao = BoardDao.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		String pageNum = request.getParameter("pageNum");
		
		int article = dao.deleteArticle(num, passwd);
		
		response.setContentType("text/html; charset=UTF-8");

		if(article != 1){
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
			
			int count = dao.getArticleCount(); 
			int pn = Integer.parseInt(pageNum);
			int pageCount = count / 10 + (count % 10 == 0 ? 0 : 1);

			if (pageCount < pn) { 
				request.setAttribute("pageNum", Integer.parseInt(pageNum)-1);
			} 
			else {
				request.setAttribute("pageNum",pageNum);
			}
		}
		
		
	}

}
