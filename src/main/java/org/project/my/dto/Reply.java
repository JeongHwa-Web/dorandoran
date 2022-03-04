package org.project.my.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

public class Reply {
	private int replynum;
	private int boardnum;
	private String userid;
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date regdate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date modidate;
	
	private String ip;
	private String nickname;
	public Reply() {
		super();
	}
	public Reply(int replynum, int boardnum, String userid, String content, Date regdate, Date modidate, String ip,
			String nickname) {
		super();
		this.replynum = replynum;
		this.boardnum = boardnum;
		this.userid = userid;
		this.content = content;
		this.regdate = regdate;
		this.modidate = modidate;
		this.ip = ip;
		this.nickname = nickname;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Reply [replynum=" + replynum + ", boardnum=" + boardnum + ", userid=" + userid + ", content=" + content
				+ ", regdate=" + regdate + ", modidate=" + modidate + ", ip=" + ip + ", nickname=" + nickname + "]";
	}
	
}
