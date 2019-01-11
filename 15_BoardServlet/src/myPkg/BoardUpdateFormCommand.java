package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardUpdateFormCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("BoardUpdateFormCommand");
		BoardDao dao = BoardDao.getInstance();

		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println();

		BoardBean  article = dao.getArticle(num);
		System.out.println(article.getWriter());

		request.setAttribute("article", article);
	}

}
