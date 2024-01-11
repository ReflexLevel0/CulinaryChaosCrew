CREATE TABLE userRelations
(
    userId VARCHAR NOT NULL,
    followerId VARCHAR NOT NULL,
    PRIMARY KEY(userId, followerId),
    FOREIGN KEY (userid) REFERENCES profile(userID) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (followerId) REFERENCES profile(userID) ON DELETE CASCADE ON UPDATE CASCADE
);