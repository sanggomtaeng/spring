package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	// 전체조회
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boardList" , list);
		return "board/list";
	}
	
	// 단건조회
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/info";
	}
	
	// 등록
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "board/insert";
	}
	
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		int bno = boardService.boardInsert(boardVO);
		String url = null;
		if(bno > -1) {
			url = "redirect:boardInfo?bno=" + bno;
		}else {
			url = "redirect:boardList";
		}
		return url;
	}
	
	// 수정
	@GetMapping("boardUpdate")
	public String boardUpdateForm(Integer bno, Model model) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(bno);
		
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/update";
	}
	
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(BoardVO boardVO) {
		return boardService.boardUpdate(boardVO);
	}
	
	// 삭제
	@GetMapping("boardDelete")
	public String boardDelete(BoardVO boardVO) {
		boardService.boardDelete(boardVO);
		return "redirect:boardList";
	}
}
