ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;


CREATE USER member_jtj IDENTIFIED BY member1234;



GRANT CONNECT, RESOURCE TO member_jtj;

ALTER USER member_jtj DEFAULT TABLESPACE SYSTEM QUOTA
UNLIMITED ON SYSTEM;

-- [멤버 계정]
-- 테이블명 : MEMBER
-- 컬럼명 : MEMBER_NO 숫자 기본키 ' 회원 번호(PK)'
--			 MEMBER_ID VARCHAR2(20) NULL 허용 X '회원 아이디'
--			 MEMBER_PW VARCHAR2(20) NULL 허용 X '회원 비밀번호'
--			 MEMBER_NM VARCHAR2(30) NULL 허용 X '회원 이름'
--			 MEMBER_GENDER CHAR(1) M 또는 F만 입력 가능 '회원 성별(M/F)'
--			 ENROLL_DATE DATE 기본값 현재날짜 '회원 가입일'
--			 SECESSION_FL CHAR(1) 기본값 'N' , Y 또는 N만 입력 가능 '탈퇴여부(Y/N)'

CREATE TABLE MEMBER(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR(20) NOT NULL,
	MEMBER_PW VARCHAR2(20) NOT NULL,
	MEMBER_NM VARCHAR2(30) NOT NULL,
	MEMBER_GENDER CHAR(1) CHECK(MEMBER_GENDER IN('M','F')),
	ENROLL_DATE DATE DEFAULT SYSDATE,
	SECESSION_FL CHAR(1) DEFAULT 'N' CHECK(SECESSION_FL IN('Y','N'))
);
COMMENT ON COLUMN MEMBER.MEMBER_NO IS '회원번호(PK)';
COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원 아이디';
COMMENT ON COLUMN MEMBER.MEMBER_PW IS '회원 비밀번호';
COMMENT ON COLUMN MEMBER.MEMBER_NM IS '회원이름';
COMMENT ON COLUMN MEMBER.MEMBER_GENDER IS '회원 성별(M/F';
COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '회원 가입일';
COMMENT ON COLUMN MEMBER.SECESSION_FL IS '탈퇴여부(Y/N)';


-- 게시판 테이블

CREATE TABLE BOARD(
    BOARD_NO NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(200) NOT NULL,
    BOARD_CONTENT VARCHAR2(4000) NOT NULL,
    CREATE_DATE DATE DEFAULT SYSDATE,
    READ_COUNT NUMBER DEFAULT 0,
    MEMBER_NO NUMBER REFERENCES MEMBER  -- MEMBER 테이블 PK값 참조
);

COMMENT ON COLUMN BOARD.BOARD_NO        IS '게시글 번호';
COMMENT ON COLUMN BOARD.BOARD_TITLE     IS '게시글 제목';
COMMENT ON COLUMN BOARD.BOARD_CONTENT   IS '게시글 내용';
COMMENT ON COLUMN BOARD.CREATE_DATE     IS '게시글 작성일';
COMMENT ON COLUMN BOARD.READ_COUNT      IS '조회수';
COMMENT ON COLUMN BOARD.MEMBER_NO       IS '회원 번호(작성자)';

-- 댓글 테이블
CREATE TABLE REPLY(
    REPLY_NO NUMBER PRIMARY KEY,
    REPLY_CONTENT VARCHAR2(500) NOT NULL,
    CREATE_DATE DATE DEFAULT SYSDATE,
    MEMBER_NO NUMBER REFERENCES MEMBER, -- MEMBER 테이블 PK 참조
    BOARD_NO NUMBER REFERENCES BOARD -- BOARD 테이블 PK 참조
);

COMMENT ON COLUMN REPLY.REPLY_NO         IS '댓글 번호(PK)';
COMMENT ON COLUMN REPLY.REPLY_CONTENT    IS '댓글 내용';
COMMENT ON COLUMN REPLY.CREATE_DATE      IS '댓글 작성일';
COMMENT ON COLUMN REPLY.MEMBER_NO        IS '회원 번호(작성자)';
COMMENT ON COLUMN REPLY.BOARD_NO         IS '게시글 번호(어떤 게시글의 댓글인지 확인)';

-- 각 테이블 PK 생성용 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO; -- 1부터 1씩 증가, 반복 X
CREATE SEQUENCE SEQ_BOARD_NO;
CREATE SEQUENCE SEQ_REPLY_NO;

--------------------------------------------------------------------------------------

-- 아이디 중복 검사
SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = 'USER01' AND SECESSION_FL = 'N'; 
-- 탈퇴 안한 계정

-- 회원 가입
INSERT INTO MEMBER VALUES(SEQ_MEMBER_NO.NEXTVAL,?,?,?,?,DEFAULT,DEFAULT);

-- 회원 목록 조회
SELECT MEMBER_ID, MEMBER_NM, ENROLL_DATE
FROM MEMBER
WHERE SECESSION_FL ='N';

-- 정보 수정

UPDATE MEMBER 
SET MEMBER_NM = ? , MEMBER_GENDER=?
WHERE MEMBER_ID = ?;

ROLLBACK;

UPDATE MEMBER
SET MEMBER_PW = ?
WHERE MEMBER_NO = ?
AND MEMBER_PW = ?;

UPDATE MEMBER SET
SESSION_FL = 'Y'
WHERE MEMBER_NO = ?
AND MEMBER_PW = ?;

-- 게시글 목록 조회 + 댓글 갯수 (상관 서브쿼리+ 스칼라 서브쿼리)
SELECT BOARD_NO, BOARD_TITLE, CREATE_DATE, READ_COUNT, MEMBER_NM, 
(SELECT COUNT(*) FROM REPLY WHERE REPLY.BOARD_NO = BOARD.BOARD_NO) AS "REPLY_COUNT"
FROM BOARD 
JOIN MEMBER USING(MEMBER_NO)
ORDER BY BOARD_NO DESC;

SELECT * FROM BOARD;

-- 게시글 샘플 데이터 + 댓글 개수
INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 게시블 1', ' 샘플1 내용입니다.', DEFAULT,DEFAULT,41);

INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 게시블 2', ' 샘플2 내용입니다.', DEFAULT,DEFAULT,41);

INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 게시블 3', ' 샘플3 내용입니다.', DEFAULT,DEFAULT,41);

COMMIT;
ROLLBACK;
SELECT * FROM BOARD;
SELECT COUNT(*)FROM REPLY WHERE BOARD_NO = 3;
-- 댓글 샘플 데이터 삽입

INSERT INTO REPLY
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글1',DEFAULT,41,61);

INSERT INTO REPLY
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글2',DEFAULT,41,61);

INSERT INTO REPLY
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글3',DEFAULT,41,61);

COMMIT;
SELECT * FROM BOARD;

-- 특정 게시글 상세 조회

SELECT B.*, MEMBER_NM
FROM BOARD B
JOIN MEMBER M ON (B.MEMBER_NO = M.MEMBER_NO)
WHERE BOARD_NO = 41;

-- 댓글 목록에서 최근에 작성한 글은 젤위 젤아래?


-- 특정 게시글의 댓글 목록 조회

SELECT R.*, MEMBER_NM
FROM REPLY R
JOIN MEMBER M ON (R.MEMBER_NO = M.MEMBER_NO)
WHERE BOARD_NO = 41
ORDER BY REPLY_NO DESC; -- 최근 댓글 상단
-- ORDER BY REPLY_NO; -- 최근 댓글 하단

-- 조회수 증가
UPDATE BOARD SET
READ_COUNT = READ_COUNT + 1
WHERE BOARD_NO = ?;

-- 게시글 삭제
DELETE FROM BOARD 
WHERE BOARD_NO = 41;

SELECT* FROM BOARD WHERE BOARD_NO = 41; -- BOARD 테이블 41번 게시글
SELECT* FROM REPLY WHERE BOARD_NO = 41; -- REPLY 테이블에서 BOARD 테이블 41번 게시글을 참조하는 댓글
--> 기본적으로 삭제 불가
--> 삭제 옵션을 추가하면 가능
-- ON DELETE SET NULL(자식 컬럼 NULL) / ON DELETE CASCADE(참조하던 자식 행도 삭제)

-- 제약조건은 ALTER(변경) 없음 -> 삭제 후 다시 추가

-- 기존 REPLY 테이블 FK 제약조건 삭제

-- 삭제 옵션이 추가된 FK를 다시 추가

ALTER TABLE REPLY
DROP CONSTRAINT SYS_C007604;
ROLLBACK;
ALTER TABLE REPLY
ADD FOREIGN KEY(BOARD_NO) REFERENCES BOARD(BOARD_NO) ON DELETE CASCADE;
SELECT * FROM REPLY;
SELECT * FROM BOARD;
SELECT * FROM MEMBER;
DELETE FROM REPLY;
COMMIT;
ROLLBACK;
DELETE FROM BOARD 
WHERE BOARD_NO = ?

UPDATE BOARD SET 
BOARD_TITLE ='NAME',BOARD_CONTENT='NAME'
WHERE BOARD_NO = 43;
ROLLBACK;

UPDATE REPLY SET
REPLY_CONTENT ='NAME'
WHERE REPLY_NO =61;

-- 댓글 삽입
INSERT INTO REPLY
VALUES(SEQ_REPLY_NO.NEXTVAL,?,DEFAULT,?,?);



-- 게시글 검색
-- 제목 검색

SELECT BOARD_NO, BOARD_TITLE, CREATE_DATE, READ_COUNT, MEMBER_NM, 
(SELECT COUNT(*) FROM REPLY WHERE REPLY.BOARD_NO = BOARD.BOARD_NO) AS "REPLY_COUNT" 
FROM BOARD 
JOIN MEMBER USING (MEMBER_NO)

-- 제목 검색
WHERE BOARD_TITLE LIKE '%?%';

-- 내용 검색
WHERE BOARD_CONTENT LIKE '%?%';

-- 제목 + 내용 검색
WHERE (BOARD_TITLE LIKE '%송%') OR (BOARD_CONTENT LIKE '%송%');

-- 작성자 검색
WHERE MEMBER_NM LIKE '%oh%'

ORDER BY BOARD_NO DESC;





