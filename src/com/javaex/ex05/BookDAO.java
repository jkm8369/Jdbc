package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";
	
	//생성자
	public BookDAO() {
		
	}
	
	//메소드 gs
	
	//메소드 일반
	//DB연결 메소드
	private void connect() {     //메인에서는 사용하지 못함
		
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);	
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
				
	}
	
	//자원정리 메소드 - 공통
	private void close() {
		// 5. 자원정리
		try {
			
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect(); // connect 안써도 됨
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(null, ?, ?, ?, ?) ";
		    
		    // 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//5. 자원정리
		this.close();
		
		return count;
	}
	
	public int bookUpdate(String title, String pubs, int bookId) {
		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect(); // connect 안써도 됨
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += " 	   pubs = ? ";
			query += " where book_id = ? ";
		    
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setInt(3, bookId);
			
			
			
			// 실행
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count + "건이 수정되었습니다");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//5. 자원정리
		this.close();
		
		return count;
	}
	
	public int bookDelete(int bookId) {
		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect(); // connect 안써도 됨
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
		    
			// SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			// 실행
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//5. 자원정리
		this.close();
		
		return count;
	}
	
	public List<BookVO> bookSelect() {
		
		//리스트
		List<BookVO> bookList = new ArrayList<BookVO>();

		
		this.connect();
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
		    // SQL문 준비
			String query = "";
			query += " select book_id, ";
			query += " 		  title, ";
			query += " 		  pubs, ";
			query += " 		  pub_date, ";
			query += " 		  author_id ";
			query += " from book ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			
		    //실행
			rs = pstmt.executeQuery();
			
			//4.결과처리
			while(rs.next()) {
				
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				//데이터 객체로 만들기(묶기)
				BookVO bookVO = new BookVO(bookId, title, pubs, pubDate, authorId);
				
				//묶은 데이터를 리스트에 담기
				bookList.add(bookVO);
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//5. 자원정리
		this.close();
		
		return bookList;
	}
	
	//데이터 1개 가져오기
		public BookVO bookSelectOne(int bookId) {
			
			//VO
			BookVO bookVO = null;
			
			// 0. import java.sql.*;

			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
			    // SQL문 준비
				String query = "";
				query += " select book_id, ";
				query += " 		  title, ";
				query += " 		  pubs, ";
				query += " 		  pub_date, ";
				query += " 		  author_id ";
				query += " from book ";
				query += " where book_id = ? ";
				
				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookId);
				
			    //실행
				rs = pstmt.executeQuery();
				
				//4.결과처리
				rs.next();
				
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				//데이터 객체로 만들기(묶기)
				bookVO = new BookVO(id, title, pubs, pubDate, authorId);
				
					
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			//5.자원정리
			this.close();
			
			return bookVO;
			
			
		}
		
		
		public List<BookAuthorVO> bookSelectList() {
			
			List<BookAuthorVO> baList = new ArrayList<BookAuthorVO>();
			
			this.connect();
			
			try {
				
				// 3. SQL문 준비 / 바인딩 / 실행
			    // SQL문 준비
				String query = "";
				query += " select b.book_id, ";
				query += " 		  b.title, ";
				query += " 		  b.pubs, ";
				query += " 		  date_format(b.pub_date, '%Y-%m-%d') as pub_date, ";
				query += " 		  a.author_id, ";
				query += " 		  a.author_name, ";
				query += " 		  a.author_desc ";
				query += " from book b, author a ";
				query += " where b.author_id = a.author_id ";
				
				// 바인딩
				pstmt = conn.prepareStatement(query);
				
			    //실행
				rs = pstmt.executeQuery();
				
				//4.결과처리
				while(rs.next()) {
					
					int bookId = rs.getInt("b.book_id");
					String bookTitle = rs.getString("b.title");
					String bookPubs = rs.getString("b.pubs");
					String bookPubDate = rs.getString("pub_date");
					int authorId = rs.getInt("a.author_id");
					String authorName = rs.getString("a.author_name");
					String authorDesc = rs.getString("a.author_desc");
					
					//데이터 객체로 만들기(묶기)
					BookAuthorVO bookAuthorVO = new BookAuthorVO(bookId, bookTitle, bookPubs, bookPubDate, authorId, authorName, authorDesc);
					
					//묶은 데이터를 리스트에 담기
					baList.add(bookAuthorVO);
					
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			//5. 자원정리
			this.close();
			
			return baList;
			
		}
		
	

	
}
