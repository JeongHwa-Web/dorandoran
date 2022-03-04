package org.project.my.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int boardnum;
	private String wruserid;
	private String title;
	private String content;
	private int readcnt;
	private String truserid;
	private String ip;
	private int review;
	private Date regdate;
	private Date modidate;
	private String nickname;
	private String filename;
	private List<MultipartFile> files;
	public Board() {
		super();
	}
	public Board(int boardnum, String wruserid, String title, String content, int readcnt, String truserid, String ip,
			int review, Date regdate, Date modidate, String nickname, String filename, List<MultipartFile> files) {
		super();
		this.boardnum = boardnum;
		this.wruserid = wruserid;
		this.title = title;
		this.content = content;
		this.readcnt = readcnt;
		this.truserid = truserid;
		this.ip = ip;
		this.review = review;
		this.regdate = regdate;
		this.modidate = modidate;
		this.nickname = nickname;
		this.filename = filename;
		this.files = files;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getWruserid() {
		return wruserid;
	}
	public void setWruserid(String wruserid) {
		this.wruserid = wruserid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getTruserid() {
		return truserid;
	}
	public void setTruserid(String truserid) {
		this.truserid = truserid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModidate() {
		return modidate;
	}
	public void setModidate(Date modidate) {
		this.modidate = modidate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "Board [boardnum=" + boardnum + ", wruserid=" + wruserid + ", title=" + title + ", content=" + content
				+ ", readcnt=" + readcnt + ", truserid=" + truserid + ", ip=" + ip + ", review=" + review + ", regdate="
				+ regdate + ", modidate=" + modidate + ", nickname=" + nickname + ", filename=" + filename + ", files="
				+ files + "]";
	}
	
}
