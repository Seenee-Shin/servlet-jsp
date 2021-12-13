package edu.kh.practice.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.practice.model.Book;
import edu.kh.practice.model.BookDAO;


@WebServlet("/ncstest/bookRegister.do")
public class BookRegisterCtrl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}
	
	
	//GET, POST 전달방식 관계없이 모두 처리
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String method = req.getMethod(); //데이터 전달 방식을 변수에 저장 
		if("GET".equals(method)) { // 데이터 전달 방식이 "get"인 경우
			
			//책등록 화면 jsp로 요청 위임
			String path = "/WEB-INF/views/bookRegister.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		}else { //POST 방식 일 경우
			String bookTitle = req.getParameter("bookTitle");
			String bookAuthor = req.getParameter("bookAuthor");
			
			int bookprice = Integer.parseInt(req.getParameter("bookPrice"));
			int sale = Integer.parseInt(req.getParameter("sale"));
			
			Book book = new Book();
			book.setBookTitle(bookTitle);
			book.setBookAuthor(bookAuthor);
			book.setBookPrice(bookprice);
			book.setSale(sale);
			
			try {
				BookDAO dao = new BookDAO();
				
				int result = dao.bookRegister(book);
				
//				System.out.println("result :" + result);
				
				if(result >0) {
					// practice/ncstest/boardList.do 재요청 - redirect 
					resp.sendRedirect("/practice/ncstest/boardList.do");
					// 절대경로로 작성시 contextpath부터 모두 작성
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
				//sql exceptoin 발생시 에러 메세지를 출력하는 jsp로 요청
				
				String errorMessage = "SQL 관련 예외가 발생했습니다.";
				req.setAttribute("errorMessage", errorMessage);
				
				String path = "/WEB-INF/views/error.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
			}
		}
	}
}
