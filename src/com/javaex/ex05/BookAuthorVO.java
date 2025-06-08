package com.javaex.ex05;

public class BookAuthorVO {

	//필드
		private int bookId;
		private String bookTitle;
		private String bookPubs;
		private String bookPubDate;
		private int authorId;
		private String authorName;
		private String authorDesc;
		
		//생성자
		public BookAuthorVO() {
			super();
		}

		public BookAuthorVO(int bookId, String bookTitle, String bookPubs, String bookPubDate, int authorId,
				String authorName, String authorDesc) {
			super();
			this.bookId = bookId;
			this.bookTitle = bookTitle;
			this.bookPubs = bookPubs;
			this.bookPubDate = bookPubDate;
			this.authorId = authorId;
			this.authorName = authorName;
			this.authorDesc = authorDesc;
		}

		//메소드 gs
		public int getBookId() {
			return bookId;
		}

		public void setBookId(int bookId) {
			this.bookId = bookId;
		}

		public String getBookTitle() {
			return bookTitle;
		}

		public void setBookTitle(String bookTitle) {
			this.bookTitle = bookTitle;
		}

		public String getBookPubs() {
			return bookPubs;
		}

		public void setBookPubs(String bookPubs) {
			this.bookPubs = bookPubs;
		}

		public String getBookPubDate() {
			return bookPubDate;
		}

		public void setBookPubDate(String bookPubDate) {
			this.bookPubDate = bookPubDate;
		}

		public int getAuthorId() {
			return authorId;
		}

		public void setAuthorId(int authorId) {
			this.authorId = authorId;
		}

		public String getAuthorName() {
			return authorName;
		}

		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}

		public String getAuthorDesc() {
			return authorDesc;
		}

		public void setAuthorDesc(String authorDesc) {
			this.authorDesc = authorDesc;
		}

		//메소드 일반
		@Override
		public String toString() {
			return "BookAuthorVO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPubs=" + bookPubs
					+ ", bookPubDate=" + bookPubDate + ", authorId=" + authorId + ", authorName=" + authorName
					+ ", authorDesc=" + authorDesc + "]";
		}
		
		
}
