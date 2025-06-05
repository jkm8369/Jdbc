package com.javaex.ex03;

import java.util.List;

//메인 프로그램
public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDao = new AuthorDAO();
		
		/*
		int count = authorDao.authorInsert("주호민", "웹툰 작가");
		if(count>0) {
			System.out.println("등록되었습니다.");
		} else if(count<0) {
			System.out.println("알수없는 오류 발생");
		} else {
			System.out.println("등록되지 않았습니다.");
		}
		*/
		
		/*
		authorDao.authorInsert("김영하", "알쓸신잡 출연");
		authorDao.authorInsert("기안840", "방송대상 수상");
		*/
		
		//int count = authorDao.authorDelete(9);
		
		//int count = authorDao.authorUpdate(2, "한강", "노벨상수상");
		
		List<AuthorVO> authorList = authorDao.authorList();
		
		//사용자용 출력화면
		for(int i=0; i<authorList.size(); i++) {
			AuthorVO authorVO = authorList.get(i);
			System.out.println(authorVO.getAuthorId() + ". " + authorVO.getAuthorName() + "(" + authorVO.getAuthorDesc() + ")");
		}
		
		/*
		1. 김문열 (경북 영양)
		2. 한강 (노벨상 수상)
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
