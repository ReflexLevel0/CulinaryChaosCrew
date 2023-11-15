CREATE TABLE profile
(
    userId UUID NOT NULL
    username VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    name VARCHAR,
    surname VARCHAR,
    age INT,
    PRIMARY KEY (email, username),
    FOREIGN KEY (username) REFERENCES login(username)
);