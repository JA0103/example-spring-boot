package com.example.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.mvc.domain.Board;
import com.example.mvc.repository.BoardRepository;

/**
 * 게시판 서비스
 */
/**
 * 
 */
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	/**
	 * 목록
	 * @return
	 */
	public List<Board> getList(){
		return repository.getList();
	}

	/**
	 * 상세정보
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	/**
	 * 등록/수정
	 * @param board
	 */
	public void save(Board param) {
		Board board = repository.get(param.getBoardSeq());
		if(board == null) {
			repository.save(param);
		} else {
			repository.update(param);
		}
	}
	
	
	/**
	 * 삭제
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
}
