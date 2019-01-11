package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardContentViewCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardContentViewCommand");
		BoardDao dao = BoardDao.getInstance();
		
		int num=Integer.parseInt(request.getParameter("num")); //��¥ �ʿ��Ѱ� num ��
		//String pageNum = request.getParameter("pageNum");
		
		BoardBean  article = dao.getArticle(num);
		//article: num�� ���ڵ��� ��� ����(�ۼ���, ����, ��Ÿ ���)
		
		request.setAttribute("article", article);
		
	}

}
