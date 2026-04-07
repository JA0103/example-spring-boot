package com.example.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.configuration.exception.BaseException;
import com.example.configuration.http.BaseResponse;
import com.example.configuration.http.BaseResponseCode;
import com.example.mvc.domain.Board;
import com.example.mvc.parameter.BoardParameter;
import com.example.mvc.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * 게시판 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/board")
@Tag(name = "게시판 API", description = "게시판 관련 기능")
public class BoardContorller {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService boardService;

	/**
	 * 게시물 목록 조회
	 */
	@GetMapping
	@Operation(summary = "게시물 목록 조회", description = "게시물 목록 정보를 조회할 수 있습니다.")
	public BaseResponse<List<Board>> getList() {
		logger.info("getList");
		return new BaseResponse<List<Board>>(boardService.getList());
	}

	/**
	 * 게시물 상세 조회
	 * @param boardSeq
	 */
	@GetMapping("/{boardSeq}")
	@Operation(summary = "게시물 상세 조회", description = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@Parameter(name = "boardSeq", description = "게시물 번호", example = "1")
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}

	/**
	 * 게시물 등록/수정
	 * @param board
	 */
	@PutMapping("/save")
	@Operation(summary = "게시물 등록/수정", description = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	public BaseResponse<Integer> save(@RequestBody BoardParameter param) {
		// 제목 필수 체크
		if (!StringUtils.hasText(param.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
		}
		// 내용 필수 체크
		if (!StringUtils.hasText(param.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" });
		}

//		// null 처리
//		if (board == null) {
//			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
//		}
//		
		boardService.save(param);
		return new BaseResponse<Integer>(param.getBoardSeq());
	}

	/**
	 * 게시물 삭제
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@Operation(summary = "게시물 삭제", description = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@Parameter(name = "boardSeq", description = "게시물 번호", example = "1")
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
}
