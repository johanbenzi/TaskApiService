CREATE TABLE USERS
(
    ID                      BIGINT PRIMARY KEY NOT NULL,
    USERNAME                VARCHAR            NOT NULL,
    CREATED_DATE_TIME       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE USERS_SEQ
    INCREMENT BY 1
    START 1
    CACHE 1
    OWNED BY USERS.ID;

CREATE TABLE TASKS
(
    ID                      BIGINT PRIMARY KEY NOT NULL,
    TITLE                   VARCHAR            NOT NULL,
    DESCRIPTION             VARCHAR            NOT NULL,
    USER_ID                 BIGINT REFERENCES USERS (ID),
    CREATED_DATE_TIME       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE TASKS_SEQ
    INCREMENT BY 1
    START 1
    CACHE 1
    OWNED BY TASKS.ID;
