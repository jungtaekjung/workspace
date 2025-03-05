ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 계정 생성
CREATE USER project IDENTIFIED BY "project1234";

-- 권한 부여
GRANT CONNECT, RESOURCE, CREATE VIEW TO project;

-- 객체 생성 공간 할당
ALTER USER project DEFAULT TABLESPACE SYSTEM
QUOTA UNLIMITED ON SYSTEM;

------------------------------------------------------------

DROP TABLE "MEMBER";

CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"MEMBER_EMAIL"	VARCHAR2(50)		NOT NULL,
	"MEMBER_PW"	VARCHAR2(100)		NOT NULL,

	"MEMBER_NICKNAME" VARCHAR2(30) NOT NULL,
	
	"MEMBER_TEL"	CHAR(11)		NOT NULL,
	"MEMBER_ADDR"	VARCHAR2(300)		NULL,
	"PROFILE_IMG"	VARCHAR2(300)		NULL,
	"ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MEMBER_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"AUTHORITY"	NUMBER	DEFAULT 1	NOT NULL
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원 번호(SEQ_MEMBER_NO)';

COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호(암호화 적용)';

COMMENT ON COLUMN "MEMBER"."MEMBER_NICKNAME" IS '회원 이름(별명)';

COMMENT ON COLUMN "MEMBER"."MEMBER_TEL" IS '전화번호(- 없음)';

COMMENT ON COLUMN "MEMBER"."MEMBER_ADDR" IS '회원 주소';

COMMENT ON COLUMN "MEMBER"."PROFILE_IMG" IS '프로필 이미지 저장 경로';

COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '회원 가입일';

COMMENT ON COLUMN "MEMBER"."MEMBER_DEL_FL" IS '탈퇴여부(N:탈퇴X, Y: 탈퇴O)';

COMMENT ON COLUMN "MEMBER"."AUTHORITY" IS '회원권한(1:일반, 2:관리자)';


ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY (
	"MEMBER_NO"
);

-- 탈퇴여부 CHECK 제약 조건
ALTER TABLE "MEMBER" ADD CONSTRAINT "CH_MEMBER_DEL_FL" 
CHECK("MEMBER_DEL_FL" IN ('N', 'Y'));

-- 권한 CHECK 제약 조건
ALTER TABLE "MEMBER" ADD CONSTRAINT "CH_AUTHORITY" 
CHECK("AUTHORITY" IN (1, 2));


-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;


-- 샘플 계정 추가
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01@kh.or.kr', 'pass01!'
	, '유저일', '01012341234', '06234,,서울 강남구 테헤란로 14길 6,,5층',
	NULL, DEFAULT, DEFAULT, DEFAULT);


INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02@kh.or.kr', 'pass02!'
	, '이번유저', '01043214321', '06234,,서울 강남구 테헤란로 14길 6,,5층',
	NULL, DEFAULT, DEFAULT, DEFAULT);

COMMIT;



-- 로그인 SQL
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME,
	MEMBER_TEL, MEMBER_ADDR, PROFILE_IMG, AUTHORITY,
	TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') AS ENROLL_DATE 
FROM "MEMBER"
WHERE MEMBER_DEL_FL = 'N'
AND MEMBER_EMAIL = 'user01@kh.or.kr'
;

UPDATE MEMBER
SET MEMBER_PW = '$2a$10$IOwHghQtyb8MxGdzvmp2OOY9xSG/0s07qIna9ZeTDn3roxNbfaGBC'
WHERE MEMBER_EMAIL = 'user01@kh.or.kr';


SELECT * FROM MEMBER;


-- 현재 로그인한 회원의 비밀번호 조회
SELECT MEMBER_PW 
FROM MEMBER 
WHERE MEMBER_NO = ?;

-- 비밀번호 변경
UPDATE MEMBER 
SET MEMBER_PW = '?' 
WHERE MEMBER_NO = '?';

-- 이메일로 회원 정보조히
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME, MEMBER_TEL, 
       NVL(MEMBER_ADDR, '미작성') AS MEMBER_ADDR,
       TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월", DD"일"') AS ENROLL_DATE
FROM MEMBER
WHERE MEMBER_EMAIL = 'user01@kh.or.kr'
AND MEMBER_DEL_FL = 'N';

-- 입력한 이메일 값으로 회원 정보 조회
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME
FROM MEMBER
WHERE MEMBER_EMAIL LIKE '%user%'
AND MEMBER_DEL_FL = 'N'

--------------------------------------------------------------------------------

–이메일 인증키 테이블
DROP TABLE "AUTH_KEY";

CREATE TABLE "AUTH_KEY" (
   "AUTH_KEY_NO"   NUMBER      NOT NULL,
   "CODE"   CHAR(6)      NOT NULL,
   "EMAIL"   VARCHAR2(50)      NOT NULL,
   "CREATE_TIME"   DATE   DEFAULT SYSDATE   NOT NULL
);



COMMENT ON COLUMN "AUTH_KEY"."AUTH_KEY_NO" IS '인증키 구분 번호(SEQ_AUTH_KEY_NO)';

COMMENT ON COLUMN "AUTH_KEY"."CODE" IS '코드';

COMMENT ON COLUMN "AUTH_KEY"."EMAIL" IS '이메일';

COMMENT ON COLUMN "AUTH_KEY"."CREATE_TIME" IS '인증 코드 생성 시간';

ALTER TABLE "AUTH_KEY" ADD CONSTRAINT "PK_AUTH_KEY" PRIMARY KEY (
   "AUTH_KEY_NO"
);


CREATE SEQUENCE SEQ_AUTH_KEY_NO NOCACHE;


UPDATE "AUTH_KEY" SET
CODE = #{authkey},
CREATE_TIME = sysdate
WHERE EMAIL = #{email};

INSERT INTO "AUTH_KEY" VALUES(SEQ_AUTH_KEY_NO.NEXTVAL, #{authkey}, #{email}, DEFAULT);

SELECT * FROM "AUTH_KEY";

SELECT COUNT(*) FROM "AUTH_KEY"
WHERE EMAIL = #{email}
AND CODE = #{inputKey}
;



--------------------------------------------------------------------------------------------------


-- 게시판 종류
CREATE TABLE "BOARD_TYPE"(
   "BOARD_CODE" NUMBER CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY,
   "BOARD_NAME" VARCHAR2(30) NOT NULL
);

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CODE" 
IS '게시판 코드(SEQ_BOARD_CODE)';

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME" 
IS '게시판 이름';

CREATE SEQUENCE SEQ_BOARD_CODE NOCACHE;

-- 게시판 종류 추가
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '공지사항');

INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '자유 게시판');

INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '질문 게시판');

INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, 'FAQ');

COMMIT;

SELECT * FROM "BOARD_TYPE";

SELECT * FROM BOARD_TYPE ORDER BY 1;




-- [게시판 DB 설정]


CREATE TABLE "BOARD" (
   "BOARD_NO"   NUMBER      NOT NULL,
   "BOARD_TITLE"   VARCHAR2(150)      NOT NULL,
   "BOARD_CONTENT"   VARCHAR2(4000)      NOT NULL,
   "B_CREATE_DATE"   DATE   DEFAULT SYSDATE   NOT NULL,
   "B_UPDATE_DATE"   DATE      NULL,
   "READ_COUNT"   NUMBER   DEFAULT 0   NOT NULL,
   "BOARD_DEL_FL"   CHAR(1)   DEFAULT 'N'   NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL,
   "BOARD_CODE"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호(SEQ_BOARD_NO)';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';

COMMENT ON COLUMN "BOARD"."B_CREATE_DATE" IS '게시글 작성일';

COMMENT ON COLUMN "BOARD"."B_UPDATE_DATE" IS '마지막 수정일(수정 시 UPDATE)';

COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "BOARD"."BOARD_DEL_FL" IS '삭제 여부(N : 삭제X , Y : 삭제O)';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '작성자 회원 번호';

COMMENT ON COLUMN "BOARD"."BOARD_CODE" IS '게시판 코드 번호';

----------------------------------------------------------------------

CREATE TABLE "BOARD_IMG" (
   "IMG_NO"   NUMBER      NOT NULL,
   "IMG_PATH"   VARCHAR2(300)      NOT NULL,
   "IMG_RENAME"   VARCHAR2(30)      NOT NULL,
   "IMG_ORIGINAL"   VARCHAR2(300)      NOT NULL,
   "IMG_ORDER"   NUMBER      NOT NULL,
   "BOARD_NO"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "BOARD_IMG"."IMG_NO" IS '이미지 번호(SEQ_IMG_NO)';

COMMENT ON COLUMN "BOARD_IMG"."IMG_PATH" IS '이미지 저장 폴더 경로';

COMMENT ON COLUMN "BOARD_IMG"."IMG_RENAME" IS '변경된 이미지 파일 이름';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORIGINAL" IS '원본 이미지 파일 이름';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORDER" IS '이미지 파일 순서 번호';

COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '이미지가 첨부된 게시글 번호';


----------------------------------------------------------------------


CREATE TABLE "BOARD_LIKE" (
   "BOARD_NO"   NUMBER      NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "BOARD_LIKE"."BOARD_NO" IS '게시글 번호';

COMMENT ON COLUMN "BOARD_LIKE"."MEMBER_NO" IS '좋아요 누른 회원 번호';


----------------------------------------------------------------------


CREATE TABLE "COMMENT" (
   "COMMENT_NO"   NUMBER      NOT NULL,
   "COMMENT_CONTENT"   VARCHAR2(4000)      NOT NULL,
   "C_CREATE_DATE"   DATE   DEFAULT SYSDATE   NOT NULL,
   "COMMENT_DEL_FL"   CHAR(1)   DEFAULT 'N'   NOT NULL,
   "BOARD_NO"   NUMBER      NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL,
   "PARENT_NO"   NUMBER   
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호(SEQ_COMMENT_NO)';

COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "COMMENT"."C_CREATE_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부(N : 삭제X, Y : 삭제O)';

COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '댓글이 작성된 게시글 번호';

COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '댓글 작성자 회원 번호';

COMMENT ON COLUMN "COMMENT"."PARENT_NO" IS '부모 댓글 번호';

----------------------------------------------------------------------


ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
   "BOARD_NO"
);

ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "PK_BOARD_IMG" PRIMARY KEY (
   "IMG_NO"
);

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "PK_BOARD_LIKE" PRIMARY KEY (
   "BOARD_NO",
   "MEMBER_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
   "COMMENT_NO"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY (
   "MEMBER_NO"
)
REFERENCES "MEMBER" (
   "MEMBER_NO"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_BOARD_1" FOREIGN KEY (
   "BOARD_CODE"
)
REFERENCES "BOARD_TYPE" (
   "BOARD_CODE"
);

ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "FK_BOARD_TO_BOARD_IMG_1" FOREIGN KEY (
   "BOARD_NO"
)
REFERENCES "BOARD" (
   "BOARD_NO"
);

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARD_LIKE_1" FOREIGN KEY (
   "BOARD_NO"
)
REFERENCES "BOARD" (
   "BOARD_NO"
);

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_LIKE_1" FOREIGN KEY (
   "MEMBER_NO"
)
REFERENCES "MEMBER" (
   "MEMBER_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_BOARD_TO_COMMENT_1" FOREIGN KEY (
   "BOARD_NO"
)
REFERENCES "BOARD" (
   "BOARD_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY (
   "MEMBER_NO"
)
REFERENCES "MEMBER" (
   "MEMBER_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_1" FOREIGN KEY (
   "PARENT_NO"
)
REFERENCES "COMMENT" (
   "COMMENT_NO"
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOARD_NO NOCACHE; -- 게시글 번호
CREATE SEQUENCE SEQ_IMG_NO NOCACHE; -- 게시글 이미지 번호
CREATE SEQUENCE SEQ_COMMENT_NO NOCACHE; -- 댓글 번호





-- BOARD 테이블 샘플 데이터 삽입(PL/SQL)
BEGIN
   FOR I IN 1..1500 LOOP
      INSERT INTO BOARD 
      VALUES( SEQ_BOARD_NO.NEXTVAL,
              SEQ_BOARD_NO.CURRVAL || '번째 게시글',
              SEQ_BOARD_NO.CURRVAL || '번째 게시글 내용 입니다.',
              DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 
              CEIL(DBMS_RANDOM.VALUE(0,4))
      );
   END LOOP;
END;
/

SELECT * FROM BOARD;


COMMIT;

-- BOARD_CODE가 1(공지사항)인 게시글을 최신순으로 조회
-- 단, 삭제된 글은 조회x

SELECT * FROM BOARD
WHERE BOARD_CODE = 1
  AND BOARD_DEL_FL = 'N'
ORDER BY BOARD_NO DESC;

-- COMMENT 테이블 샘플 데이터 삽입(PL/SQL)
BEGIN
   FOR I IN 1..1000 LOOP
      INSERT INTO "COMMENT" 
      VALUES(SEQ_COMMENT_NO.NEXTVAL, 
            SEQ_COMMENT_NO.CURRVAL || '번째 댓글',
            DEFAULT, DEFAULT,
             CEIL(DBMS_RANDOM.VALUE(0,1500)),
             1, NULL);
   END LOOP;
END;
/

SELECT * FROM "COMMENT";

SELECT * FROM BOARD_IMG;

INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211104413_00001.jpg',
		'cat1.jpg',0,1498);

INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211104413_00002.jpg',
		'cat2.jpg',0,1493);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211104413_00003.jpg',
		'cat3.jpg',0,1491);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211104413_00004.jpg',
		'cat4.jpg',0,1489);

INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211104413_00005.jpg',
		'cat5.jpg',0,1488);
	
SELECT * FROM BOARD
WHERE BOARD_CODE = 1
ORDER BY 1 DESC;

-- 특정 게시판의 삭제되지 않은 게시글 수 ㅈ회
SELECT COUNT(*) FROM BOARD
WHERE BOARD_DEL_FL = 'N'
AND BOARD_CODE = 1;

SELECT * FROM BOARD;

-- 게시글 목록 조회
-- 마이바티스 사용 X
SELECT * FROM(
   SELECT ROWNUM RNUM, A.* FROM (
      SELECT BOARD_NO, BOARD_TITLE, MEMBER_NICK, 
         TO_CHAR(CREATE_DT, 'YYYY-MM-DD') AS CREATE_DT 
         , READ_COUNT, (SELECT IMG_RENAME FROM BOARD_IMG I
                  WHERE I.BOARD_NO = B.BOARD_NO
                  AND IMG_LEVEL = 0) THUMBNAIL
      FROM BOARD B
      JOIN MEMBER USING(MEMBER_NO)
      WHERE BOARD_CD = 1
      AND BOARD_ST = 'N'
      ORDER BY BOARD_NO DESC
   ) A
)
WHERE RNUM BETWEEN ? AND ?

-- 마이바티스 O
 SELECT BOARD_NO, BOARD_TITLE, MEMBER_NICKNAME, 
         TO_CHAR(B_CREATE_DATE, 'YYYY-MM-DD') AS B_CREATE_DATE 
         , READ_COUNT
  FROM BOARD B
  JOIN MEMBER USING(MEMBER_NO)
  WHERE BOARD_CODE = 1
  AND BOARD_DEL_FL = 'N'
  ORDER BY BOARD_NO DESC;
  
 -- 게시글 상세 조회
 SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, READ_COUNT, MEMBER_NICKNAME, MEMBER_NO,
 	PROFILE_IMG, BOARD_CODE,
 	TO_CHAR(B_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') B_CREATE_DATE,
 	TO_CHAR(B_UPDATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') B_UPATE_DATE,
 	(SELECT COUNT(*) FROM BOARD_LIKE L WHERE L.BOARD_NO = B.BOARD_NO) AS LIKE_COUNT
FROM BOARD B
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_DEL_FL = 'N'
AND BOARD_CODE = 1
AND BOARD_NO = 1498;

SELECT * FROM BOARD_LIKE;

-- 게시글 좋아요 샘플데이터 삽입
INSERT INTO BOARD_LIKE VALUES(1498, 1);
INSERT INTO BOARD_LIKE VALUES(1498, 2);

COMMIT;

SELECT * FROM MEMBER;

-- 특정 게시글의 좋아요 개수 카운트

SELECT COUNT(*) FROM BOARD_LIKE
WHERE BOARD_NO = 1498
AND MEMBER_NO = 1;

-- 특정 게시글에 대한 이미지 조회(IMG_ORDER 오름차순)
SELECT * FROM BOARD_IMG
WHERE BOARD_NO = 1498
ORDER BY IMG_ORDER;

INSERT INTO BOARD_IMG 
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/','20250211204413_00005.jpg',
		'cat5.jpg',4,1498);
	
-- 특정 게시글에 대한 댓글 목록 조회(바꿀 예정)
SELECT COMMENT_NO, COMMENT_CONTENT, 
	TO_CHAR(C_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') C_CREATE_DATE,
	BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
FROM "COMMENT"
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_NO = 1498
ORDER BY 1;

SELECT * FROM BOARD
ORDER BY BOARD_NO DESC;

SELECT * FROM "COMMENT"
ORDER BY 1 DESC;

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '부모 댓글 1', DEFAULT,DEFAULT,1519,1,NULL);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '부모 댓글 2', DEFAULT,DEFAULT,1519,1,NULL);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-2', DEFAULT,DEFAULT,1519,1,1001);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-3', DEFAULT,DEFAULT,1519,1,1001);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-4', DEFAULT,DEFAULT,1519,1,1001);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 2-1', DEFAULT,DEFAULT,1519,1,1002);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 2-2', DEFAULT,DEFAULT,1519,1,1002);

INSERT INTO "COMMENT"                                
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 2-1-1', DEFAULT,DEFAULT,1519,1,1006);

/* 계층형 쿼리(START WITH, CONNECT BY, ORDER SIBLINGS BY) 
 * 	상위 타입과 하위 타입간의 관계를 계층식으로 표현할 수 있게 하는 질의어(SELECT)
	- START WITH : 상위 타입(최상위 부모)으로 사용될 행을 지정 (서브쿼리로 지정 가능)
	
	- CONNECT BY 
	  -> 상위 타입과 하위 타입 사이의 관계를 규정
	  -> PRIOR(이전의) 연산자와 같이 사용하여
	     현재 행 이전에 상위 타입 또는 하위 타입이 있을지 규정
	
	   1) 부모 -> 자식 계층 구조
	     CONNECT BY PRIOR 자식 컬럼 = 부모 컬럼
	
	   2) 자식 -> 부모 계층 구조
	     CONNECT BY PRIOR 부모 컬럼 = 자식 컬럼
	
	- ORDER SIBLINGS BY : 계층 구조 정렬
	
	
	** 계층형 쿼리가 적용 SELECT 해석 순서 **
	
	5 : SELECT
	1 : FROM (+JOIN)
	4 : WHERE
	2 : START WITH
	3 : CONNECT BY
	6 : ORDER SIBLINGS BY
	
	- WHERE절이 계층형 쿼리보다 해석 순서가 늦기 때문에
	먼저 조건을 반영하고 싶은 경우 FROM절 서브쿼리(인라인뷰)를 이용
	 * 
 * */

-- 댓글 목록 조회 (계층형 쿼리 적용)
SELECT LEVEL, C.* FROM
	(SELECT COMMENT_NO, COMMENT_CONTENT, 
		TO_CHAR(C_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') C_CREATE_DATE,
		BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
	FROM "COMMENT"
	JOIN MEMBER USING(MEMBER_NO)
	WHERE BOARD_NO = 1519) C
WHERE COMMENT_DEL_FL = 'N'
START WITH PARENT_NO IS NULL
CONNECT BY PRIOR COMMENT_NO = PARENT_NO
ORDER SIBLINGS BY COMMENT_NO;



-- 회원 프로필 이미지 변경
UPDATE MEMBER SET
PROFILE_IMG='/resources/images/member/gromit.jpg'
WHERE MEMBER_NO =1;
COMMIT;

SELECT * FROM BOARD_LIKE;
SELECT * FROM BOARD_IMG;

-- 좋아요 여부 확인
SELECT COUNT(*) FROM BOARD_LIKE
WHERE BOARD_NO = 1498
AND MEMBER_NO = 1

-- 좋아요 테이블 삽입
INSERT INTO BOARD_LIKE VALUES(1, 1);


-- 좋아요 테이블 삭제
DELETE FROM BOARD_LIKE
WHERE BOARD_NO = 1
AND MEMBER_NO = 1;

-- 해당 게시글의 좋아요 개수 조회
SELECT COUNT(*) FROM BOARD_LIKE
WHERE BOARD_NO = 1;

UPDATE BOARD
SET READ_COUNT = READ_COUNT + 1
WHERE BOARD_NO = ?;

UPDATE MEMBER
SET PROFILE_IMG = ?
WHERE MEMBER_NO= ?;



-- 게시글 삽입
INSERT INTO BOARD 
VALUES(SEQ_BOARD_NO.NEXTVAL, #{boardTitle}, #{boardContent}, SYSDATE,
DEFAULT, DEFAULT, DEFAULT, ${memberNo}, ${boardCode});

SELECT * FROM BOARD_IMG;

-- 이미지 삽입
INSERT INTO BOARD_IMG
SELECT SEQ_IMG_NO.NEXTVAL, A.*
FROM (SELECT '웹접근경로' IMG_PATH, '변경명' IMG_RENAME, '원본명' IMG_ORIGINAL, 0 IMG_ORDER,
		1517 BOARD_NO
		FROM DUAL

		UNION ALL

		SELECT '웹접근경로' IMG_PATH, '변경명' IMG_RENAME, '원본명' IMG_ORIGINAL, 1 IMG_ORDER,
		1517 BOARD_NO
		FROM DUAL)A;

DELETE  FROM BOARD_IMG
WHERE IMG_NO=15;

COMMIT;

SELECT * FROM BOARD_IMG;
-- INSERT ALL 테이블 VALUES(), 테이블 VALUES(), 테이블 VALUES() : 적합 x

SELECT * FROM BOARD_IMG ORDER BY 1 DESC;	

-- INSERT + SUB QUERY
SELECT '웹접근경로' IMG_PATH, '변경명' IMG_RENAME, '원본명' IMG_ORIGINAL, 0 IMG_ORDER,
		1517 BOARD_NO
FROM DUAL

UNION ALL

SELECT '웹접근경로' IMG_PATH, '변경명' IMG_RENAME, '원본명' IMG_ORIGINAL, 1 IMG_ORDER,
		1517 BOARD_NO
FROM DUAL;		


-- 특정 게시판의 삭제되지 않고 검색 조건이 일치하는 게시글 수 조회
SELECT COUNT(*) FROM BOARD
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_CODE = '게시판코드'
AND BOARD_DEL_FL ='N'
-- AND (BOARD_TITLE LIKE '%검색%');
-- AND (BOARD_CONTENT LIKE '%검색%');
-- AND (BOARD_TITLE LIKE '%게시글%' OR BOARD_CONTENT LIKE '%수정%');
AND MEMBER_NICKNAME LIKE '%유저%';


SELECT COUNT(*)
FROM BOARD
WHERE BOARD_CODE=${boardCode}
AND BOARD_DEL_FL='N'

SELECT IMG_RENAME FROM BOARD_IMG;

SELECT * FROM BOARD
ORDER BY 1 DESC;

 SELECT * FROM(
         SELECT BOARD_NO, BOARD_TITLE, READ_COUNT, BOARD_CODE, BOARD_NAME 
         FROM BOARD
         JOIN BOARD_TYPE USING(BOARD_CODE)
         WHERE BOARD_TITLE LIKE '%15%'
         AND BOARD_DEL_FL = 'N'
         ORDER BY READ_COUNT DESC, BOARD_NO DESC)
         WHERE ROWNUM <= 10;

----------------------------------------------------------------------------------------------------        
-- 채팅
CREATE TABLE "CHATTING_ROOM" (
   "CHATTING_NO"   NUMBER      NOT NULL,
   "CH_CREATE_DATE"   DATE   DEFAULT SYSDATE   NOT NULL,
   "OPEN_MEMBER"   NUMBER      NOT NULL,
   "PARTICIPANT"   NUMBER      NOT NULL
);
COMMENT ON COLUMN "CHATTING_ROOM"."CHATTING_NO" IS '채팅방 번호';
COMMENT ON COLUMN "CHATTING_ROOM"."CH_CREATE_DATE" IS '채팅방 생성일';
COMMENT ON COLUMN "CHATTING_ROOM"."OPEN_MEMBER" IS '개설자 회원 번호';
COMMENT ON COLUMN "CHATTING_ROOM"."PARTICIPANT" IS '참여자 회원 번호';
ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "PK_CHATTING_ROOM" PRIMARY KEY (
   "CHATTING_NO"
);
ALTER TABLE "CHATTING_ROOM"
ADD CONSTRAINT "FK_OPEN_MEMBER"
FOREIGN KEY ("OPEN_MEMBER") REFERENCES "MEMBER";
ALTER TABLE "CHATTING_ROOM"
ADD CONSTRAINT "FK_PARTICIPANT"
FOREIGN KEY ("PARTICIPANT") REFERENCES "MEMBER";
DROP TABLE "MESSAGE";
CREATE TABLE "MESSAGE" (
   "MESSAGE_NO"   NUMBER      NOT NULL,
   "MESSAGE_CONTENT"   VARCHAR2(4000)      NOT NULL,
   "READ_FL"   CHAR   DEFAULT 'N'   NOT NULL,
   "SEND_TIME"   DATE   DEFAULT SYSDATE   NOT NULL,
   "SENDER_NO"   NUMBER      NOT NULL,
   "CHATTING_NO"   NUMBER      NOT NULL
);
COMMENT ON COLUMN "MESSAGE"."MESSAGE_NO" IS '메세지 번호';
COMMENT ON COLUMN "MESSAGE"."MESSAGE_CONTENT" IS '메세지 내용';
COMMENT ON COLUMN "MESSAGE"."READ_FL" IS '읽음 여부';
COMMENT ON COLUMN "MESSAGE"."SEND_TIME" IS '메세지 보낸 시간';
COMMENT ON COLUMN "MESSAGE"."SENDER_NO" IS '보낸 회원의 번호';
COMMENT ON COLUMN "MESSAGE"."CHATTING_NO" IS '채팅방 번호';
ALTER TABLE "MESSAGE" ADD CONSTRAINT "PK_MESSAGE" PRIMARY KEY (
   "MESSAGE_NO"
);
ALTER TABLE "MESSAGE"
ADD CONSTRAINT "FK_CHATTING_NO"
FOREIGN KEY ("CHATTING_NO") REFERENCES "CHATTING_ROOM";
ALTER TABLE "MESSAGE"
ADD CONSTRAINT "FK_SENDER_NO"
FOREIGN KEY ("SENDER_NO") REFERENCES "MEMBER";
-- 시퀀스 생성
CREATE SEQUENCE SEQ_ROOM_NO NOCACHE;
CREATE SEQUENCE SEQ_MESSAGE_NO NOCACHE;

----------------------------------------------------------------------

-- 샘플데이터 삽입


SELECT * FROM "MEMBER"; -- 회원번호 조회
SELECT * FROM CHATTING_ROOM ;
INSERT INTO CHATTING_ROOM
VALUES(SEQ_ROOM_NO.NEXTVAL, DEFAULT, 1, 3);
                  -- 개설자 회원 번호 / 참여자 회원번호


SELECT SEQ_ROOM_NO.CURRVAL FROM DUAL; -- 채팅방 번호 조회


INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '오늘 수업 어땠어?', DEFAULT, DEFAULT, 1, 1);
                                          -- 보낸 회원번호 / 채팅방번호
INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '채팅 배웠는데 너무 재밌어!', DEFAULT, DEFAULT, 3, 1);
                                          -- 보낸 회원번호 / 채팅방번호
INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '진짜? 나도 배우고 싶다~', DEFAULT, DEFAULT, 1, 1);
                                          -- 보낸 회원번호 / 채팅방번호


INSERT INTO CHATTING_ROOM
VALUES(SEQ_ROOM_NO.NEXTVAL, DEFAULT, 2, 2);


INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '주말에 뭐하지?', DEFAULT, DEFAULT, 2, 2);
                                          -- 보낸 회원번호 / 채팅방번호
INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '프로젝트 해야지~', DEFAULT, DEFAULT, 3, 2);
                                          -- 보낸 회원번호 / 채팅방번호


INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, '곧 종강이니 좀만 더 힘내야겠다ㅠㅠ', DEFAULT, DEFAULT, 2, 2);
                                          -- 보낸 회원번호 / 채팅방번호

INSERT INTO CHATTING_ROOM
VALUES(SEQ_ROOM_NO.NEXTVAL, DEFAULT, 1, 2);


COMMIT;

SELECT* FROM CHATTING_ROOM;
-------------------------------------------------------------
-- 내가 참여한 채팅방 조회
SELECT * FROM CHATTING_ROOM
WHERE OPEN_MEMBER = 3
OR PARTICIPANT =3;

-- 1) 가장 최근 메세지 내용 조회
SELECT MESSAGE_CONTENT FROM(
	SELECT * FROM MESSAGE M
	WHERE CHATTING_NO = 1
	ORDER BY MESSAGE_NO DESC)
WHERE ROWNUM = 1;



-- 2) 가장 채팅 보낸 시간
SELECT TO_CHAR(MAX(SEND_TIME), 'YYYY.MM.DD') SEND_TIME
FROM MESSAGE
WHERE CHATTING_NO = 1;

-- 3) 타겟 회원 번호(메세지를 받은 회원의 번호)
-- NVL2('값', '지정값1', '지정값2') : NULL이 아닌 경우 지정값1, NULL인 경우 지정값2
-- (TRUE)? 1:2
--채팅방 개설자인지 조회 후
-- 일치하는 값이 있다 == 개설자 -> 채팅방 참여자에게 메세지 보내야함
-- 일치하는 값이 없다 == 참가자 -> 채팅방 개설자에게 메세지 보내야함
SELECT NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM
			WHERE CHATTING_NO = 1
			AND OPEN_MEMBER =1),
			PARTICIPANT,
			OPEN_MEMBER) TARGET_NO
FROM CHATTING_ROOM
WHERE OPEN_MEMBER =3
OR PARTICIPANT =3;

-- 4) 타겟 회원 닉네임
SELECT NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM
			WHERE CHATTING_NO = 2
			AND OPEN_MEMBER =1),
			(SELECT MEMBER_NICKNAME FROM MEMBER WHERE MEMBER_NO = R.PARTICIPANT),
			(SELECT MEMBER_NICKNAME FROM MEMBER WHERE MEMBER_NO = R.OPEN_MEMBER)) TARGET_NICKNAME
FROM CHATTING_ROOM R
WHERE OPEN_MEMBER =3
OR PARTICIPANT =3;

-- 5) 타겟 회원 프로필
SELECT NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM
			WHERE CHATTING_NO = 2
			AND OPEN_MEMBER =1),
			(SELECT PROFILE_IMG FROM MEMBER WHERE MEMBER_NO = R.PARTICIPANT),
			(SELECT PROFILE_IMG FROM MEMBER WHERE MEMBER_NO = R.OPEN_MEMBER)) TARGET_PROFILE
FROM CHATTING_ROOM R
WHERE OPEN_MEMBER =3
OR PARTICIPANT =3;

-- 6) 읽지 않은 받은 메세지 수
(SELECT COUNT(*)
FROM MESSAGE M
WHERE READ_FL ='N'
AND M.CHATTING_NO = R.CHATTING_NO
AND SENDER_NO !=3) NOT_READ_COUNT


SELECT * FROM MESSAGE;
SELECT * FROM MEMBER;

-- 7) 최근 메세지 번호(정렬용)
SELECT MAX(MESSAGE_NO) FROM MESSAGE
WHERE CHATTING_NO =3;


-- 채팅 목록 조회(조립)
SELECT CHATTING_NO,

   -- 1) 가장 최근 메세지 내용 조회
   (SELECT MESSAGE_CONTENT FROM(
      SELECT * FROM MESSAGE M
      WHERE M.CHATTING_NO = R.CHATTING_NO 
      ORDER BY MESSAGE_NO DESC)
   WHERE ROWNUM = 1) LAST_MESSAGE,
   
   -- 2) 가장 최근 채팅 보낸 시간
   (SELECT TO_CHAR(MAX(SEND_TIME), 'YYYY.MM.DD')  
   FROM MESSAGE M
   WHERE M.CHATTING_NO = R.CHATTING_NO) SEND_TIME,
   
   -- 3) 타겟 회원 번호
   NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM R2
      WHERE R2.CHATTING_NO = R.CHATTING_NO 
      AND OPEN_MEMBER = 1),
      PARTICIPANT,
      OPEN_MEMBER) TARGET_NO,
         
   -- 4) 타겟 회원 닉네임
   NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM R2
      WHERE R2.CHATTING_NO = R.CHATTING_NO 
      AND OPEN_MEMBER = 1),
      (SELECT MEMBER_NICKNAME FROM MEMBER WHERE MEMBER_NO = R.PARTICIPANT),
      (SELECT MEMBER_NICKNAME FROM MEMBER WHERE MEMBER_NO = R.OPEN_MEMBER)) TARGET_NICKNAME,
      
   -- 5) 타켓 프로필 이미지
   NVL2((SELECT OPEN_MEMBER FROM CHATTING_ROOM R2
      WHERE R2.CHATTING_NO = R.CHATTING_NO 
      AND OPEN_MEMBER = 1),
      (SELECT PROFILE_IMG FROM MEMBER WHERE MEMBER_NO = R.PARTICIPANT),
      (SELECT PROFILE_IMG FROM MEMBER WHERE MEMBER_NO = R.OPEN_MEMBER)) TARGET_PROFILE,
      
  	-- 6) 읽지 않은 받은 메세지 수
	(SELECT COUNT(*)
	FROM MESSAGE M
	WHERE READ_FL ='N'
 	AND M.CHATTING_NO = R.CHATTING_NO
    AND SENDER_NO !=1) NOT_READ_COUNT,
      
   (SELECT MAX(MESSAGE_NO) FROM MESSAGE M
	WHERE M.CHATTING_NO =R.CHATTING_NO) MAX_MESSAGE_NO
   
FROM CHATTING_ROOM R
WHERE OPEN_MEMBER = 1
OR PARTICIPANT = 1
-- 8) 최근 메세지 순으로 정렬 + NULL 마지막으로 조회
ORDER BY MAX_MESSAGE_NO DESC NULLS LAST;

-- 채팅 상대 검색(회원번호, 이메일, 닉네임, 프로필사진)
-- 닉네임 또는 이메일 일부라도 일치하는 회원 검색
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME, PROFILE_IMG
FROM MEMBER
WHERE (MEMBER_NICKNAME LIKE '%' || ? || '%' OR MEMBER_EMAIL LIKE '%' || ? || '%')
AND MEMBER_DEL_FL ='N'
AND MEMBER_NO !=1;

-- 검색한 회원과의 채팅방 존재여부 확인

SELECT NVL(SUM(CHATTING_NO),0) CHATTING_NO 
FROM CHATTING_ROOM
WHERE (OPEN_MEMBER =1 AND PARTICIPANT =2)
OR (OPEN_MEMBER = 2 AND PARTICIPANT =1);

-- 채팅방 생성
INSERT INTO CHATTING_ROOM
VALUES(SEQ_ROOM_NO.NEXTVAL, DEFAULT, OPEN_MEMBER , PARTICIPANT);

-- 채팅 메세지 중 내가 보내지 않은 글을 읽음으로 표시
UPDATE MESSAGE
SET READ_FL = 'N'
WHERE CHATTING_NO = 1
AND SENDER_NO != 1;

SELECT * FROM MEMBER;

BEGIN
  FOR I IN 1..10 LOOP
     INSERT INTO MEMBER
     VALUES( SEQ_MEMBER_NO.NEXTVAL,
             'user' || SEQ_MEMBER_NO.NEXTVAL || '@kh.or.kr',
             '$2a$10$9mmso4R6veZ3IZkdMocJf./Mq1t7sTg6CfRvin2gWFW/XQw7kU/Sy',
             SEQ_MEMBER_NO.NEXTVAL || '번유저' , '01012341234',
             DEFAULT, NULL, DEFAULT, DEFAULT, DEFAULT
             );
  END LOOP;
END;

COMMIT;

-- 메세지 목록 조회

SELECT MESSAGE_NO, MESSAGE_CONTENT, READ_FL, SENDER_NO, CHATTING_NO,
	TO_CHAR(SEND_TIME, 'YYYY.MM.DD HH24:MI')
FROM MESSAGE
WHERE CHATTING_NO=1
ORDER BY MESSAGE_NO ;


SELECT * FROM MESSAGE;

-- 채팅 메세지 삽입
INSERT INTO MESSAGE
VALUES(SEQ_MESSAGE_NO.NEXTVAL, 메세지내용, DEFAULT, DEFAULT, 보낸사람, 채팅방번호);

/* 알림 테이블 */
CREATE TABLE "NOTIFICATION" (
   "NOTIFICATION_NO"   NUMBER      NOT NULL,
   "NOTIFICATION_CONTENT"   VARCHAR2(1000)      NOT NULL,
   "NOTIFICATION_CHECK"   CHAR(1)   DEFAULT 'N'   NOT NULL,
   "NOTIFICATION_DATE"   DATE   DEFAULT CURRENT_DATE   NOT NULL,
   "NOTIFICATION_URL"   VARCHAR2(1000)      NOT NULL,
   "SEND_MEMBER_NO"   NUMBER      NOT NULL,
   "RECEIVE_MEMBER_NO"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_NO" IS '알림 번호';
COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CONTENT" IS '알림 내용(HTML 포함)';
COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CHECK" IS '읽음 여부(Y,N)';
COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_DATE" IS '알림 생성 시간';
COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_URL" IS '연결 페이지 주소';
COMMENT ON COLUMN "NOTIFICATION"."SEND_MEMBER_NO" IS '알림 보낸 회원 번호';
COMMENT ON COLUMN "NOTIFICATION"."RECEIVE_MEMBER_NO" IS '알림 받는 회원 번호';


ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "PK_NOTIFICATION" PRIMARY KEY (
   "NOTIFICATION_NO"
);

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "FK_MEMBER_TO_NOTIFICATION_1" FOREIGN KEY (
   "SEND_MEMBER_NO"
)
REFERENCES "MEMBER" (
   "MEMBER_NO"
);

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "FK_MEMBER_TO_NOTIFICATION_2" FOREIGN KEY (
   "RECEIVE_MEMBER_NO"
)
REFERENCES "MEMBER" (
   "MEMBER_NO"
);


-- 알림 번호 시퀀스 생성
CREATE SEQUENCE SEQ_NOTI_NO NOCACHE;



SELECT * FROM NOTIFICATION;

COMMIT;

--알림 삽입

INSERT INTO NOTIFICATION (NOTIFICATION_NO,NOTIFICATION_CONTENT,NOTIFICATION_URL,
						SEND_MEMBER_NO,RECEIVE_MEMBER_NO)
VALUES(SEQ_NOTI_NO.NEXTVAL, '내용', 'URL주소','알림 보낸 회원번호',

	--알림 종류가 게시글 댓글 작성 또는 좋아요 -> 받는 회원의 번호 : 게시글 작성자의 번호
	(SELECT MEMBER_NO FROM BOARD WHERE BOARD_NO= '1500')
	--알림 종류가 답글인 경우 -> 받는 회원의 번호 : 댓글 작성자
	(SELECT MEMBER_NO FROM "COMMENT" WHERE COMMENT_NO =100)
);
----

--알림을 받아야 하는 회원번호 + 안읽은 알림 개수 조회


      SELECT 
         RECEIVE_MEMBER_NO "receiveMemberNo",
         (SELECT COUNT(*) 
         FROM "NOTIFICATION" SUB
         WHERE SUB.RECEIVE_MEMBER_NO = MAIN.RECEIVE_MEMBER_NO 
         AND SUB.NOTIFICATION_CHECK = 'N') "notiCount"
      FROM (
         SELECT RECEIVE_MEMBER_NO
            FROM "NOTIFICATION"
         WHERE NOTIFICATION_NO =  #{notificationNo}
      ) MAIN;
     
 -- 알림 목록 조회
     
     
SELECT 
         NOTIFICATION_NO, 
         NOTIFICATION_CONTENT, 
         NOTIFICATION_URL, 
         PROFILE_IMG AS SEND_MEMBER_PROFILE_IMG, 
         SEND_MEMBER_NO, 
         RECEIVE_MEMBER_NO,
         NOTIFICATION_CHECK,
         CASE 
            WHEN TRUNC(NOTIFICATION_DATE) = TRUNC(CURRENT_DATE) THEN TO_CHAR(NOTIFICATION_DATE, 'AM HH:MI')
            ELSE TO_CHAR(NOTIFICATION_DATE, 'YYYY-MM-DD')
         END AS NOTIFICATION_DATE
      FROM "NOTIFICATION"
      JOIN "MEMBER" ON (SEND_MEMBER_NO = MEMBER_NO)
      WHERE RECEIVE_MEMBER_NO = 1
      ORDER BY NOTIFICATION_NO DESC;
--읽지 않은 알림 개수 조회

     

SELECT COUNT(*) FROM NOTIFICATION
WHERE RECEIVE_MEMBER_NO=1
AND NOTIFICATION_CHECK='N';

UPDATE NOTIFICATION SET
NOTIFICATION_URL='/board/3/1499'
WHERE NOTIFICATION_URL='/board/1/1499';
SELECT * FROM MEMBER WHERE MEMBER_NO = 1;

SELECT * FROM "MEMBER" ORDER BY 1 DESC ;
UPDATE MEMBER SET 
ENROLL_DATE = SYSDATE ;

SELECT * FROM MESSAGE

COMMIT;
MAX(MESSAGE_NO)
        INSERT INTO NOTIFICATION VALUES(SEQ_NOTI_NO.NEXTVAL,'1499번 게시글 좋아요 눌림',DEFAULT,SYSDATE,'/board/3/1499',14,1);
        
        
       

 