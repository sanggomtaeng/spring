package com.yedam.app.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int boardInsert(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		
		return result == 1 ? boardVO.getBno() : -1;
	}

	@Override
	public Map<String, Object> boardUpdate(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = boardMapper.updateBoardInfo(boardVO.getBno(), boardVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", boardVO);
		return map;
	}

	@Override
	public Map<String, Object> boardDelete(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = boardMapper.deleteBoardInfo(boardVO.getBno());
		
		if(result == 1) {
			map.put("bno", boardVO.getBno());
		}
		return map;
	}
	
	
}
