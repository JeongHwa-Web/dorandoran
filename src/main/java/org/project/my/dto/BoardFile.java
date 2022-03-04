package org.project.my.dto;

public class BoardFile {
	private int photonum;
	private int boardnum;
	private String filename;
	public BoardFile(int photonum, int boardnum, String filename) {
		super();
		this.photonum = photonum;
		this.boardnum = boardnum;
		this.filename = filename;
	}
	public BoardFile() {
		super();
	}
	public int getPhotonum() {
		return photonum;
	}
	public void setPhotonum(int photonum) {
		this.photonum = photonum;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "BoardFile [photonum=" + photonum + ", boardnum=" + boardnum + ", filename=" + filename + "]";
	}
	
}
