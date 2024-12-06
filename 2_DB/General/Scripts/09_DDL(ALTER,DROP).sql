-- DDL(Data Definition Language) : 데이터 정의 언어로
-- 객체를 만들고(CREATE), 수정하고(ALTER), 삭제하는(DROP) 구문

-- ALTER(바꾸다, 변조하다)
-- 수정 가능한 것 : 컬럼(추가/수정/삭제), 제약조건(추가/삭제)
--                  이름변경(테이블, 컬럼, 제약조건)

-- [작성법]
-- 테이블을 수정하는 경우
-- ALTER TABLE 테이블명 ADD|MODIFY|DROP 수정할 내용;

--------------------------------------------------------------------------------
-- 1. 제약조건 추가 / 삭제


-- 제약조건 추가 : ALTER TABLE 테이블명 
--                 ADD [CONSTRAINT 제약조건명] 제약조건(컬럼명) [REFERENCES 테이블명[(컬럼명)]];

-- 제약조건 삭제 : ALTER TABLE 테이블명
--                 DROP CONSTRAINT 제약조건명;

-- 서브쿼리를 이용해서 DEPARTMENT 테이블 복사 -- NOT NULL 제약조건만 복사됨

CREATE TABLE DEPT_COPY
AS SELECT * FROM DEPARTMENT;

SELECT * FROM DEPT_COPY;

-- DEPT_COPY 테이블에 PRIMARY 추가

ALTER TABLE DEPT_COPY
ADD PRIMARY KEY(DEPT_ID);

-- DEPT_COPY 테이블의 DEPT_TITLE 컬럼에 UNIQUE 제약조건 추가 (제약조건명 : DEPT_TITLE_U)
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DEPT_TITLE_U UNIQUE(DEPT_TITLE);

-- DEPT_COPY 테이블의 LOCATION_ID 컬럼에 CHECK 제약조건 추가
-- 컬럼에 작성할 수 있는 값은 L1, L2, L3, L4, L5 
-- 제약조건명 : LOCATION_ID_CHK

ALTER TABLE DEPT_COPY
ADD CONSTRAINT LOCATION_ID_CHK CHECK(LOCATION_ID IN ('L1','L2','L3','L4','L5'));

-- DEPT_COPY 테이블의 DEPT_TITLE 컬럼에 NOT NULL 제약조건 추가
-- * NOT NULL 제약조건은 다루는 방법이 다름
--> NOT NULL을 제외한 제약 조건들은 추가적인 조건으로 인식됨(ADD/DROP)
--> NOT NULL은 기존 컬럼의 성질을 변경하는 것으로 인식됨(MODIFY) -- MODIFY : 바꾸다, 수정하다



ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE NOT NULL; -- NOT NULL은 MODIFY 사용 !!

-----------------------------------------------------------------------------------

-- DEPARTMENT_COPY에 추가한 제약 조건 중 PK빼고 모두 삭제
ALTER TABLE DEPT_COPY DROP CONSTRAINT LOCATION_ID_CHK; 
ALTER TABLE DEPT_COPY DROP CONSTRAINT DEPT_TITLE_U; 

ALTER TABLE DEPT_COPY MODIFY DEPT_TITLE NOT NULL;
ALTER TABLE DEPT_COPY MODIFY DEPT_TITLE CONSTRAINT SYS_C007550 NULL;

-- 테이블 제약 조건 확인
SELECT * 
FROM USER_CONSTRAINTS C1
JOIN USER_CONS_COLUMNS C2 USING(CONSTRAINT_NAME)
WHERE C1.TABLE_NAME = 'DEPT_COPY';


---------------------------------------------------------------------------------
-- 2. 컬럼 추가/수정/삭제

-- 컬럼 추가 : ALTER TABLE 테이블명 ADD(컬럼명 데이터타입 [DEFAULT '값']);


-- 컬럼 수정 : ALTER TABLE 테이블명 MODIFY 컬럼명 데이터타입;   (데이터 타입 변경)
--             ALTER TABLE 테이블명 MODIFY 컬럼명 DEFAULT '값'; (기본값 변경)

--> ** 데이터 타입 수정 시 컬럼에 저장된 데이터 크기 미만으로 변경할 수 없다.


-- 컬럼 삭제 : ALTER TABLE 테이블명 DROP (삭제할컬럼명);
--             ALTER TABLE 테이블명 DROP COLUMN 삭제할컬럼명;

--> ** 테이블에는 최소 1개 이상의 컬럼이 존재해야 되기 때문에 모든 컬럼 삭제 X

-- 테이블이란? 행과 열로 이루어진 데이터베이스의 가장 기본적인 객체

-- (추가)
-- DEPT_COPY 테이블에 DEPT_NAME VARCHAR2(20) 컬럼 추가


SELECT * FROM DEPT_COPY;

ALTER TABLE DEPT_COPY ADD(DEPT_NAME VARCHAR2(20));

-- (추가)
-- LO_NAME VARCHAR2(30) 기본값 '한국' 컬럼 추가
ALTER TABLE DEPT_COPY ADD(LO_NAME VARCHAR2(30) DEFAULT '한국');
SELECT * FROM DEPT_COPY; -- LO_NAME 컬럼 추가, 컬럼 값 모두 '한국'으로 됨

-- (수정)
-- DEPT_COPY 테이블의 DEPT_ID 컬럼의 데이터 타입을 CHAR(2) -> VARCHAR(3)로 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_ID VARCHAR2(3);

-- ( 수정 시 에러 발생)
-- DEPT_TITLE 컬럼의 데이터 타입을 VARCHAR2(10)으로 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_TITLE VARCHAR2(10);
-- ORA-01441: 일부 값이 너무 커서 열 길이를 줄일 수 없음

-- (기본값 수정)
-- LO_NAME 기본값 '한국' -> '대한민국'으로 변경
ALTER TABLE DEPT_COPY MODIFY LO_NAME DEFAULT '대한민국';

SELECT * FROM DEPT_COPY; --> 기본값을 변경했지만 기존에 저장된 값이 변경되진 않음

-- 컬럼값 변경 시 UPDATE 사용!
UPDATE DEPT_COPY SET LO_NAME = '대한민국';
UPDATE DEPT_COPY SET LO_NAME = DEFAULT; -- DEFAULT를 이용한 데이터 수정 방법

ROLLBACK;

-- (삭제)
-- 추가한 DEPT_NAME, LO_NAME  컬럼 삭제

-- 컬럼 삭제 : ALTER TABLE 테이블명 DROP (삭제할컬럼명);
--             ALTER TABLE 테이블명 DROP COLUMN 삭제할컬럼명;
ALTER TABLE DEPT_COPY DROP (DEPT_NAME);
ALTER TABLE DEPT_COPY DROP (LO_NAME);

SELECT * FROM DEPT_COPY; -- 컬럼 3개 확인

ALTER TABLE DEPT_COPY DROP COLUMN DEPT_TITLE;
ALTER TABLE DEPT_COPY DROP COLUMN LOCATION_ID;
SELECT * FROM DEPT_COPY; -- 컬럼 1개 확인

-- (컬럼 삭제 시 문제점)
-- 마지막 남은 DEPT_ID 컬럼 삭제
ALTER TABLE DEPT_COPY DROP COLUMN DEPT_ID; -- 삭제 실패
-- ORA-12983: 테이블에 모든 열들을 삭제할 수 없습니다

ROLLBACK; -- 트랜잭션( DML을 이용한 데이터 변경 사항을 저장)을 삭제하고 마지막 커밋 상태로 돌아감

SELECT * FROM DEPT_COPY;
--> ROLLBACK 안됨!
--> 왜? CREATE / ALTER / DROP 같은 DDL은 ROLLBACK의 대상이 아님!!

INSERT INTO DEPT_COPY VALUES('D0'); -- D0 삽입
SELECT * FROM DEPT_COPY;
ROLLBACK; -- 트랜잭션에서 'D0' INSERT 구문 삭제
SELECT * FROM DEPT_COPY;

INSERT INTO DEPT_COPY VALUES('D0'); -- D0 삽입
SELECT * FROM DEPT_COPY;

-- DEPT_ID 데이터 타입을 VARCHAR2(4)로 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_ID VARCHAR2(4); --DDL 수행
ROLLBACK;
SELECT * FROM DEPT_COPY; -- 'D0'가 사라지지 않음

-- * DDL / DML을 혼용해서 사용할 경우 발생하는 문제점
-- DML을 수행하여 트랜잭션에 변경사항이 저장된 상태에서
-- COMMIT / ROLLBACK 없이 DDL 구문을 수행하게되면
-- DDL 수행 동시에 선행 DML이 자동으로 COMMIT 되어버림

--> 결론 : DDL / DML 혼용해서 사용하지 말자

----------------------------------------------------------------------------------------------------------------------

-- 3. 테이블 삭제
-- [작성법]
-- DROP TABLE 테이블명 [ CASCADE CONSTRAINTS];


CREATE TABLE TB1(
    TB1_PK NUMBER PRIMARY KEY,
    TB1_COL NUMBER
);

CREATE TABLE TB2(
    TB2_PK NUMBER PRIMARY KEY,
    TB2_COL NUMBER REFERENCES TB1 -- TB1 테이블의 PK 값을 참조
);

-- 일반 삭제(DEPT_COPY)

DROP TABLE DEPT_COPY;

-- * 관계가 형성된 테이블 중 부모 테이블(TB1)
DROP TABLE TB1;
-- ORA-02449: 외래 키에 의해 참조되는 고유/기본 키가 테이블에 있습니다
--> 다른 테이블이 TB1 테이블을 참조하고 있어서 삭제 불가

-- 해결 방법 1: 자식 -> 부모 테이블 순서로 삭제
DROP TABLE TB2;
DROP TABLE TB1; -- 삭제 성공

-- 해결 방법 2 : CASCADE CONSTRAINTS 옵션 사용
--> 제약조건까지 모두 삭제
-- 원래는 FK 제약조건으로 인해 삭제가 불가능 하지만,
-- 제약조건을 없애버려서 FK 관계를 해제

DROP TABLE TB1 CASCADE CONSTRAINTS; -- 삭제 성공
DROP TABLE TB2;

-------------------------------------------------------------------------------------

-- 4 . 컬럼, 제약조건, 테이블 이름 변경(RENAME)

-- 1) 컬럼명 변경 : ALTER TABLE 테이블명 RENAME COLUMN 컬럼명 TO 변경명;
-- 2) 제약조건명 변경 : ALTER TABLE 테이블명 RENAME CONSTRAINT 제약조건명 TO 변경명;
-- 3) 테이블명 변경 : ALTER TABLE 테이블명 RENAME TO 변경명;

-- 테이블 복사
CREATE TABLE D_COPY AS SELECT * FROM DEPARTMENT;

-- 복사한 테이블에 PK 추가

ALTER TABLE D_COPY ADD CONSTRAINT PK_DEPT_ID PRIMARY KEY(DEPT_ID);

-- 컬럼명 DEPT_TITLE -> DEPT_NAME으로 변경
ALTER TABLE D_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;
-- 제약조건 PK_DEPT_ID -> DEPT_COPY_PK로 변경
ALTER TABLE D_COPY RENAME CONSTRAINT PK_DEPT_ID TO DEPT_COPY_PK;
-- 테이블명 D_COPY -> DCOPY로 변경
ALTER TABLE D_COPY RENAME TO DCOPY;

