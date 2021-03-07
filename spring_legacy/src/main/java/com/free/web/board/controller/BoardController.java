package com.free.web.board.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.free.common.Pagination;
import com.free.common.Search;
import com.free.web.board.model.BoardVO;
import com.free.web.board.model.ReplyVO;
import com.free.web.board.service.BoardService;



@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	//logger
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model
			, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range
			, @RequestParam(required = false, defaultValue = "title") String searchType
			, @RequestParam(required = false) String keyword
			) throws Exception {
		
		// search ��ü ���� -> search ��ü�� pagination�� ��ӹ޾ұ⶧���� pagination��ſ� search��ü�� ����Ѵ�.
		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		//��ü �� ����
		int listCnt = boardService.getBoardListCnt();
		
		// Pagination ��ü ����
		Pagination pagination = new Pagination();
		//pagination.pageInfo(page, range, listCnt);
		search.pageInfo(page, range, listCnt);	//search ��ü�� pagination�� ��ӹ޾ұ⶧���� pagination��ſ� search��ü�� ����Ѵ�.
		
		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		return "board/index";
	}

	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "board/boardForm";
	}
	
	/*
	 * @ModelAttribute �Ѱ��ִ� ���� vo�� ��Ī���Ѽ� �����͸� �޾ƿ��� ��, name���� boardVO�� �����ų �� �ֵ��� ������ ������ �����Ѵ�.
	 * RedirectAttributes �� �Ϸ��� redirect�� ���ؼ� Ư�� �ڷΰ��� ��ư�� Ŭ���������� �����. -> �ڵ����� ���� �����ϱ⸦ �������Ѱ�.
	 */
	
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO,
			@RequestParam("mode") String mode,
			RedirectAttributes rttr) throws Exception {
		
		if(mode.equals("edit")) {
			boardService.updateBoard(boardVO);
		}else {
			boardService.insertBoard(boardVO);
		}
		
		return "redirect:/board/getBoardList";
	}
	
	/*
	 * service �޾ƿ� �����͸� addAttribute�� �̿��ؼ� view���� ����
	 */
	
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		model.addAttribute("boardContent",boardService.getBoardContent(bid));
		model.addAttribute("replyVO", new ReplyVO());
		return "board/boardContent";
				
	}
	/*
	 * �����ڴ� Model  ��ü�� ���� �ʿ䰡 ���� -> spring���� �ڱⰡ model ��ü�� �������.
	 * ������ ���� �κ�
	 * boardVO�� �Է����� ���踦 �ϱ� ���ؼ� ������ �����ִ� ��Ȱ
	 * �ϴ��� �Է� ������ ������.
	 */
	@RequestMapping(value = "/editForm", method = RequestMethod.GET)
	public String editForm(@RequestParam("bid") int bid, @RequestParam("mode") String mode, Model model) throws Exception{
		model.addAttribute("boardContent",boardService.getBoardContent(bid));
		model.addAttribute("mode",mode);
		model.addAttribute("boardVO",new BoardVO());
		return "board/boardForm";
	}
	
	
	/*
	 * �Խù� delete 
	 */
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(RedirectAttributes rttr, @RequestParam("bid") int bid)throws Exception{
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}
	
	
	/*
	 * @ExceptionHandler�� �̿��� ����ó��
	 
	@ExceptionHandler(RuntimeException.class)
	public String exceptionHandler(Model model, Exception e) {
		logger.info("exception :" + e.getMessage());
		model.addAttribute("exception",e);
		return "error/exception";
	}
	*/
}	
