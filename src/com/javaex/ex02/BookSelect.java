package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BookVO> bList = new ArrayList<BookVO>();
		
		
		try {
		    // 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

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
			
		    // 4.결과처리
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				BookVO bookVO = new BookVO(bookId, title, pubs, pubDate, authorId);
				bList.add(bookVO);
				
				
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
		
		/*
		for(int i=0; i<bList.size(); i++) {
			int bookId = bList.get(i).getBookId();
			String title = bList.get(i).getTitle();
			String pubs = bList.get(i).getPubs();
			String pubDate = bList.get(i).getPubDate();
			int authorId = bList.get(i).getAuthorId();
			
			System.out.println(bookId + "." + title + "\t <" + pubs + ">\t (" + pubDate + ") \t" + authorId);
		}
		*/
		
		for(int i=0; i<bList.size(); i++) {
			bList.get(i).bookInfo();
		}
	}

}
