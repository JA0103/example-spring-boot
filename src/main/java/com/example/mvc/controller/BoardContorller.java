package com.example.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvc.domain.Board;
import com.example.mvc.repository.BoardRepository;
import com.example.mvc.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "게시판 API", description = "게시판 관련 기능")
public class BoardContorller {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 목록
	 * @return
	 */
	@GetMapping
	@Operation(summary = "목록 조회", description = "게시물 목록 정보를 조회할 수 있습니다.")
	public List<Board> getList(){
		return boardService.getList();
	}

	/**
	 * 상세정보
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	@Operation(summary = "상세 조회", description = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@Parameter(name = "boardSeq", description = "게시물 번호", example = "1")
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
