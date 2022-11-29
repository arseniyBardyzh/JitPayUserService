DROP TABLE IF EXISTS LOCATION;
DROP TABLE IF EXISTS DOMAIN_USER;


CREATE TABLE DOMAIN_USER (
                             ID VARCHAR(64) PRIMARY KEY,
                             EMAIL VARCHAR(255),
                             FIRST_NAME VARCHAR(255) NOT NULL,
                             LAST_NAME VARCHAR(255) NOT NULL,
                             UPDATE_TIME TIMESTAMP
);

CREATE TABLE LOCATION (
                          ID VARCHAR(64) PRIMARY KEY NOT NULL,
                          USER_ID VARCHAR(64) NOT NULL,
                          LATITUDE NUMERIC,
                          LONGITUDE NUMERIC,
                          TIME_STAMP TIMESTAMP NOT NULL,
                          foreign key (USER_ID) references DOMAIN_USER(ID)
);