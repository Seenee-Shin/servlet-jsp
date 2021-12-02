package edu.kh.semi.member.model.dao;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.vo.Member;

//DAO : Datbase Access Object

public class MemberDAO {
	
	//JDBC 객체 참조변수 
	 private Statement stmt = null;
	 private PreparedStatement pstmt = null;
	 private ResultSet rs = null;

	 
	 //XML에 작성된 SQL을 저장할 Collection참조변수 선언 
	 private Properties prop; //key, value의 자료형이 모두 String인 Map
	 
	 
	 public MemberDAO() {
		 
		 //DAO 객체 생성 시 특정 위치의 XML파일을 prop에 저장
		 try {
	         prop = new Properties();
	         
	         String filePath 
	         = MemberDAO.class.getResource(""
	         		+ "/edu/kh/semi/sql/member-query.xml").getPath();     
	         // ->sql이 작성된 XML파일의 경로
	      
	         
	         prop.loadFromXML( new FileInputStream( filePath ) );
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	}


	/** login DAO
	 * @param memberId
	 * @param memberPw
	 * @param conn
	 * @return loginMember/ null when it's failed
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw, Connection conn) throws Exception{

		//create instance for saving result  
		Member loginMember = null;
		
		try {
			//2) get  sql
			String sql = prop.getProperty("login");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,memberId);
			pstmt.setString(2,memberPw);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberId(memberId);
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setMemberPhone(rs.getString("MEMBER_PHONE"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setEnrollDate(rs.getDate("ENROLL_DT"));
				loginMember.setStatusCode(rs.getInt("STATUS_CD"));
				loginMember.setGradeCode(rs.getInt("GRADE_CD"));
				
			}
			
			
		}finally{
			//return resource
			close(rs);
			close(pstmt);
			
		}
		
		return loginMember;
	}


	/** sign up dao
	 * @param member
	 * @param conn
	 * @return result 1 
	 * @throws Exception
	 */
	public int singup(Member member, Connection conn) throws Exception {
		int result = 0;
		
		try {
			// sql얻어오기 
			String sql = prop.getProperty("signup");
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			
			result= pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	 
	
	 
}