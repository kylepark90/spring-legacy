package com.free.web.board.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.free.common.Pagination;
import com.free.common.Search;
import com.free.web.board.model.BoardVO;
import com.free.web.board.model.ReplyVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		return sqlSession.selectList("com.free.web.board.boardMapper.getBoardList", search);
	}

	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		return sqlSession.selectOne("com.free.web.board.boardMapper.getBoardContent", bid);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		return sqlSession.insert("com.free.web.board.boardMapper.insertBoard", boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return sqlSession.update("com.free.web.board.boardMapper.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSession.delete("com.free.web.board.boardMapper.deleteBoard", bid);
	}

	@Override
	public int updateViewCnt(int bid) throws Exception {
		return sqlSession.update("com.free.web.board.boardMapper.updateViewCnt", bid);
	}

	@Override
	public int getBoardListCnt() throws Exception {
		return sqlSession.selectOne("com.free.web.board.boardMapper.getBoardListCnt");
	}

	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		return sqlSession.selectList("com.free.web.board.replyMapper.getReplyList",bid);
	}

	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		return sqlSession.insert("com.free.web.board.replyMapper.saveReply", replyVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		return sqlSession.update("com.free.web.board.replyMapper.updateReply", replyVO);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		return sqlSession.delete("com.free.web.board.replyMapper.deleteReply", rid);
	}

}

