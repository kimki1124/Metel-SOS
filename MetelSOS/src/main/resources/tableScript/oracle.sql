<!-- ���� ���̺� CREATE-->
CREATE TABLE METELSOS_USER(
USER_NUM NUMBER PRIMARY KEY,
USER_ID VARCHAR(20) NOT NULL,
USER_PASSWD VARCHAR(20) NOT NULL,
USER_NAME VARCHAR(10) NOT NULL,
USER_DEPT VARCHAR(20) NOT NULL,
USER_EMAIL VARCHAR(40) NOT NULL,
USER_PHONE VARCHAR(15) NOT NULL,
USER_CODE VARCHAR(10) NOT NULL,
USER_CREATE_DATE VARCHAR(14) NOT NULL,
LAST_CONNECT_DATE VARCHAR(14) NOT NULL);

<!-- USR_NUM SEQUENCE --> 
CREATE SEQUENCE SEQ_USER_NUM
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

<!-- ADMIN ACCOUNT INSERT TO METELSOS_USER -->
insert into metelsos_user(user_num, user_id, user_passwd, user_name, user_dept, user_email, user_phone, user_code, user_create_date, last_connect_date)
values(seq_user_num.nextval, 'admin', 'metel1', '����', '��������', 'kimki1124@metabuild.co.kr', '010-6822-9671', 'engineer', '20161011005111', '20161011005111');