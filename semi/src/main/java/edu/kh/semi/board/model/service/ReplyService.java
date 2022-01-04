package edu.kh.semi.board.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.board.model.vo.Reply;
import edu.kh.semi.common.XSS;
import edu.kh.semi.board.model.dao.ReplyDAO;

public class ReplyService {
	
	private ReplyDAO  dao = new ReplyDAO();

	public List<Reply> selectReplyList(int boardNo) throws Exception{	
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectReplyList(conn, boardNo);
		
		return rList;
	}

	public int insertReply(Reply reply) throws Exception {
		
		Connection conn =getConnection();
		
		//크로스사이트 스크립트 방지 후 content에 set
		reply.setReplyContent(XSS.replaceParameter(reply.getReplyContent()));
		
		//개행문자 처리 content에 set
		reply.setReplyContent(reply.getReplyContent().replaceAll("\r\n|\r|\n|\n\r", "<br>"));
		
		int  result = dao.insertReply(reply,conn);
		if(result > 0) { commit(conn);}
		else { rollback(conn);}
		
		close(conn);
		
		return result;
	}

	public int updateReply(int replyNo, String replyContent) throws Exception{
		Connection conn = getConnection();
		//크로스사이트 스크립트 방지 후 content에 set
		replyContent = XSS.replaceParameter(replyContent);
		
		//개행문자 처리 content에 set
		replyContent = replyContent.replaceAll("\r\n|\r|\n|\n\r", "<br>");
		
		int result = dao.updateReply(conn, replyNo,replyContent);
		if(result > 0) { commit(conn);}
		else { rollback(conn);}
		
		close(conn);
		
		return result;
	}

	public int deleteReply(int replyNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result =dao.deleteReply(conn, replyNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	} 
}
