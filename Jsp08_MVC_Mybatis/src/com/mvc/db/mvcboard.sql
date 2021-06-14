DROP SEQUENCE MVCBOARDSEQ;
DROP TABLE MVCBOARD;

CREATE SEQUENCE MVCBOARDSEQ;

CREATE TABLE MVCBOARD(
   SEQ NUMBER PRIMARY KEY,
   WRITER VARCHAR2(100) NOT NULL,
   TITLE VARCHAR2(1000) NOT NULL,
   CONTENT VARCHAR2(4000) NOT NULL,
   REGDATE DATE NOT NULL
);

INSERT INTO MVCBOARD
VALUES(MVCBOARDSEQ.NEXTVAL, '관리자','MVC재밋다','진짜재밋따.',SYSDATE	);


SELECT SEQ,WRITER,TITLE,CONTENT, REGDATE
FROM MVCBOARD
ORDER BY SEQ DESC;