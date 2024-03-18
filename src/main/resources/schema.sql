CREATE TABLE CHAT_ROOM (ROOM_ID INT, CHAT_ROOM_NAME VARCHAR(100), CREATED_BY VARCHAR(100), CREATED_ON TIMESTAMP, PRIMARY KEY (ROOM_ID));

CREATE TABLE CHAT_MESSAGES (ID INT, CLIENT_ID VARCHAR(100), ROOM_ID INT, DATA NVARCHAR(2000), ATTACHMENT_ID VARCHAR(100), CREATED_ON TIMESTAMP, UPDATED_ON TIMESTAMP, PRIMARY KEY (ID));

CREATE TABLE USER_DETAILS (ID INT, NAME VARCHAR(100), MOBILE_NO VARCHAR(15), CREATED_ON TIMESTAMP, UPDATED_ON TIMESTAMP, PRIMARY KEY (ID));