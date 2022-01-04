package edu.kh.semi.board.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.board.model.dao.BoardDAO;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.BoardImage;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
import edu.kh.semi.common.XSS;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	/** paging 처리용 객체 생성
	 * @param cp
	 * @return pagination
	 * @throws Exception
	 */
	public Pagination getPagination(int cp) throws Exception{
		
		Connection conn = getConnection();
		
		int listCount = dao.getListCount(conn);
		
		close(conn);
		
		
		return new Pagination(listCount, cp);
	}

	/** 게시글 목록 조회
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination) throws Exception{
		Connection conn =getConnection();
		List<Board> boardList = dao.selectBoardList(pagination,conn);
		
		//조회된 게시글 목록에서 번호를 얻어와 해당 게시글 번호의 모든 이미지를 조회 
		for(Board temp : boardList) {
			List<BoardImage> imgList = dao.selectBoardImageList(temp.getBoardNo(), conn);
			
			//temp : 조회된 게시글 정보를 담은 board VO 
			// temp에 imgList세팅 
			temp.setImgList(imgList);
		}
		
		close(conn);
		
		return boardList;
	}

	/** 게시글 상세 조회
	 * @param boardNo
	 * @param memberNo 
	 * @return board(null)
	 * @throws Exception
	 */
	public Board selectBoardList(int boardNo, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(conn, boardNo);
		
		//이미지조회
		List<BoardImage> imglist = dao.selectBoardImageList(boardNo, conn);
		
		board.setImgList(imglist);
		
		
		//조회된 게시글이 있고, 해당 게시글의 작성자와 로그인된 회원이 같지 않으면 조회수 증가 
		if(board != null && board.getMemberNo() != memberNo) {
			int result = dao.increaseReadCount(conn, boardNo);
			
			if(result > 0) { 
				commit(conn);
				//조회된 게시글 정보 조회수 1증가 
				board.setReadCount(board.getReadCount()+1);
			}
			else {rollback(conn);}
		}
		
		
		
		close(conn);
		
		return board;
	}

	/** 카테고리 조회
	 * @return
	 * @throws Exception
	 */
	public List<Category> selectCategory() throws Exception{
		Connection conn = getConnection();
		
		List<Category> category = dao.selectCategory(conn);
		
		close(conn);
		
		
		return category;
	}

	/** insertBoard 
	 * @param board
	 * @param imgList
	 * @return result  (1)
	 * @throws Exception
	 */
	public int insertBoard(Board board, List<BoardImage> imgList) throws Exception {
		Connection conn = getConnection();
		
		
		// 다음 차례 게시글 번호 얻어오기 
		//게시글 이미지 정보 db삽입중 사이에 다른 Insert 요청에 의해 시퀀스번호 불일치 가능성 있음
		//이를 예방하기 위해 다음 시퀀스 번호 keep 
		
		int boardNo = dao.nextBoardNo(conn);

		
		//조회된 다음 글 번호를 board에 셋팅( DB 삽입시 사용 )
		board.setBoardNo(boardNo);
		
		//xss 방지 처리 후 board에 저장 -> XSS메소드사용 
		board.setBoardTitle(XSS.replaceParameter(board.getBoardTitle()));
		board.setBoardContent(XSS.replaceParameter(board.getBoardContent()));
		
		//개행문자 -> br태그로 변경 ->정규 표현식 사용 
		String content = board.getBoardContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		board.setBoardContent(content);
		
		
		// 게시글 삽입 SEQ_BOARD_NO.NEXTVAL
		int result = dao.insertBoard(board ,conn);
		
		
		// 이미지 삽입 CURRVAL 
		if(result>0) {
			//이미지 최대 4장 저장 
			//총 4번의 INSERT  -> FOR문이용 
			for(BoardImage img:imgList) {
				//BoardImg -> 어떤게시글의 이미지 인지 알수 있게 게시글 번호 추가 
				img.setBoardNo(boardNo);
				result = dao.insertBoardImage(conn, img);
				
				if(result == 0) {
					rollback(conn);
					//service의 메소드는 하나의 트랜잭션을 공유
					// -> 이미지 정보 삽입 전에 삽인된 게시글 정보도 트랜잭션에 담겨있음 
					break;
				}
				//보드 이미지 삽입이 모두 DB예 올라간 경우 
				if(result >0) {
					commit(conn);
					result = boardNo;
					
				}else rollback(conn);
			}
			
			//글작성 후 작성한 게시글로 이동 
			//상세 조회시 boardNo필요 -> controller로 반환하여 상세조회로 이동 
			// insert 성공 시 1, 실패시 0 -> 0보다 큰 값만 반환 -> boardNo > 0 이므로 o
			
		}else {
			rollback(conn);
		}
		
		
		return result;
		
	}

	/** 게시글 수정화면 전환
	 * @param boardNo
	 * @return board 
	 * @throws Exception
	 */
	public Board updateView(int boardNo) throws Exception {

		Connection conn =getConnection();
		
		Board board = dao.selectBoard(conn, boardNo);
		
		//이미지조회
		List<BoardImage> imglist = dao.selectBoardImageList(boardNo, conn);
		
		board.setImgList(imglist);
		
		//줄바꿈 <br>을 \r\n (원래의 표기)로 수정 
		String content = board.getBoardContent().replaceAll("<br>", "/r/n");
		
		board.setBoardContent(content);
		
		close(conn);
		
		return board;
	}

	/** 게시글 수정 
	 * @param board
	 * @param imgList
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(Board board, List<BoardImage> imgList) throws Exception{
		
		Connection conn = getConnection();
		
		//xss 방지 처리 후 board에 저장 -> XSS메소드사용 
		String boardTitle = XSS.replaceParameter(board.getBoardTitle());
		String boardContent = XSS.replaceParameter(board.getBoardContent());
		
		boardContent = boardContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		
		//게시글 부분 수정 
		int result= dao.updateBoard(board,conn);
		
		if(result > 0) {
			//게시글 수정 성공 시 이미지 수정 
			// 반복문으로 새로 업로드된 이미지 정보를 기존 board_img테이블에 저장된 행에 update
			// 기존 데이터가 없으면 insert, 
			
			for(BoardImage img : imgList) {
				result = dao.updateBoardImage(img, conn);
				//-> 기존 데이터 수정 성공 -> reusult ==1
				//-> 기존 데이터 없음 수정 실패 -> reusult ==0
				
				if(result==0) {
					result = dao.insertBoardImage(conn, img);
					
				}
				
				
			}
			
			if(result >0) commit(conn);
			else rollback(conn);
			
			
		}else {
			
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
}
