package edu.kh.practice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDAO {
	   private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	   private Connection conn;
	   private PreparedStatement pstmt;
	   private ResultSet rs;

	   // DBCP
	   public BookDAO() {
	      try {
	         Context initContext = new InitialContext();
	         Context envContext = (Context) initContext.lookup("java:/comp/env");
	         ds = (DataSource) envContext.lookup("jdbc/oracle");
	      } catch (NamingException e) {
	         e.printStackTrace();
	      }
	   }

	   private void close() {
	      try {
	         if (rs != null) {
	            rs.close();
	            rs = null;
	         }

	         if (pstmt != null) {
	            pstmt.close();
	            pstmt = null;
	         }

	         if (conn != null) {
	            conn.close();
	            conn = null;
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

	public int bookRegister(Book book) throws SQLException {
		int result = 0;
		
		try {
			String sql = "INSERT INTO BOOK VALUES(?,?,?,?,DEFAULT)";
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookAuthor());
			pstmt.setInt(3, book.getBookPrice());
			pstmt.setInt(4, book.getSale());
			
			result = pstmt.executeUpdate();
		
			
		}finally {
			close();
		}
		return result;
	}

	public List<Book> selectBookList() throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			String sql = "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE, SALE FROM BOOK";
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setBookTitle(rs.getString("BOOK_TITLE"));
				book.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				book.setBookPrice(rs.getInt("BOOK_PRICE"));
				book.setSale(rs.getInt("SALE"));

				//list 추가
				bookList.add(book);
			}
		}finally {
			close();
		}
		return bookList;
	}
}
