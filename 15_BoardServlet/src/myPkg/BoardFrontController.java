package myPkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
//@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext application;  
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		application = config.getServletContext(); //ServletContext��ü�� �� application�� ����
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		doProcess(request, response); //��� ��û�� ������ doProcess�� 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("doPost");
		doProcess(request, response); //��� ��û�� ������ doProcess�� 
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doProcess");
		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		BoardCommand command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		System.out.println("com:"+com);

		if(com.equals("/insert.bd")){
			if(!application.getAttribute("flag").equals("true")){

				command = new BoardInsertCommand();
				command.execute(request,response);
				viewPage = "/list.bd";
			}
			else{
				viewPage ="list.bd";
			}


		}else if(com.equals("/list.bd")){
			//list.bd�� ��û�ϴ� ����

			System.out.println("/list.bd"); 
			String pageNum = request.getParameter("pageNum");
			//��ó������ Null�� �� (ó������ �־��� ���� ���� ����, but �������� Ŭ���ϸ� �� ���� �Ѿ)

			request.setAttribute("pageNum", pageNum);
			//�Ѿ���� ������ request �Ӽ����� ó��

			command = new BoardSelectCommand();
			command.execute(request, response);
			viewPage ="list.jsp"; //list.bd�� ������

		}else if(com.equals("/content.bd")){ 

			System.out.println("FrontContorller�� /content.bd");
			command = new BoardContentViewCommand();
			//r.gP("pageNum")
			command.execute(request, response);
			viewPage ="content.jsp"; //request��ü�� ������ �Ѿ


		}else if(com.equals("/updateForm.bd")){

			System.out.println("/updateForm.bd");

			command = new BoardUpdateFormCommand();
			command.execute(request, response);
			viewPage ="updateForm.jsp";

		}else if(com.equals("/updatePro.bd")){ //updateForm.jsp ����Ŭ���� �� ��û

			System.out.println("/updatePro.bd");

			command = new BoardUpdateProCommand();
			command.execute(request, response);

			if(request.getAttribute("match").equals("false")){
				return;
			}
			else{
				viewPage = "list.bd";
			}

		}else if(com.equals("/deletePro.bd")){

			System.out.println("/deletePro.bd");

			command = new BoardDeleteProCommand();
			command.execute(request, response);
			
			Object pageNum = request.getAttribute("pageNum");
			
			if(request.getAttribute("match").equals("false")){
				return;
			}
			else{
				viewPage = "list.bd?pageNum="+pageNum;
			}

		}else if(com.equals("/id_check_proc.bd")){

		}else if(com.equals("/replyPro.bd")){

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);


	}

}
