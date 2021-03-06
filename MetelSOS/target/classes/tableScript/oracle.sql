-- 고객 테이블
CREATE TABLE METELSOS_CUSTOMER(
CUSTOMER_NUM NUMBER PRIMARY KEY,
CUSTOMER_ID VARCHAR(20) NOT NULL,
CUSTOMER_PASSWD VARCHAR(20) NOT NULL,
CUSTOMER_NAME VARCHAR(10) NOT NULL,
COMPANY_NAME VARCHAR(20),
CUSTOMER_POSITION VARCHAR(20) NOT NULL,
CUSTOMER_EMAIL VARCHAR(50) NOT NULL,
CUSTOMER_PHONE VARCHAR(15) NOT NULL,
CUSTOMER_CREATE_DATE VARCHAR(14) NOT NULL,
LAST_CONNECT_DATE VARCHAR(14),
CONSTRAINT UK_CUSTOMER_NAME_PHONE UNIQUE(CUSTOMER_NAME, CUSTOMER_PHONE),
CONSTRAINT UK_CUSTOMER_EMAIL UNIQUE(CUSTOMER_EMAIL),
CONSTRAINT UK_CUSTOMER_ID UNIQUE(CUSTOMER_ID));

-- 고객사 테이블
CREATE TABLE METELSOS_CUSTOMER_COMPANY(
COMPANY_NUM NUMBER PRIMARY KEY,
COMPANY_NAME VARCHAR(20) NOT NULL,
COMPANY_ENG_NAME VARCHAR(30) NOT NULL,
COMPANY_PHONE VARCHAR(15) NOT NULL,
COMPANY_REPRESENTATIVE VARCHAR(10) NOT NULL,
COMPANY_SITE_LOCATION VARCHAR(50) NOT NULL,
COMPANY_CHARGE VARCHAR(10),
COMPANY_CHARGE_PHONE VARCHAR(15),
COMPANY_CHARGE_EMAIL VARCHAR(50),
CONSTRAINT FK_COMPANY_CHARGE FOREIGN KEY(COMPANY_CHARGE, COMPANY_CHARGE_PHONE)
REFERENCES METELSOS_CUSTOMER(CUSTOMER_NAME, CUSTOMER_PHONE),
CONSTRAINT FK_COMPANY_CHARGE_EMAIL FOREIGN KEY(COMPANY_CHARGE_EMAIL)
REFERENCES METELSOS_CUSTOMER(CUSTOMER_EMAIL),
CONSTRAINT UK_COMPANY_NAME UNIQUE(COMPANY_NAME),
CONSTRAINT UK_COMPANY_ENG_NAME UNIQUE(COMPANY_ENG_NAME),
CONSTRAINT UK_COMPANY_PHONE UNIQUE(COMPANY_PHONE));

-- 고객 테이블에서 고객사 테이블의 COMPANY_NAME 컬럼을 참조하는 외래키 제약조건 추가
ALTER TABLE METELSOS_CUSTOMER
ADD CONSTRAINT FK_COMPANY_NAME FOREIGN KEY(COMPANY_NAME)
REFERENCES METELSOS_CUSTOMER_COMPANY(COMPANY_NAME);

-- 본사 엔지니어 테이블
CREATE TABLE METELSOS_ENGINEER(
ENGINEER_NUM NUMBER PRIMARY KEY,
ENGINEER_ID VARCHAR(20) NOT NULL,
ENGINEER_PASSWD VARCHAR(20) NOT NULL,
ENGINEER_NAME VARCHAR(10) NOT NULL,
ENGINEER_DEPT VARCHAR(20) NOT NULL,
ENGINEER_EMAIL VARCHAR(50) NOT NULL,
ENGINEER_PHONE VARCHAR(15) NOT NULL,
ENGINEER_CREATE_DATE VARCHAR(14) NOT NULL,
LAST_CONNECT_DATE VARCHAR(14),
ENGINEER_POSITION VARCHAR(20) NOT NULL,
CONSTRAINT UK_ENGINEER_ID UNIQUE(ENGINEER_ID),
CONSTRAINT UK_ENGINEER_NAME_PHONE UNIQUE(ENGINEER_NAME, ENGINEER_PHONE));

-- 유지보수 지원 현황 정보 테이블
CREATE TABLE METELSOS_SUPPORT_HISTORY(
SUPPORT_NUM NUMBER PRIMARY KEY,
SUPPORT_TITLE VARCHAR(50) NOT NULL,
COMPANY_NAME VARCHAR(20),
SUPPORT_ENGINEER VARCHAR(10),
SUPPORT_ENGINEER_PHONE VARCHAR(15),
CUSTOMER_NAME VARCHAR(20),
CUSTOMER_PHONE VARCHAR(15),
SUPPORT_ACCEPT_DATE VARCHAR(14) NOT NULL,
SUPPORT_WAY VARCHAR(10),
PURPOSE_OF_VISIT CLOB,
SUPPORT_DATE VARCHAR(14),
SUPPORT_REQUEST CLOB,
SUPPORT_RESPONSE CLOB,
SUPPORT_STATE VARCHAR(20) DEFAULT '접수대기',
SUPPORT_ENGINEER_COMMENT CLOB,
CONSTRAINT FK_SUPPORT_COMPANY FOREIGN KEY(COMPANY_NAME)
REFERENCES METELSOS_CUSTOMER_COMPANY(COMPANY_NAME),
CONSTRAINT FK_SUPPORT_ENGINEER FOREIGN KEY(SUPPORT_ENGINEER, SUPPORT_ENGINEER_PHONE)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME, ENGINEER_PHONE),
CONSTRAINT FK_SUPPORT_CUSTOMER FOREIGN KEY(CUSTOMER_NAME, CUSTOMER_PHONE)
REFERENCES METELSOS_CUSTOMER(CUSTOMER_NAME, CUSTOMER_PHONE));

-- 엔지니어 번호 시퀀스
CREATE SEQUENCE SEQ_ENGINEER_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 고객 번호 시퀀스
CREATE SEQUENCE SEQ_CUSTOMER_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 고객사 번호 시퀀스
CREATE SEQUENCE SEQ_COMPANY_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 유지보수 지원 현황 번호 시퀀스
CREATE SEQUENCE SEQ_SUPPORT_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- ADMIN ACCOUNT INSERT
INSERT INTO METELSOS_ENGINEER(ENGINEER_NUM, ENGINEER_ID, ENGINEER_PASSWD, ENGINEER_NAME, ENGINEER_DEPT, ENGINEER_EMAIL, ENGINEER_PHONE, ENGINEER_CREATE_DATE)
VALUES(SEQ_ENGINEER_NUM.NEXTVAL, 'admin', 'metel1', '김기범', '사업수행부', 'kimki1124@metabuild.co.kr', '010-6822-9671', '20161016074335');
COMMIT;

-- CUSTOMER ADMIN ACCOUNT INSERT
INSERT INTO METELSOS_CUSTOMER
VALUES(SEQ_CUSTOMER_NUM.NEXTVAL, 'admin', 'metel2', '김기범', null, '연구원', 'kimki1124@naver.com', '010-6822-9671', '20161016195400', null);

-- COMPANY TEST DATA INSERT
INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '자동차관리정보시스템', 'ECAR', '031-111-1111', '이춘우', '(우)30103 세종특별자치시 도움6로 11');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '국토교통부', 'MOLIT', '031-222-2222', '김흥섭', '(우)30103 세종특별자치시 도움6로 12');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '교통안전공단', 'TS2020', '031-333-3333', '김진영', '경상북도 김천시 혁신6로 17');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '송도 U-city', 'SONGDOUCITY', '031-444-4444', '이승기', '인천광역시 연수구 아트센터대로 175 G-tower 21층');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '국군지휘통신사령부', 'ROKDCC', '031-555-5555', '이현호', '경기도 과천시 별양동 과천우체국 사서함 제69호');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '국민안전처', 'MPSS', '031-666-6666', '강신욱', '세종특별자치시 정부2청사로 13');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '심사평가원', 'HIRA', '031-777-7777', '김준호', '강원도 원주시 혁신로 60');

INSERT INTO METELSOS_CUSTOMER_COMPANY(COMPANY_NUM, COMPANY_NAME, COMPANY_ENG_NAME, COMPANY_PHONE, COMPANY_REPRESENTATIVE, COMPANY_SITE_LOCATION)
VALUES(SEQ_COMPANY_NUM.NEXTVAL, '공정거래위원회', 'FTC', '031-888-8888', '장준환', '세종특별자치시 다솜3로 95');

-- ENGINEER LEFTMENU 테이블
CREATE TABLE METELSOS_ENGINEER_LEFTMENU(
MENU_CODE NUMBER PRIMARY KEY,
MENU_TITLE VARCHAR(50) NOT NULL,
MENU_PARENT_CODE NUMBER,
MENU_SEQ NUMBER NOT NULL,
MENU_LEVEL NUMBER NOT NULL,
MENU_VISIBLE VARCHAR(2) DEFAULT 'Y',
MENU_ICON VARCHAR(50),
MENU_ENG_TITLE VARCHAR(50),
CONSTRAINT FK_MENU_PARENT FOREIGN KEY(MENU_PARENT_CODE)
REFERENCES METELSOS_ENGINEER_LEFTMENU(MENU_CODE),
CONSTRAINT CHECK_MENU_VISIBLE CHECK(MENU_VISIBLE IN('Y','N')));

-- CUSTOMER LEFTMENU 테이블
CREATE TABLE METELSOS_CUSTOMER_LEFTMENU(
MENU_CODE NUMBER PRIMARY KEY,
MENU_TITLE VARCHAR(50) NOT NULL,
MENU_PARENT_CODE NUMBER,
MENU_SEQ NUMBER NOT NULL,
MENU_LEVEL NUMBER NOT NULL,
MENU_VISIBLE VARCHAR(2) DEFAULT 'Y',
MENU_ICON VARCHAR(50),
MENU_ENG_TITLE VARCHAR(50),
CONSTRAINT FK_CUSTOMER_MENU_PARENT FOREIGN KEY(MENU_PARENT_CODE)
REFERENCES METELSOS_CUSTOMER_LEFTMENU(MENU_CODE),
CONSTRAINT CHECK_CUSTOMER_MENU_VISIBLE CHECK(MENU_VISIBLE IN('Y','N')));

-- ENGINEER LEFTMENU MENU_CODE 시퀀스
CREATE SEQUENCE SEQ_ENGINEER_MENU_CODE 
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- CUSTOMER LEFTMENU MENU_CODE 시퀀스
CREATE SEQUENCE SEQ_CUSTOMER_MENU_CODE 
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- ENGINEER LEFTMENU DATA INSERT
INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(0, 'ROOT', NULL, 0, 0, 'N', NULL, NULL);

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '메인화면', 0, 1, 1, 'Y', 'fa fa-lg fa-fw fa-home', 'EngineerMain');

UPDATE METELSOS_ENGINEER_LEFTMENU SET MENU_CODE = 1 WHERE MENU_TITLE = '메인화면';

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.CURRVAL, '지원 업무', 0, 2, 1, 'Y', 'fa fa-lg fa-fw fa-wrench', 'support');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '유지보수 지원 사항', 2, 1, 2, 'Y', NULL, 'SupportList');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '미지원 업무 조회', 2, 2, 2, 'Y', NULL, 'UnSupportList');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '내부 자료', 0, 3, 1, 'Y', 'fa fa-lg fa-fw fa-sticky-note-o', 'innerdata');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '내부 회의록', 5, 1, 2, 'Y', NULL, 'InnerMinutes');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '트러블 슈팅', 5, 2, 2, 'Y', NULL, 'TroubleShooting');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '기술 자료', 5, 3, 2, 'Y', NULL, 'TechData');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, 'BMT', 0, 4, 1, 'Y', 'fa fa-lg fa-fw fa-star', 'bmt');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '2013년', 9, 1, 2, 'Y', NULL, 'Bmt2013');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '2014년', 9, 2, 2, 'Y', NULL, 'Bmt2014');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '2015년', 9, 3, 2, 'Y', NULL, 'Bmt2015');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '2016년', 9, 4, 2, 'Y', NULL, 'Bmt2016');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '엔지니어 검색', 0, 5, 1, 'Y', 'fa fa-lg fa-fw fa-user-md', 'EngineerSearch');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '고객 검색', 0, 6, 1, 'Y', 'fa fa-lg fa-fw fa-users', 'CustomerSearch');

INSERT INTO METELSOS_ENGINEER_LEFTMENU
VALUES(SEQ_ENGINEER_MENU_CODE.NEXTVAL, '고객사 회의록', 2, 3, 2, 'Y', null, 'CustomerMinutes');

-- CUSTOMER LEFTMENU DATA INSERT
INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(0, 'ROOT', NULL, 0, 0, 'N', NULL, NULL);

INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(SEQ_CUSTOMER_MENU_CODE.NEXTVAL, '메인화면', 0, 1, 1, 'Y', 'fa fa-lg fa-fw fa-home', 'CustomerMain');

UPDATE METELSOS_CUSTOMER_LEFTMENU SET MENU_CODE = 1 WHERE MENU_TITLE = '메인화면';

INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(SEQ_CUSTOMER_MENU_CODE.CURRVAL, '지원 요청', 0, 2, 1, 'Y', 'fa fa-lg fa-fw fa-info-circle', 'RequestSupport');

INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(SEQ_CUSTOMER_MENU_CODE.NEXTVAL, '지원 히스토리', 0, 3, 1, 'Y', 'fa fa-lg fa-fw fa-history', 'SupportHistory');

INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(SEQ_CUSTOMER_MENU_CODE.NEXTVAL, '엔지니어 검색', 0, 4, 1, 'Y', 'fa fa-lg fa-fw fa-user-md', 'SearchEngineer');

INSERT INTO METELSOS_CUSTOMER_LEFTMENU
VALUES(SEQ_CUSTOMER_MENU_CODE.NEXTVAL, '자주묻는 질문', 0, 5, 1, 'Y', 'fa fa-lg fa-fw fa-question-circle', 'FAQPage');

-- 고객사 회의록 정보 관리 테이블
CREATE TABLE METELSOS_CSTMR_MTNG_HSTRY(
MEETING_NUM NUMBER PRIMARY KEY,
MEETING_TITLE VARCHAR(100) NOT NULL,
PROJECT_NAME VARCHAR(100) NOT NULL,
COMPANY_NAME VARCHAR(20),
CUSTOMER_NAME VARCHAR(10),
ENGINEER_NAME VARCHAR(10),
MEETING_ACCEPT_DATE VARCHAR(14) NOT NULL,
MEETING_WAY VARCHAR(20),
MEETING_DATE VARCHAR(14),
MEETING_LOCATION VARCHAR(100),
PURPOSE_OF_MEETING VARCHAR(100),
MEETING_CONTENT CLOB,
MEETING_DECISION CLOB,
QUESTION_ANSWER CLOB,
MEETING_STATE VARCHAR(20) DEFAULT '접수대기',
CONSTRAINT FK_MEETING_COMPANY_NAME FOREIGN KEY(COMPANY_NAME)
REFERENCES METELSOS_CUSTOMER_COMPANY(COMPANY_NAME),
CONSTRAINT FK_MEETING_CUSTOMER_NAME FOREIGN KEY(CUSTOMER_NAME)
REFERENCES METELSOS_CUSTOMER(CUSTOMER_NAME),
CONSTRAINT FK_MEETING_ENGINEER_NAME FOREIGN KEY(ENGINEER_NAME)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME));

-- METELSOS_CSTMR_MTNG_HSTRY MEETING_NUM 시퀀스
CREATE SEQUENCE SEQ_CSTMR_MTNG_NUM 
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 내부 회의록 정보 관리 테이블
CREATE TABLE METELSOS_INR_MTNG_HSTRY(
MEETING_NUM NUMBER PRIMARY KEY,
MEETING_TITLE VARCHAR(100) NOT NULL,
MEETING_DEPT VARCHAR(20),
MEETING_ATTENDENT VARCHAR(100) NOT NULL,
MEETING_DATE VARCHAR(14) NOT NULL,
MEETING_LOCATION VARCHAR(100) NOT NULL,
PURPOSE_OF_MEETING VARCHAR(100) NOT NULL,
MEETING_CONTENT CLOB NOT NULL,
MEETING_DECISION CLOB NOT NULL,
MEETING_ETC CLOB,
WRITER_NAME VARCHAR(10),
CONSTRAINT FK_MEETING_DEPT FOREIGN KEY(MEETING_DEPT)
REFERENCES METELSOS_ENGINEER(ENGINEER_DEPT),
CONSTRAINT FK_WRITER_NAME FOREIGN KEY(WRITER_NAME)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME));

-- METELSOS_INR_MTNG_HSTRY MEETING_NUM 시퀀스
CREATE SEQUENCE SEQ_INR_MTNG_NUM 
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 공지사항 정보 관리 테이블
CREATE TABLE METELSOS_NOTICE(
NOTICE_NUM NUMBER PRIMARY KEY,
PARENT_NOTICE_NUM NUMBER,
NOTICE_TITLE VARCHAR(100) NOT NULL,
NOTICE_AUTHOR VARCHAR(20) NOT NULL,
NOTICE_DATE VARCHAR(14) NOT NULL,
NOTICE_CONTENT CLOB NOT NULL,
NOTICE_HIT NUMBER DEFAULT 0 NOT NULL,
NOTICE_DEL_GB VARCHAR(5) DEFAULT 'N' NOT NULL,
CONSTRAINT FK_NOTICE_AUTHOR FOREIGN KEY(NOTICE_AUTHOR)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME));

-- METELSOS_NOTICE NOTICE_NUM 시퀀스
CREATE SEQUENCE SEQ_NOTICE_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 우수사원 정보 관리 테이블
CREATE TABLE METELSOS_EXCLNT_STF(
EXCLNT_YEAR_MONTH DATE NOT NULL,
ENGINEER_NAME VARCHAR(20),
ENGINEER_POSITION VARCHAR(20) NOT NULL,
ENGINEER_DEPT VARCHAR(50),
ENGINEER_IMAGE BLOB,
IMPRESSION_SPEECH CLOB NOT NULL,
CONSTRAINT FK_EXCLNT_STF_NAME FOREIGN KEY(ENGINEER_NAME)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME)
);

-- 신입사원 정보 관리 테이블 
CREATE TABLE METELSOS_NEW_EMPLYD(
NEW_EMPLY_YEAR_MONTH DATE NOT NULL,
ENGINEER_NAME VARCHAR(20),
ENGINEER_POSITION VARCHAR(20) NOT NULL,
ENGINEER_DEPT VARCHAR(50),
ENGINEER_IMAGE BLOB,
IMPRESSION_SPEECH CLOB NOT NULL,
CONSTRAINT FK_NEW_EMPLYD_NAME FOREIGN KEY(ENGINEER_NAME)
REFERENCES METELSOS_ENGINEER(ENGINEER_NAME)
);

-- 공지사항 첨부파일 관리 테이블
CREATE TABLE METELSOS_NOTICE_FILE(
FILE_NUM NUMBER,
BOARD_NUM NUMBER NOT NULL,
ORIGINAL_FILE_NAME VARCHAR2(260 BYTE) NOT NULL,
STORED_FILE_NAME VARCHAR2(36 BYTE) NOT NULL,
FILE_SIZE NUMBER,
CREA_DTM DATE DEFAULT SYSDATE NOT NULL,
CREA_ID VARCHAR2(30 BYTE) NOT NULL,
DEL_GB VARCHAR2(1 BYTE) DEFAULT 'N' NOT NULL,
PRIMARY KEY(FILE_NUM)
);

-- METELSOS_FILE FILE_NUM 시퀀스
CREATE SEQUENCE SEQ_NOTICE_FILE_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- QNA 정보 관리 테이블
CREATE TABLE METELSOS_QNA(
QNA_NUM NUMBER PRIMARY KEY,
QNA_QUESTION CLOB NOT NULL,
QNA_ANSWER CLOB NOT NULL,
QNA_HIT NUMBER DEFAULT 0
);

-- METELSOS_QNA QNA_NUM 시퀀스
CREATE SEQUENCE SEQ_QNA_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-- 지원 요청 첨부파일 정보 관리 테이블
CREATE TABLE METELSOS_SUPPORT_FILE(
FILE_NUM NUMBER,
BOARD_NUM NUMBER NOT NULL,
ORIGINAL_FILE_NAME VARCHAR2(260 BYTE) NOT NULL,
STORED_FILE_NAME VARCHAR2(36 BYTE) NOT NULL,
FILE_SIZE NUMBER,
CREA_DTM DATE DEFAULT SYSDATE NOT NULL,
CREA_ID VARCHAR2(30 BYTE) NOT NULL,
DEL_GB VARCHAR2(1 BYTE) DEFAULT 'N' NOT NULL,
PRIMARY KEY(FILE_NUM)
);

-- METELSOS_SUPPORT_FILE FILE_NUM 시퀀스
CREATE SEQUENCE SEQ_SUPPORT_FILE_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;