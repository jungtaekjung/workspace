SELECT * FROM REVIEW;
SELECT * FROM MEMBER;

SELECT * FROM AUTH_KEY;
SELECT sequence_name FROM user_sequences WHERE sequence_name = 'SEQ_AUTH_KEY_NO';


CREATE SEQUENCE SEQ_AUTH_KEY_NO
  START WITH 4
  INCREMENT BY 1
  NOCACHE;

COMMIT;


ALTER TABLE AUTH_KEY NOPARALLEL;

ALTER TABLE AUTH_KEY NOPARALLEL;
ALTER SESSION DISABLE PARALLEL DML;
ALTER SESSION DISABLE PARALLEL DML;

DROP SEQUENCE SEQ_AUTH_KEY_NO;
CREATE SEQUENCE SEQ_AUTH_KEY_NO
  START WITH 5
  INCREMENT BY 1
  NOCACHE;