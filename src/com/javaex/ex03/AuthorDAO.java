package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//MySQL 전문가
public class AuthorDAO {

	
	// 생성자
	public AuthorDAO() {

	}
	
	

	// 메소드 일반

	// 작가 등록
	public int authorInsert(String name, String desc) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values (null, ?, ?) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name); // 메소드의 파라미터
			pstmt.setString(2, desc); // 메소드의 파라미터

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
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
		return count;
	}

	// 작가 수정
	public int authorUpdate(int authorId, String name, String desc) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += " 	   author_desc = ? ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);   //메소드 파라미터
			pstmt.setString(2, desc);   //메소드 파라미터
			pstmt.setInt(3, authorId);  //메소드 파라미터

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정 되었습니다");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
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
		return count;
	}

	// 작가 삭제
	public int authorDelete(int authorId) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
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
		return count;
	}

	// 작가 리스트
	public List<AuthorVO> authorList() {
		
		//리스트
		List<AuthorVO> authorList = new ArrayList<AuthorVO>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

		    // 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select author_id, ";
			query += " 		  author_name, ";
			query += " 		  author_desc ";
			query += " from author ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
		    
			// 실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리 (java 리스트로 만든다)
			while(rs.next()) {
				
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				// 데이터 객체로 만들기 (묶기)
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);
				
				//묶은 데이터를 리스트에 달기
				authorList.add(authorVO);
			}
			
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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
		
		return authorList;  //리스트 (주소)를 리턴한다
		
		
		
	}

}
