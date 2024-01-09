CREATE TABLE likes
(
    userid VARCHAR NOT NULL,
    recipeId VARCHAR NOT NULL,
    PRIMARY KEY(userId, recipeId)
);