package edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.semi.board.model.service.ReplyService;
import edu.kh.semi.board.model.vo.Reply;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//데이터 전달방식 저장용 변수 
		String method = req.getMethod();
		
		//요청주소 뒷부분을 잘라내어 구분방법 만들기 
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring( (contextPath + "/reply/").length() );
		//요청 주소에서 /semi/board/의 길이만큼 잘라낸 후 나머지 문자열 command에 저장
		
		ReplyService service = new ReplyService();
		
		try {
			
			if(command.equals("select")) {
				//파라미터 
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				List<Reply> rList = service.selectReplyList(boardNo);
				
				//ajax 비동기 통신 시 연결된 스트림을 이용할 값을 출력 -> gson, json 사용 
				
				new Gson().toJson(rList,resp.getWriter());
				
				
			}else if(command.equals("insert")) {
				//파라미터를 얻어와 reply vo에 담아 Service 호출하기 
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				String replyContent =req.getParameter("replyContent");
				
				Reply reply = new Reply();
				reply.setMemberNo(memberNo);
				reply.setBoardNo(boardNo);
				reply.setReplyContent(replyContent);
				
				int result = service.insertReply(reply);
				
				resp.getWriter().print(result);
				
			}else if(command.equals("update")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				String replyContent =req.getParameter("replyContent");
				
		
				int result  = service.updateReply(replyNo,replyContent);
				
				resp.getWriter().print(result);
				
			}else if
			(command.equals("delete")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				int result = service.deleteReply(replyNo);
				
				resp.getWriter().print(result);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

}
