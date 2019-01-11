package myPkg;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;
import board.BoardDao;

public class BoardSelectCommand extends BoardCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardSelectCommand");

		BoardDao dao = BoardDao.getInstance();

		int pageSize = 10 ; /* 한 페이지에 10개의 글이 보이게 하겠다. */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 2
		} 

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 1,11
		int endRow = currentPage * pageSize; // 10,20
		int count = 0;
		int number=0; 

		List<BoardBean> al = null;

		count = dao.getArticleCount(); // 전체 레코드 수 리턴 

		if (count > 0) { //하나라도 삽입된게 있으면 (0보다 크면)
			al = dao.getArticles(startRow, endRow); //al에 집어넣는다
			//syso(articleList.get(0).getNum());

		}
		number=count-(currentPage-1)*pageSize; // 37-(2-1)*10 = 27

		System.out.println("BoardSelectCommand al:"+al.size());

		request.setAttribute("count", count); //전체 레코드 수
		request.setAttribute("pageNum", pageNum); //현재 클릭한 페이지 번호
		request.setAttribute("number", number); //현재페이지에 나타날 첫번째 레코드
		request.setAttribute("al", al); //현재페이지에만 보여줄 목록들

		int pageCount = count /pageSize +(count % pageSize == 0? 0 : 1);

		int pageBlock=5; /*화면 아래에 5개에 페이지가 보이게 할것이다.*/

		int startPage = ((Integer.parseInt(pageNum)-1)/pageBlock*pageBlock)+1;
		int endPage = startPage +pageBlock-1;
		if(endPage > pageCount) endPage = pageCount;

		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageCount",pageCount);


		request.getServletContext().setAttribute("flag", "true"); //새로고침 했을때 계속 삽입되지 않게

		request.setAttribute("count", count);

	}
}