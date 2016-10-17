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
CONSTRAINT UK_ENGINEER_ID UNIQUE(ENGINEER_ID),
CONSTRAINT UK_ENGINEER_NAME_PHONE UNIQUE(ENGINEER_NAME, ENGINEER_PHONE));

-- 유지보수 지원 현황 정보 테이블
CREATE TABLE METELSOS_SUPPORT_HISTORY(
SUPPORT_NUM NUMBER PRIMARY KEY,
SUPPORT_TITLE VARCHAR(50) NOT NULL,
COMPANY_NAME VARCHAR(20),
SUPPORT_ENGINEER VARCHAR(10),
SUPPORT_ENGINEER_PHONE VARCHAR(10),
CUSTOMER_NAME VARCHAR(20),
CUSTOMER_PHONE VARCHAR(15),
SUPPORT_ACCEPT_DATE VARCHAR(14) NOT NULL,
SUPPORT_WAY VARCHAR(10),
PURPOSE_OF_VISIT VARCHAR(100),
SUPPORT_DATE VARCHAR(14),
SUPPORT_REQUEST VARCHAR(200),
SUPPORT_RESPONSE VARCHAR(200),
SUPPORT_STATE VARCHAR(10) DEFAULT '접수대기',
SUPPORT_ENGINEER_COMMENT VARCHAR(200),
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