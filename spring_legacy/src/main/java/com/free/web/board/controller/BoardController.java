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
		
		// search 객체 생성 -> search 객체가 pagination을 상속받았기때문에 pagination대신에 search객체로 사용한다.
		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		//전체 글 갯수
		int listCnt = boardService.getBoardListCnt();
		
		// Pagination 객체 생성
		Pagination pagination = new Pagination();
		//pagination.pageInfo(page, range, listCnt);
		search.pageInfo(page, range, listCnt);	//search 객체가 pagination을 상속받았기때문에 pagination대신에 search객체로 사용한다.
		
		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		return "board/index";
	}

	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "board/boardForm";
	}
	
	/*
	 * @ModelAttribute 넘겨주는 값을 vo에 매칭시켜서 데이터를 받아오게 함, name값은 boardVO와 연결시킬 수 있도록 동일한 값으로 설정한다.
	 * RedirectAttributes 는 완료후 redirect을 위해서 특히 뒤로가기 버튼을 클릭했을때를 고려함. -> 자동으로 글을 도배하기를 막기위한것.
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
	 * service 받아온 데이터를 addAttribute를 이용해서 view으로 보냄
	 */
	
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		model.addAttribute("boardContent",boardService.getBoardContent(bid));
		model.addAttribute("replyVO", new ReplyVO());
		return "board/boardContent";
				
	}
	/*
	 * 개발자는 Model  객체를 만들 필요가 없다 -> spring에서 자기가 model 객체는 만들어줌.
	 * 데이터 수정 부분
	 * boardVO는 입력폼과 연계를 하기 위해서 데이터 보내주는 역활
	 * 일단은 입력 폼으로 보낸다.
	 */
	@RequestMapping(value = "/editForm", method = RequestMethod.GET)
	public String editForm(@RequestParam("bid") int bid, @RequestParam("mode") String mode, Model model) throws Exception{
		model.addAttribute("boardContent",boardService.getBoardContent(bid));
		model.addAttribute("mode",mode);
		model.addAttribute("boardVO",new BoardVO());
		return "board/boardForm";
	}
	
	
	/*
	 * 게시물 delete 
	 */
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(RedirectAttributes rttr, @RequestParam("bid") int bid)throws Exception{
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}
	
	
	/*
	 * @ExceptionHandler를 이용한 예외처리
	 
	@ExceptionHandler(RuntimeException.class)
	public String exceptionHandler(Model model, Exception e) {
		logger.info("exception :" + e.getMessage());
		model.addAttribute("exception",e);
		return "error/exception";
	}
	*/
}	
