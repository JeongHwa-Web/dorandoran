package org.project.my.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String userid;
	private String passwd;
	private String nickname;
	private String email;
	private String name;
	private String filename;
	private int simplejoin;
	private String callnum;
	private Date regdate;
	private MultipartFile file;
	public Member() {
		super();
	}
	public Member(String userid, String passwd, String nickname, String email, String name, String filename,
			int simplejoin, String callnum, Date regdate, MultipartFile file) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.nickname = nickname;
		this.email = email;
		this.name = name;
		this.filename = filename;
		this.simplejoin = simplejoin;
		this.callnum = callnum;
		this.regdate = regdate;
		this.file = file;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getSimplejoin() {
		return simplejoin;
	}
	public void setSimplejoin(int simplejoin) {
		this.simplejoin = simplejoin;
	}
	public String getCallnum() {
		return callnum;
	}
	public void setCallnum(String callnum) {
		this.callnum = callnum;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", passwd=" + passwd + ", nickname=" + nickname + ", email=" + email
				+ ", name=" + name + ", filename=" + filename + ", simplejoin=" + simplejoin + ", callnum=" + callnum
				+ ", regdate=" + regdate + ", file=" + file + "]";
	}
	
}
