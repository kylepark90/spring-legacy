package com.free.web.board.service;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.free.common.Pagination;
import com.free.common.Search;
import com.free.web.board.dao.BoardDAO;
import com.free.web.board.model.BoardVO;
import com.free.web.board.model.ReplyVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDAO;

	public List<BoardVO> getBoardList(Search search) throws Exception {
		return boardDAO.getBoardList(search);
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}
	@Transactional
	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		// boardDAO.updateViewCnt(bid);
		return boardDAO.getBoardContent(bid);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardDAO.updateBoard(boardVO);
		
	}

	@Override
	public void deleteBoard(int bid) throws Exception {
		boardDAO.deleteBoard(bid);
		
	}

	@Override
	public int getBoardListCnt() throws Exception {
		return boardDAO.getBoardListCnt();
	}

	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		return boardDAO.getReplyList(bid);
	}

	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		return boardDAO.saveReply(replyVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		return boardDAO.updateReply(replyVO);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		return boardDAO.deleteReply(rid);
	}

}

