package edu.kh.semi.board.controller;

import java.beans.beancontext.BeanContextChildSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.semi.board.model.service.BoardService;
import edu.kh.semi.board.model.service.ReplyService;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.BoardImage;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
import edu.kh.semi.board.model.vo.Reply;
import edu.kh.semi.common.MyRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

//프론트 컨트롤러 패턴 : 크라이언트의 요청을 한 곳으로 집중시켜 개발,유지보수 효율 증가 시킴 
//요청별 Servlet을 작성하지 않아 클래스 관리에 효율적
@WebServlet("/board/*")
public class BoradController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//데이터 전달방식 저장용 변수 
		String method = req.getMethod();
		
		//요청주소 뒷부분을 잘라내어 구분방법 만들기 
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring( (contextPath + "/board/").length() );
		//요청 주소에서 /semi/board/의 길이만큼 잘라낸 후 나머지 문자열 command에 저장 
		
		
		String path = null;
		RequestDispatcher dispatcher =null;
		String message = null;
		try {
			//command에 저장된 값에 따라 서로 다른 동작을 수행 
			
			BoardService service = new BoardService();
			//게시판 관련 기능 수행 시 "현재 페이지"는 여러 의미로 많이 사용되기 때문에 
			//미리 얻어와서 준비 시켜두는 것이 좋다
			
			int cp = req.getParameter("cp") == null ? 1: Integer.parseInt(req.getParameter("cp"));
			
			
			//게시글 목록 조회 Controller
			if(command.equals("list")) {
				
				//목록조회 순서
				// 1. DB에 저장된 조회 가능한 게시글 전체 수 카운트 
				// 2. 전체 게시글 수 + 현재 페이지를 이용하여 페이징 처리에 필요한 숫자 생성
				Pagination pagination = service.getPagination(cp);
				
				// 3. 조회 되어지는 게시글 번호를 계산하여 DB에서 조회해옴 
				List<Board> boardList = service.selectBoardList(pagination);
				
				//4.화면 출력
				req.setAttribute("pagination", pagination);
				req.setAttribute("boardList", boardList);
				
				//요청위임
				path = "/WEB-INF/views/board/boardList.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
						
			}
			
			//게시글 상세조회 
			else if(command.equals("view")) {
				//상세 조회 시 쿼리 스트링에 작성된 값 : no, cp 
				
				//게시글 번호 (PK) 얻어오기
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				//로그인한 회원의 번호를 조회 
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				
				int memberNo = 0;
				if(loginMember!=null) memberNo = loginMember.getMemberNo();
				
				Board board = service.selectBoardList(boardNo,memberNo);
				
				if(board != null) {
					
					List<Reply> rList = new ReplyService().selectReplyList(boardNo);
					req.setAttribute("rList", rList);
					
					
					//조회 결과 있음 
					req.setAttribute("board", board);
					
					
					path = "/WEB-INF/views/board/boardView.jsp";
					
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
				}else {
					
					
					req.setAttribute("message", "게시글이 존재하지 않습니다.");
					
					//게시글 없을 경우 목록으로 이동 
//					resp.sendRedirect(req.getContextPath()+"/board/list");
					resp.sendRedirect("list");
					
				}
				
			}
			//게시글 작성
			else if(command.equals("insert")) {
				
				//Get 방식 요청시 
				if(method.equals("GET")) {
					//카테고리 조회
					List<Category> category = service.selectCategory();
					
					req.setAttribute("category", category);
					
					path = "/WEB-INF/views/board/boardInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
				}
				//post 요청시 (게시글 등록)
				else {
					//***** 주의 ******
					//enType="multipart/form-data" 형식의 태그에서 전달된 파라미터는 
					// HttpServletRequest로는 다룰 수 없다. 
					// --> cos.jar에서 제공하는 MultipartRequest 사용 
					
					//**multipartRequest사용 
					//1. 업로드 되는 총 파일 최대 용량지정 (byte)
					int maxSize = 1024 * 1024 *100; //100MB
					
					
					//2. 업로드 파일 저장 경로 ->특정 폴더의 컴퓨터 절대경로 작성 
					// ex) D:\ ~~ 
					 HttpSession session = req.getSession();
					 String root = session.getServletContext().getRealPath("/");
					 //webapp폴더의 절대경로 (컴퓨터 상)  - properties -> location
					 
					 String filePath = "/resources/images/board/"; //DB에 저장, 주소경로 사용 
					 
					 String realPath = root+filePath;
					 
					//3. 저장되는 파일의 이름을 변경 
					 // MyRenamePolicy
					 
					//객체생성
					 MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
					
					 

					 
					 
					 //MultipartRequest 다루기 
					 //1. 텍스트 형식의 파라미터 
//					 mReq.getParameter("boardTitle");
					 String boardTitle = mReq.getParameter("boardTitle");
					 String boardContent = mReq.getParameter("boardContent");
					 int categoryCode = Integer.parseInt(mReq.getParameter("categoryCode"));
					 
					 int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					 
					 Board board = new Board();
					 board.setBoardTitle(boardTitle);
					 board.setBoardContent(boardContent);
					 board.setCategoryCode(categoryCode);
					 board.setMemberNo(memberNo);
					 
					 
					 //2. 파일 형식의 파라미터 
					 Enumeration<String> files = mReq.getFileNames();
					 //Enumeration<String> == iterator(ResultSet과 유사): 반복 접근자 
					 // form에서 전달된 input type="file" name속성 모두 반환
					 //파일이 업로드 되지 않아도 모든 요소를 얻어옴
					 
					 //업로드된 이미지 정보를 담을 List생성 
					 List<BoardImage> imgList = new ArrayList<BoardImage>();
					 
					 
					 while(files.hasMoreElements()) {
						 //다음 요소(name)가 있으면 true
						 
						 String name = files.nextElement(); // 다음요소 내려오기 
						 
						 //업로드된 파일이 존재할 경우(변경된 name 있음) 
						 if(mReq.getOriginalFileName(name) != null) {
							 BoardImage temp = new BoardImage();
							 temp.setImgName(mReq.getFilesystemName(name));
							 temp.setImgOriginal(mReq.getOriginalFileName(name));
							 temp.setImgPath(filePath); //root는 이미 작성 되어있음 
							 
							 //name img0~ img3에서 숫자를 제외한 img문자열을 제거 후 imgLevel set
							temp.setImgLevel(Integer.parseInt(name.replace("img","")));
							
							//imgList에 추가 
							imgList.add(temp);
						 }
					 }
					 
					 //board, imgList DB에 저장 (Service 호출)
					 int result = service.insertBoard(board, imgList);
					 
					 //결과반환
					 if(result >0) {
						 message = "게시글 작성이 등록 되었습니다. ";
						 
						 //상세조회 redirect 주소
						 path = "view?no="+ result+"&cp=1";
						 
					 }else {
						 message ="게시글 등록 중 문제 발생";
						 //게시글 화면으로 redirect 
						 path = "insert";
					 }
					 session.setAttribute("message", message);
					 resp.sendRedirect(path);
				}
			}
			
			//게시글 수정화면으로 전환
			else if(command.equals("updateForm")) {
				//Post 요청방식 
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				//게시글 등록화면에 이전에 작성된 내용일 작성되어있어야함 
				// 상세조회 + 게시글 삽입
				
				//수정할 게시글 불러오기 
				Board board = service.updateView(boardNo);
				
				//카테고리 목록 조회 
				List<Category> category = service.selectCategory();
				
				req.setAttribute("board", board);
				req.setAttribute("category", category);
				
				path = "/WEB-INF/views/board/boardUpdate.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
				System.out.println(board);
				System.out.println(boardNo);
				
			}
			
			//게시글 수정 
			else if(command.equals("update")) {
				//**multipartRequest사용 
				//1. 업로드 되는 총 파일 최대 용량지정 (byte)
				int maxSize = 1024 * 1024 *100; //100MB
				
				
				//2. 업로드 파일 저장 경로 ->특정 폴더의 컴퓨터 절대경로 작성 
				// ex) D:\ ~~ 
				 HttpSession session = req.getSession();
				 String root = session.getServletContext().getRealPath("/");
				 //webapp폴더의 절대경로 (컴퓨터 상)  - properties -> location
				 
				 String filePath = "/resources/images/board/"; //DB에 저장, 주소경로 사용 
				 
				 String realPath = root+filePath;
				 
				//3. 저장되는 파일의 이름을 변경 
				 // MyRenamePolicy
				 
				//객체생성
				 MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
				 
				//MultipartRequest 다루기 
				 //1. 텍스트 형식의 파라미터 
//				 mReq.getParameter("boardTitle");
				 String boardTitle = mReq.getParameter("boardTitle");
				 String boardContent = mReq.getParameter("boardContent");
				 int categoryCode = Integer.parseInt(mReq.getParameter("categoryCode"));
				 int boardNo = Integer.parseInt(mReq.getParameter("no"));
				 
				 int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				 
				 Board board = new Board();
				 board.setBoardTitle(boardTitle);
				 board.setBoardContent(boardContent);
				 board.setCategoryCode(categoryCode);
				 board.setMemberNo(memberNo);
				 board.setBoardNo(boardNo);
				
				 //파일형식의 파라미터 얻어오기 
				 Enumeration<String> files = mReq.getFileNames();
				 // 요청 시 전달 받으 모든 input Type="file" 속성 값
				 
				 //이미지 정보 저장용 리스트 
				 List<BoardImage> imgList = new ArrayList<BoardImage>();
				 
				 while(files.hasMoreElements()) {
					 //.hasMoreElements() 다음 속성 값이 있다면 true 
					 String name = files.nextElement();
					 //name의 속성값을 얻어옴 (ㅑㅡㅎ)
					 
//					 mReq.getFilesystemName(name);
					 // 변경된 파일명
					 // 파일이 있으면 이름, 없으면 null 반환
					 
//					 mReq.getOriginalFileName(name);
					 // 매개변수 name과 name 속성 값이 같은 input type ="file"요소에 업로드된 파일 원본 파일면 
					 // 파일이 있으면 이름, 없으면 null 반환
					  
					 if(mReq.getFilesystemName(name) != null) {
						 //이미지 파일 정보 저장용 개체 BoardImage 객체 생성 
						 BoardImage temp = new BoardImage();
						 temp.setImgPath(filePath);
						 temp.setImgName(mReq.getFilesystemName(name)); //변경된 이름 
						 temp.setImgOriginal(mReq.getOriginalFileName(name));//원본 파일이름 
						 temp.setImgLevel(Integer.parseInt(name.replace("img","")));
						 
						 temp.setBoardNo(boardNo);
						 
						 imgList.add(temp);
					 }
					 
					 
				 }
				
				 //게시글 수정 서비스 호출 & 결과 받기
				 int result = service.updateBoard(board, imgList);
				 
				 if(result >0) {
					 message = "게시글이 수정되었습니다.";
					 path = "view?no="+boardNo+"&cp="+mReq.getParameter("cp");
				 }else {
					 message = "게시글 수정 실패";
					 path = req.getHeader("referer")+"?no="+ boardNo;;
					 // 이전페이지 요청 
				 }
				 
				 session.setAttribute("message", message);
				 resp.sendRedirect(path);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req ,resp);
		//post방식으로 요청이 오면 doGet으로 메소드 전달
		// 모두 doGet으로 처리
	}
}
