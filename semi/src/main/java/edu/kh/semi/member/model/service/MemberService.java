package edu.kh.semi.member.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;

//Sevice 데이터 가공, 트랜잭션 제어 (commit, rollback , connection 연결)
public class MemberService {
	 private MemberDAO dao = new MemberDAO();

	/** login service
	 * @param memberId
	 * @param memberPw
	 * @return loginMember failed= return null 
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		// 1) get Connection
		Connection conn = getConnection();
						// rent a Connection created in DBCP  
		
		// 2) return result from DAO()
		Member loginMember = dao.login(memberId, memberPw, conn);
		
		//3) close Connection after use
		close(conn);
		
		//4) return final result
		return loginMember;
	}

	/** sign up service
	 * @param member
	 * @return result 1 
	 * @throws Exception
	 */
	public int signup(Member member) throws Exception{
		Connection conn = getConnection();
		
		//2)회원가입 수행 후 졀과 반환 
		int result = dao.singup(member, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
}