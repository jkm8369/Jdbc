package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		List<AuthorVO> aList = new ArrayList<AuthorVO>();
		
		
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
			
			//컬럼에 별명 붙여주면 while에서 출력할 때 별명으로 이름 바꿔줘야 함
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
		    
			// 실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			// rs 표가 들어가있다. (사용이 불편함) --> 리스트에 담는다
			
			while(rs.next()) {
				
				// ResultSe의 데이터를 자바의 변수에 담는다
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				/*
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				*/
				
				//자바의 데이터를 VO로 묶는다
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);
				
				//VO를 리스트에 추가(add())한다
				aList.add(authorVO);
				
				
				
				
				System.out.println(authorId);
				System.out.println(authorName);
				System.out.println(authorDesc);
				
			}
			
			/*
			rs.next();
			int authorId = rs.getInt("author_id");
			String authorName = rs.getString("author_name");
			String authorDesc = rs.getString("author_desc");
			
			System.out.println(authorId);
			System.out.println(authorName);
			System.out.println(authorDesc);
			
			rs.next();
			int authorId2 = rs.getInt("author_id");
			String authorName2 = rs.getString("author_name");
			String authorDesc2 = rs.getString("author_desc");
			
			System.out.println(authorId2);
			System.out.println(authorName2);
			System.out.println(authorDesc2);
			*/
			
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
		for(int i = 0; i<aList.size(); i++) {
			System.out.println(aList.get(i).toString());
		}
		*/
		
		// 맨앞 데이터의 이름만 출력
		//System.out.println(aList.get(0).getAuthorName());
		System.out.println("-----------------------------------------");
		for(int i=0; i<aList.size(); i++) {
			int authorId = aList.get(i).getAuthorId();
			String authorName = aList.get(i).getAuthorName();
			String authorDesc = aList.get(i).getAuthorDesc();
			System.out.println(authorId + ". " + authorName + " (" + authorDesc + ")");
		}
		System.out.println("-----------------------------------------");
		
		System.out.println("==================================================");
		//--------------------------------------
		//1. 이문열 (경북영양)
		//2. 황일영 (강사)
		//--------------------------------------
		System.out.println("-----------------------------------------");
		for(int i=0; i<=1; i++) {
			aList.get(i).authorInfo();
		}
		System.out.println("-----------------------------------------");
		
	}
	
	
	
}
