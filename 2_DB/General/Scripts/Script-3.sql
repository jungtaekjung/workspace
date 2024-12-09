SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE FROM EMPLOYEE;

SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME FROM EMPLOYEE JOIN JOB USING (JOB_CODE) WHERE SALARY>5000000 ORDER BY SALARY DESC;


SELECT EMP_ID, EMP_NAME,SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
JOIN JOB USING (JOB_CODE)
WHERE DEPT_TITLE = 'dept'
ORDER BY DEPT_CODE;

SELECT * FROM EMPLOYEE
WHERE EMP_ID = 200;

SELECT * FROM EMPLOYEE
WHERE SALARY >= 100000;

DELETE  FROM EMPLOYEE2
WHERE EMP_ID = 242;


-- * 여러 컬럼을 한번에 수정할 시 콤마(,)로 컬럼을 구분
-- 'D9', 전략기획팀 -> 'D0', 전략기획2팀으로 수정
UPDATE DEPARTMENT2
SET DEPT_ID = 'D0', 
DEPT_TITLE = '전략기획2팀'
WHERE DEPT_ID = 'D9'
AND DEPT_TITLE = '전략기획팀';

SELECT * FROM DEPARTMENT2;

UPDATE EMPLOYEE2 SET EMAIL ='dkdlslsk@naver.com', PHONE = '01087878787', SALARY = 500000 WHERE EMP_ID = 200;

SELECT* FROM EMPLOYEE2;
ROLLBACK;
UPDATE EMPLOYEE2 SET ENT_YN = 'Y'
WHERE EMP_ID = 900;
COMMIT;

UPDATE EMPLOYEE2 SET BONUS = 0.5 WHERE DEPT_CODE = 'D1';
ROLLBACK;

