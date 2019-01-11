package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardContentViewCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardContentViewCommand");
		BoardDao dao = BoardDao.getInstance();
		
		int num=Integer.parseInt(request.getParameter("num")); //진짜 필요한건 num 값
		//String pageNum = request.getParameter("pageNum");
		
		BoardBean  article = dao.getArticle(num);
		//article: num번 레코드의 모든 정보(작성자, 제목, 기타 등등)
		
		request.setAttribute("article", article);
		
	}

}
