package edu.kh.servlet.model.service;
import static edu.kh.servlet.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.servlet.model.dao.MemberDAO;
import edu.kh.servlet.vo.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/** 회원가입
	 * @param member
	 * @return int result (1 = 성공) 
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		int result =0; 
		
		//connection 얻어오기 
		Connection conn = getConnection();
				
			//DAO호출 후 결과 반환 
			result = dao.signUP(conn, member);
			
			//반환 받은 결과를 따라서 트랜잭션 제어 
			if (result >0) {
				commit(conn);
			}else {
				rollback(conn);
			}

			close(conn);
		//사용한 Connection 반환 
		return result;
	}
}
