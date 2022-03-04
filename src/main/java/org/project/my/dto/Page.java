package org.project.my.dto;

public class Page {
	private String findkey;
	private String findvalue;
	
	private int curpage = 1; //현재페이지
	private int perpage = 5; //한페이지당 게시물수
	private int perblock = 10; //페이지수
	
	private int totpage; //전체페이지
	private int startnum; //시작번호
	private int endnum; //끝번호
	private int startpage; //시작페이지
	private int endpage; //끝페이지
	public Page() {
		super();
	}
	public Page(String findkey, String findvalue, int curpage, int perpage, int perblock, int totpage, int startnum,
			int endnum, int startpage, int endpage) {
		super();
		this.findkey = findkey;
		this.findvalue = findvalue;
		this.curpage = curpage;
		this.perpage = perpage;
		this.perblock = perblock;
		this.totpage = totpage;
		this.startnum = startnum;
		this.endnum = endnum;
		this.startpage = startpage;
		this.endpage = endpage;
	}
	public String getFindkey() {
		return findkey;
	}
	public void setFindkey(String findkey) {
		this.findkey = findkey;
	}
	public String getFindvalue() {
		return findvalue;
	}
	public void setFindvalue(String findvalue) {
		this.findvalue = findvalue;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public int getPerblock() {
		return perblock;
	}
	public void setPerblock(int perblock) {
		this.perblock = perblock;
	}
	public int getTotpage() {
		return totpage;
	}
	public void setTotpage(int totpage) {
		this.totpage = totpage;
	}
	public int getStartnum() {
		return startnum;
	}
	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}
	public int getEndnum() {
		return endnum;
	}
	public void setEndnum(int endnum) {
		this.endnum = endnum;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	@Override
	public String toString() {
		return "Page [findkey=" + findkey + ", findvalue=" + findvalue + ", curpage=" + curpage + ", perpage=" + perpage
				+ ", perblock=" + perblock + ", totpage=" + totpage + ", startnum=" + startnum + ", endnum=" + endnum
				+ ", startpage=" + startpage + ", endpage=" + endpage + "]";
	}
	
}
