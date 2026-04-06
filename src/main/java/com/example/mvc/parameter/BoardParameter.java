package com.example.mvc.parameter;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardParameter {
	@Schema(description = "게시물 번호", example = "1")
    private int boardSeq;

    @Schema(description = "제목", example = "제목입니다")
    private String title;

    @Schema(description = "내용", example = "내용입니다")
    private String contents;
}
