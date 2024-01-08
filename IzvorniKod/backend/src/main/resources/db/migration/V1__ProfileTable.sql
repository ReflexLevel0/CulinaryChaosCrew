CREATE TABLE profile
(
    email VARCHAR NOT NULL,
    name VARCHAR,
    surname VARCHAR,
    age INT,
    userID VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    username VARCHAR NOT NULL,
    PRIMARY KEY (userID),
    UNIQUE (email),
    UNIQUE (username)
);
