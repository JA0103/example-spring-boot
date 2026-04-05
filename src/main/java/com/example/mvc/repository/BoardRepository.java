package com.example.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.mvc.domain.Board;
import com.example.mvc.parameter.BoardParameter;

/**
 * 게시판 Repository
 */
@Repository
public interface BoardRepository {
	
	List<Board> getList();

	Board get(int boardSeq);
	
	void save(BoardParameter board);
	
	void update(BoardParameter board);
	
	void delete(int boardSeq);
	
}
