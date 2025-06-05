package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		List<BookAuthorVO> baList = new ArrayList<BookAuthorVO>();
		try {
		    // 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

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
			
			// 실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			
			while(rs.next()) {
				
				int bookId = rs.getInt("b.book_id");
				String bookTitle = rs.getString("b.title");
				String bookPubs = rs.getString("b.pubs");
				String bookPubDate = rs.getString("pub_date");
				int authorId = rs.getInt("a.author_id");
				String authorName = rs.getString("a.author_name");
				String authorDesc = rs.getString("a.author_desc");
				
				/*
				확인
				System.out.println(bookId);
				System.out.println(bookTitle);
				System.out.println(bookPubs);
				System.out.println(bookPubDate);
				System.out.println(authorId);
				System.out.println(authorName);
				System.out.println(authorDesc);
				System.out.println("-----------------------------------------");
				*/
				
				
				BookAuthorVO bookAuthorVO = new BookAuthorVO(bookId, bookTitle, bookPubs, bookPubDate, 
															 authorId, authorName, authorDesc);
				
				baList.add(bookAuthorVO);
				
			
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
		for(int i=0; i<baList.size(); i++) {
			//baList.get(i).bookAuthorInfo();
			BookAuthorVO baVO = baList.get(i);
			System.out.println(baVO.getBookId() + "." + baVO.getBookTitle() + "," + baVO.getAuthorName());
		}
		*/
		
		for(BookAuthorVO baVO : baList) {
			System.out.println(baVO.getBookId() + "." + baVO.getBookTitle() + "," + baVO.getAuthorName());
		}
		
		for(int i=0; i<baList.size(); i++) {
			baList.get(i).bookAuthorInfo();
		}
		
		
	}

}
