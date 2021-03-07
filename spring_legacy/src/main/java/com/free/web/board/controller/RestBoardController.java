package com.free.web.board.controller;



import java.util.HashMap;
/*
 * @ResponseBody 를 method앞에 붙이면 비동기 화 (spring 3.X version)
 * @RestController (spring 4.X version) -> class위에 사용하는 annotation.
 */
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.free.web.board.model.ReplyVO;
import com.free.web.board.service.BoardService;

@RestController
@RequestMapping(value = "/restBoard")
public class RestBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST)
	public List<ReplyVO> getReplyList(@RequestParam("bid") int bid) throws Exception{
		return boardService.getReplyList(bid);
	}
	
	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody ReplyVO replyVO) throws Exception{
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.saveReply(replyVO);
			result.put("status","OK");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status","False");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/updateReply", method = {RequestMethod.GET, RequestMethod.POST})

	public Map<String, Object> updateReply(@RequestBody ReplyVO replyVO) throws Exception {

	Map<String, Object> result = new HashMap<>();
	try {
			boardService.updateReply(replyVO);
			result.put("status", "OK");
	} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
	return result;
	}
	
	@RequestMapping(value = "/deleteReply", method = {RequestMethod.GET, RequestMethod.POST})

	public Map<String, Object> deleteReply(@RequestParam("rid") int rid) throws Exception {

	Map<String, Object> result = new HashMap<>();
	try {
		boardService.deleteReply(rid);
		result.put("status", "OK");

	} catch (Exception e) {
		e.printStackTrace();
		result.put("status", "False");
	}
	return result;
	}



}
