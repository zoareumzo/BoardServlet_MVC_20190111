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

		int pageSize = 10 ; /* �� �������� 10���� ���� ���̰� �ϰڴ�. */
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

		count = dao.getArticleCount(); // ��ü ���ڵ� �� ���� 

		if (count > 0) { //�ϳ��� ���ԵȰ� ������ (0���� ũ��)
			al = dao.getArticles(startRow, endRow); //al�� ����ִ´�
			//syso(articleList.get(0).getNum());

		}
		number=count-(currentPage-1)*pageSize; // 37-(2-1)*10 = 27

		System.out.println("BoardSelectCommand al:"+al.size());

		request.setAttribute("count", count); //��ü ���ڵ� ��
		request.setAttribute("pageNum", pageNum); //���� Ŭ���� ������ ��ȣ
		request.setAttribute("number", number); //������������ ��Ÿ�� ù��° ���ڵ�
		request.setAttribute("al", al); //�������������� ������ ��ϵ�

		int pageCount = count /pageSize +(count % pageSize == 0? 0 : 1);

		int pageBlock=5; /*ȭ�� �Ʒ��� 5���� �������� ���̰� �Ұ��̴�.*/

		int startPage = ((Integer.parseInt(pageNum)-1)/pageBlock*pageBlock)+1;
		int endPage = startPage +pageBlock-1;
		if(endPage > pageCount) endPage = pageCount;

		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageCount",pageCount);


		request.getServletContext().setAttribute("flag", "true"); //���ΰ�ħ ������ ��� ���Ե��� �ʰ�

		request.setAttribute("count", count);

	}
}