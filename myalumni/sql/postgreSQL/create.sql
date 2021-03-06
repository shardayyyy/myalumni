CREATE TABLE MYALUMNI_SYSTEM_GROUP_TBL (
  XLATGROUP_ID VARCHAR(40)   NOT NULL ,
  DESCRIPTION VARCHAR(150)   NOT NULL ,
  MINLEN INTEGER   NOT NULL ,
  MAXLEN INTEGER   NOT NULL ,
  UPDATEABLE CHAR(1)   NOT NULL   ,
PRIMARY KEY(XLATGROUP_ID)  );


CREATE UNIQUE INDEX MYALUMNI_SYSTEM_GROUP_TBL_index4300 ON MYALUMNI_SYSTEM_GROUP_TBL (XLATGROUP_ID);




CREATE TABLE MYALUMNI_SYSCONFIG_TBL (
  SYSCONFIG_ID VARCHAR(40)   NOT NULL ,
  SESSION_TIMEOUT VARCHAR(6)    ,
  RSS_HEADER VARCHAR(35)    ,
  RSS_URL VARCHAR(250)    ,
  SERVER_URL VARCHAR(250)    ,
  ALBUM_URL VARCHAR(250)    ,
  FORUM_URL VARCHAR(250)    ,
  ORG_FIRST_YEAR INTEGER    ,
  ORG_NAME VARCHAR(40)    ,
  ORG_SHORT_NAME VARCHAR(20)    ,
  ORG_EMAIL VARCHAR(100)    ,
  ORG_ABOUT_US TEXT    ,
  ORG_INTRO TEXT    ,
  HAS_DORMITORY CHAR(1)    ,
  BIRTHDAY_NOTIFICATION CHAR(1)    ,
  TWITTER_USER VARCHAR(40)    ,
  TWITTER_PSWD VARCHAR(40)    ,
  LASTMODIFICATION CHAR(1)    ,
  LOGO_FILE_NAME VARCHAR(200)    ,
  LASTMODIFIED_BY VARCHAR(40)    ,
  LASTMODIFIED_DATE TIMESTAMP      ,
PRIMARY KEY(SYSCONFIG_ID));




CREATE TABLE MYALUMNI_PRIVATE_MESSAGE_TBL (
  MESSAGE_ID VARCHAR(40)   NOT NULL ,
  MESSAGE_FROM_USER_ID VARCHAR(40)   NOT NULL ,
  MESSAGE_TO_USER_ID VARCHAR(40)   NOT NULL ,
  FOLDER_NAME VARCHAR(30)   NOT NULL ,
  PRIORITY CHAR(1)   NOT NULL ,
  SUBJECT VARCHAR(60)   NOT NULL ,
  MESSAGE_DATE TIMESTAMP   NOT NULL ,
  IP_ADDRESS VARCHAR(40)   NOT NULL ,
  MESSAGE_TEXT VARCHAR(4000)   NOT NULL ,
  MESSAGE_STATUS CHAR(1)   NOT NULL ,
  GUEST_EMAIL VARCHAR(50)    ,
  GUEST_NAME VARCHAR(40)    ,
  COPY_ME CHAR(1)   NOT NULL ,
  TO_WEBMASTER CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(MESSAGE_ID)  );


CREATE INDEX MYALUMNI_PRIVATE_MESSAGE_TBL_index4284 ON MYALUMNI_PRIVATE_MESSAGE_TBL (MESSAGE_ID, MESSAGE_STATUS, MESSAGE_FROM_USER_ID, MESSAGE_TO_USER_ID);




CREATE TABLE MYALUMNI_SCROLL_TBL (
  SCROLL_ID VARCHAR(40)   NOT NULL ,
  SCROLL_TEXT VARCHAR(200)   NOT NULL ,
  PRIORITY CHAR(1)   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(SCROLL_ID)  );


CREATE UNIQUE INDEX MYALUMNI_SCROLL_TBL_index4298 ON MYALUMNI_SCROLL_TBL (SCROLL_ID);




CREATE TABLE MYALUMNI_REMINISCE_TBL (
  REMINISCE_ID VARCHAR(40)   NOT NULL ,
  FROM_YEAR VARCHAR(4)    ,
  TO_YEAR VARCHAR(4)    ,
  SLANG VARCHAR(200)   NOT NULL ,
  PRONOUNCE VARCHAR(200)   NOT NULL ,
  MEANING VARCHAR(2000)   NOT NULL ,
  AUTHOR_ID VARCHAR(40)   NOT NULL ,
  REMINISCE_STATUS CHAR(1)   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(REMINISCE_ID));




CREATE TABLE MYALUMNI_ERRORLOG_TBL (
  ERRORLOG_ID VARCHAR(40)   NOT NULL ,
  ERROR_MESSAGE VARCHAR(4000)    ,
  STACK_TRACE VARCHAR(4000)    ,
  ERROR_DATE TIMESTAMP    ,
  CAUSE VARCHAR(1000)    ,
  LOGGED_BY VARCHAR(40)    ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(ERRORLOG_ID));




CREATE TABLE MYALUMNI_CLASS_NEWS_TBL (
  CLASS_NEWS_ID VARCHAR(40)   NOT NULL ,
  SUBJECT VARCHAR(50)   NOT NULL ,
  NEWS VARCHAR(4000)   NOT NULL ,
  FROM_CLASS_YEAR INTEGER   NOT NULL ,
  TO_CLASS_YEAR INTEGER   NOT NULL ,
  AUTHOR_ID VARCHAR(40)   NOT NULL ,
  NEWS_STATUS CHAR(1)   NOT NULL ,
  SYSTEM_NEWS CHAR(1)   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(CLASS_NEWS_ID)  );


CREATE UNIQUE INDEX MYALUMNI_CLASS_NEWS_TBL_index4296 ON MYALUMNI_CLASS_NEWS_TBL (CLASS_NEWS_ID);




CREATE TABLE MYALUMNI_FRONTPAGELINK_TBL (
  LINK_ID VARCHAR(40)   NOT NULL ,
  LINK_LABEL VARCHAR(40)    ,
  LINK_URL VARCHAR(200)    ,
  IMPORTANT CHAR(1)    ,
  LINK_STATUS CHAR(1)    ,
  LASTMODIFICATION CHAR(1)    ,
  LASTMODIFIED_BY VARCHAR(40)    ,
  LASTMODIFIED_DATE TIMESTAMP      ,
PRIMARY KEY(LINK_ID));




CREATE TABLE MYALUMNI_MEMBERS_TBL (
  MEMBER_ID VARCHAR(40)   NOT NULL ,
  USER_NAME VARCHAR(40)   NOT NULL ,
  MEMBER_STATUS VARCHAR(20)   NOT NULL ,
  MEMBER_PASSWORD VARCHAR(40)   NOT NULL ,
  EMAIL VARCHAR(60)    ,
  FIRST_IP_ADDRESS VARCHAR(40)   NOT NULL ,
  LAST_IP_ADDRESS VARCHAR(40)   NOT NULL ,
  CREATION_DATE TIMESTAMP   NOT NULL ,
  LAST_LOGON_DATE TIMESTAMP   NOT NULL ,
  ACTIVATION_CODE VARCHAR(40)    ,
  SIGNATURE VARCHAR(70)    ,
  TITLE VARCHAR(30)   NOT NULL ,
  AVATAR VARCHAR(200)    ,
  FIRST_NAME VARCHAR(70)   NOT NULL ,
  LAST_NAME VARCHAR(70)   NOT NULL ,
  MAIDEN_NAME VARCHAR(70)    ,
  NICK_NAME VARCHAR(70)    ,
  GENDER CHAR(1)   NOT NULL ,
  ADDRESS VARCHAR(200)    ,
  FIRST_EMAIL VARCHAR(60)   NOT NULL ,
  COUNTRY VARCHAR(70)   NOT NULL ,
  PHONE VARCHAR(200)    ,
  OCCUPATION VARCHAR(100)    ,
  WEBSITE VARCHAR(200)    ,
  FAV_URL_1 VARCHAR(200)    ,
  FAV_URL_2 VARCHAR(200)    ,
  YEAR_IN INTEGER   NOT NULL ,
  YEAR_OUT INTEGER   NOT NULL ,
  IS_ADMIN CHAR(1)   NOT NULL ,
  DORMITORY VARCHAR(40)    ,
  COMMENTS VARCHAR(1000)    ,
  ADMIN_COMMENTS VARCHAR(1000)    ,
  DOB DATE    ,
  HIDE_EMAIL CHAR(1)   NOT NULL ,
  HIDE_ADDRESS CHAR(1)   NOT NULL ,
  HIDE_PHONE CHAR(1)   NOT NULL ,
  HIDE_IM CHAR(1)   NOT NULL ,
  PROMPT_CHANGE CHAR(1)   NOT NULL ,
  LASTMODIFICATION VARCHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(MEMBER_ID)      );


CREATE INDEX MYALUMNI_MEMBERS_TBL_index4278 ON MYALUMNI_MEMBERS_TBL (USER_NAME);
CREATE INDEX MYALUMNI_MEMBERS_TBL_index4279 ON MYALUMNI_MEMBERS_TBL (MEMBER_ID);
CREATE UNIQUE INDEX MYALUMNI_MEMBERS_TBL_index4289 ON MYALUMNI_MEMBERS_TBL (USER_NAME, MEMBER_ID);




CREATE TABLE MYALUMNI_LOGINHISTORY_TBL (
  LOGINHISTORY_ID VARCHAR(40)   NOT NULL ,
  REQUEST_TIME TIMESTAMP    ,
  USER_NAME VARCHAR(40)    ,
  SOURCE_IP VARCHAR(50)   NOT NULL ,
  LOGIN_STATUS CHAR(1)   NOT NULL ,
  USER_AGENT VARCHAR(15)    ,
  REASON_CODE CHAR(2)   NOT NULL   ,
PRIMARY KEY(LOGINHISTORY_ID));




CREATE TABLE MYALUMNI_SYSTEM_LOOKUP_TBL (
  LOOKUP_ID VARCHAR(40)   NOT NULL ,
  XLATGROUP_ID VARCHAR(40)   NOT NULL ,
  THE_LABEL VARCHAR(150)   NOT NULL ,
  THE_STATUS CHAR(1)   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(LOOKUP_ID)    ,
  FOREIGN KEY(XLATGROUP_ID)
    REFERENCES MYALUMNI_SYSTEM_GROUP_TBL(XLATGROUP_ID));


CREATE INDEX MYALUMNI_SYSTEM_LOOKUP_TBL_index4286 ON MYALUMNI_SYSTEM_LOOKUP_TBL (LOOKUP_ID);
CREATE UNIQUE INDEX MYALUMNI_SYSTEM_LOOKUP_TBL_index4302 ON MYALUMNI_SYSTEM_LOOKUP_TBL (LOOKUP_ID);


CREATE INDEX IFK_Rel_05 ON MYALUMNI_SYSTEM_LOOKUP_TBL (XLATGROUP_ID);


CREATE TABLE MYALUMNI_MESSAGE_FOLDER_TBL (
  MESSAGE_FOLDER_ID VARCHAR(40)   NOT NULL ,
  MEMBER_ID VARCHAR(40)   NOT NULL ,
  FOLDER_NAME VARCHAR(30)   NOT NULL ,
  FOLDER_ORDER INTEGER   NOT NULL ,
  FOLDER_CREATION_DATE TIMESTAMP   NOT NULL ,
  FOLDER_MODIFICATION_DATE TIMESTAMP   NOT NULL ,
  LASTMODIFICATION CHAR(1)   NOT NULL ,
  LASTMODIFIED_BY VARCHAR(40)   NOT NULL ,
  LASTMODIFIED_DATE TIMESTAMP   NOT NULL   ,
PRIMARY KEY(MESSAGE_FOLDER_ID)  ,
  FOREIGN KEY(MEMBER_ID)
    REFERENCES MYALUMNI_MEMBERS_TBL(MEMBER_ID));


CREATE UNIQUE INDEX MYALUMNI_MESSAGE_FOLDER_TBL_index4291 ON MYALUMNI_MESSAGE_FOLDER_TBL (FOLDER_NAME, MEMBER_ID);


CREATE INDEX IFK_Rel_09 ON MYALUMNI_MESSAGE_FOLDER_TBL (MEMBER_ID);


CREATE TABLE MYALUMNI_MEMBER_INSTANT_MESSENGER_TBL (
  MEMBER_IM_ID VARCHAR(45)   NOT NULL ,
  LOOKUP_ID VARCHAR(40)   NOT NULL ,
  MEMBER_ID VARCHAR(40)   NOT NULL   ,
PRIMARY KEY(MEMBER_IM_ID),
  FOREIGN KEY(MEMBER_ID)
    REFERENCES MYALUMNI_MEMBERS_TBL(MEMBER_ID),
  FOREIGN KEY(LOOKUP_ID)
    REFERENCES MYALUMNI_SYSTEM_LOOKUP_TBL(LOOKUP_ID));


CREATE INDEX IFK_Rel_03 ON MYALUMNI_MEMBER_INSTANT_MESSENGER_TBL (MEMBER_ID);
CREATE INDEX IFK_Rel_04 ON MYALUMNI_MEMBER_INSTANT_MESSENGER_TBL (LOOKUP_ID);


