package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDAO authorDAO = new AuthorDAO();
		
		
		
		//int c01 = authorDAO.authorInsert("김종국", "런닝맨1기");
		
		//int c02 = authorDAO.authorUpdate(10, "조세호", "유퀴즈");
		
		//int c03 = authorDAO.authorDelete(10);
		
		List<AuthorVO> authorList = authorDAO.authorSelect();
		System.out.println(authorList);
		
		AuthorVO authorVO = authorDAO.authorSelectOne(9);
		System.out.println(authorVO);
	}

}
