package org.project.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.project.my.dao.BoardDAO;
import org.project.my.dao.BoardFileDAO;
import org.project.my.dao.ReviewDAO;
import org.project.my.dto.Board;
import org.project.my.dto.BoardFile;
import org.project.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private BoardFileDAO boardFileDAO;
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Override
	@Transactional
	public Map<String, Object> insert(Board board) throws Exception {
		boardDAO.insert(board);
		//게시물 파일 저장
		List<MultipartFile>files = board.getFiles();
		if(files != null) {
			for(MultipartFile file:files) {	
				String filename = fileService.fileUpLoad(file);
				if(!filename.equals("")) {					
					BoardFile boardFile = new BoardFile();
					boardFile.setFilename(filename);
					boardFile.setBoardnum(board.getBoardnum());
					boardFileDAO.insert(boardFile);
				}
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "등록완료");
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> update(Board board, List<Integer>removeFile) throws Exception {
		boardDAO.update(board);
		//게시물 파일 삭제
		if(removeFile != null) {
			for(int i=0;i<removeFile.size();i++) {	
				boardFileDAO.delete(removeFile.get(i));
			}
		}
		//게시물 파일들 저장
		List<MultipartFile>files = board.getFiles();
		if(files != null) {
			for(MultipartFile file:files) {	
				String filename = fileService.fileUpLoad(file);
				if(!filename.equals("")) {					
					BoardFile boardFile = new BoardFile();
					boardFile.setFilename(filename);
					boardFile.setBoardnum(board.getBoardnum());
					boardFileDAO.insert(boardFile);
				}
			}
		}
		Map<String, Object>result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "수정완료");
		return result;
	}

	@Override
	public Map<String, Object> delete(int boardnum) {
		boardDAO.delete(boardnum);
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "삭제되었습니다.");
		result.put("code", 0);
		return result;
	}

	@Override
	public Map<String, Object> selectOne(int boardnum) {
		Board board = boardDAO.selectOne(boardnum);
		//평점평균 조회
		int gradeAvg;
		if(reviewDAO.selectOne(boardnum)==null) {
			gradeAvg = 0; //리뷰가 없을경우
		}else {			
			gradeAvg = reviewDAO.gradeAvg(board.getWruserid());
		}
		//게시물 파일 조회
		List<BoardFile> files = boardFileDAO.selectList(boardnum);
		Map<String, Object>result = new HashMap<>();
		result.put("board", board);
		result.put("files", files);
		result.put("gradeAvg", gradeAvg);
		return result;
	}

	@Override
	public List<Board> selectList(Page page) {
		int curpage = page.getCurpage(); //현재 페이지
		int perpage = page.getPerpage(); //한페이지당 게시글수
		int perblock = page.getPerblock(); //보이는 페이지수
		
		int totcnt = boardDAO.selectTotCnt(page); //전체 게시글수
		//전체 페이지수
		int totpage = totcnt/perpage;
		if(totcnt % perpage != 0) {
			totpage++;
		}
		page.setTotpage(totpage);
		int startnum = (curpage - 1) * perpage; //게시글 시작번호
		page.setStartnum(startnum);
		int endnum = startnum + perpage - 1; //게시글 끝번호
		page.setEndnum(endnum);
		int startpage = curpage - ((curpage - 1) % perblock); //페이지 시작번호
		page.setStartpage(startpage);
		int endpage = startpage + perblock - 1; //페이지 끝번호
		if(endpage > totpage) {
			endpage = totpage;
		}
		page.setEndpage(endpage);
		//게시물 리스트에서 표시될 사진
		List<Board> blist = boardDAO.selectList(page);
		for (Board board : blist) {
			List<BoardFile> files = boardFileDAO.selectList(board.getBoardnum());
			if(files.size()!=0) {				
				BoardFile file = files.get(0);
				board.setFilename(file.getFilename());
			}
		}
		return blist;
	}

	@Override
	public Map<String, Object> tradeSuccess(Board board) {
		Board dbboard = boardDAO.selectOne(board.getBoardnum());
		String truserid = dbboard.getTruserid();
		Map<String, Object> result = new HashMap<>();
		//게시물이 이미 거래완료된경우
		if(truserid!=null) {
			result.put("msg","이미 처리되었습니다.");
			result.put("code", 1);
			return result;
		}
		boardDAO.tradeSuccess(board);
		result.put("msg", "거래완료 되었습니다.");
		result.put("code", 0);
		return result;
	}

	@Override
	public List<Board> buyList(String truserid) {
		return boardDAO.buyList(truserid);
	}

	@Override
	public List<Board> sellList(String wruserid) {
		return boardDAO.sellList(wruserid);
	}

	@Override
	public void updateReadCnt(int boardnum) {
		boardDAO.updateReadCnt(boardnum);
	}
	
	
	
}
