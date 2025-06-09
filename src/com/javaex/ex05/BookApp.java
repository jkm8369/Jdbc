package com.javaex.ex05;

import java.util.List;

public class BookApp {
	
	public static void main(String[] args) {
	
		//book테이블만
		BookDAO bookDAO = new BookDAO();
		
		//List<BookVO> bookList = bookDAO.bookSelect();
		//System.out.println(bookList);
		
		//BookVO bookVO = bookDAO.bookSelectOne(7);
		//System.out.println(bookVO);
		
		
		
		
		//-- bookVO
		
		
		//bookDAO.bookInsert();
		//bookDAO.bookUpdate();
		//bookDAO.bookDelete(16);
		//bookDAO.bookSelect();
		//bookDAO.bookSelectOne();
		
		
		
		//---
		//--BookAuthorVO   book,author 테이블 조인
		List<BookAuthorVO> baList = bookDAO.bookSelectList();
		System.out.println(baList);
		
		
		
		
		
		
		
		
	}

	
}
