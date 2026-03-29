/*
CREATE TABLE board (
    board_seq INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    contents TEXT,
    reg_date DATETIME
);
*/

-- SELECT * FROM board;

/*INSERT INTO board (title, contents, reg_date)
VALUES ("제목1", "내용1", NOW());*/

SELECT
			B.BOARD_SEQ,
			B.TITLE,
			B.CONTENTS,
			B.REG_DATE
		FROM BOARD B
		WHERE B.BOARD_SEQ = 1
		ORDER BY B.REG_DATE DESC