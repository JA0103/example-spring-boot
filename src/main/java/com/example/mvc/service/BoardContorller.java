package com.example.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvc.controller.BoardService;
import com.example.mvc.domain.Board;
import com.example.mvc.repository.BoardRepository;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * 게시판 컨트롤러
 */
/**
 * 
 */
@Slf4j
@RestController
@RequestMapping("/board")
public class BoardContorller {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 목록
	 * @return
	 */
	@GetMapping
	public List<Board> getList(){
		return boardService.getList();
	}

	/**
	 * 상세정보
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	
	/**
	 * 등록 / 수정
	 * @param board
	 */
	@GetMapping("/save")
	public int save(Board param) {
		boardService.save(param);
		return param.getBoardSeq();
	}
	
	/**
	 * 삭제
	 * @param boardSeq
	 */
	@GetMapping("/delete/{boardSeq}")
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
}
