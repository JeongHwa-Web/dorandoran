package org.project.my.dto;

public class Review {
	private int boardnum;
	private String truserid;
	private int grade;
	private String content;
	public Review() {
		super();
	}
	public Review(int boardnum, String truserid, int grade, String content) {
		super();
		this.boardnum = boardnum;
		this.truserid = truserid;
		this.grade = grade;
		this.content = content;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getTruserid() {
		return truserid;
	}
	public void setTruserid(String truserid) {
		this.truserid = truserid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Review [boardnum=" + boardnum + ", truserid=" + truserid + ", grade=" + grade + ", content=" + content
				+ "]";
	}
	
}
