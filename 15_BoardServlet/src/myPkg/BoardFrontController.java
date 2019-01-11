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
		application = config.getServletContext(); //ServletContext객체를 얻어서 application에 넣음
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		doProcess(request, response); //어느 요청이 들어오든 doProcess로 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("doPost");
		doProcess(request, response); //어느 요청이 들어오든 doProcess로 
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
			//list.bd를 요청하는 서블릿

			System.out.println("/list.bd"); 
			String pageNum = request.getParameter("pageNum");
			//맨처음에는 Null이 들어감 (처음에는 넣어준 값이 없기 때문, but 페이지를 클릭하면 그 값이 넘어감)

			request.setAttribute("pageNum", pageNum);
			//넘어오는 페이지 request 속성으로 처리

			command = new BoardSelectCommand();
			command.execute(request, response);
			viewPage ="list.jsp"; //list.bd가 들어오면

		}else if(com.equals("/content.bd")){ 

			System.out.println("FrontContorller의 /content.bd");
			command = new BoardContentViewCommand();
			//r.gP("pageNum")
			command.execute(request, response);
			viewPage ="content.jsp"; //request객체를 가지고 넘어감


		}else if(com.equals("/updateForm.bd")){

			System.out.println("/updateForm.bd");

			command = new BoardUpdateFormCommand();
			command.execute(request, response);
			viewPage ="updateForm.jsp";

		}else if(com.equals("/updatePro.bd")){ //updateForm.jsp 수정클릭할 때 요청

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
