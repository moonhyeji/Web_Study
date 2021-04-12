DROP SEQUENCE MYNOSEQ;
DROP TABLE MYMEMBER;

CREATE SEQUENCE MYNOSEQ;

--번호, 아이디, 비밀번호, 이름, 주소 , 전화번호
--이메일, 가입여부(Y:가입, N:탈퇴), 등급 (ADMIN,USER...)

CREATE TABLE MYMEMBER(
   MYNO NUMBER NOT NULL,
   MYID VARCHAR2(1000) NOT NULL,
   MYPW VARCHAR2(1000) NOT NULL,
   MYNAME VARCHAR2(500) NOT NULL,
   MYADDR VARCHAR2(2000) NOT NULL,
   MYPHONE VARCHAR2(20) NOT NULL,
   MYEMAIL VARCHAR2(100) NOT NULL,
   MYENABLED VARCHAR2(2) NOT NULL,
   MYROLE VARCHAR2(200) NOT NULL,
   
   
   --*제약조건 걸기 ! *
   --MYNO에 기본키
   CONSTRAINT MYMEMBER_MYNO_PK PRIMARY KEY(MYNO),
   --MYID에 UNIQUE
   CONSTRAINT MYMEMEBER_MYID_UQ UNIQUE(MYID),
   --MYPHONE에 UNIQue
   CONSTRAINT MYMEMEBER_MYPHONE_UQ UNIQUE(MYPHONE),
   --MYEMIAL에 UNIQue
   CONSTRAINT MYMEMEBER_MYEMAIL_UQ UNIQUE(MYEMAIL),
   --MYENABLED에 Y/N 만 (CHECK)
   CONSTRAINT MYMEMEBER_MEMBER_CHK CHECK(MYENABLED IN('Y','N'))
    
   );
   
   INSERT INTO MYMEMBER
   VALUES(MYNOSEQ.NEXTVAL, 'admin','admin1234','관리자','서울시 강남구','010-0000-0000','ADMIN@COM','Y','ADMIN');
   
   
   
   SELECT MYNO, MYID, MYPW, MYNAME, MYADDR,MYPHONE,MYEMAIL,MYENABLED ,MYROLE
   FROM MYMEMBER
   ORDER BY MYNO DESC;
   
   
    
   
   
   
   