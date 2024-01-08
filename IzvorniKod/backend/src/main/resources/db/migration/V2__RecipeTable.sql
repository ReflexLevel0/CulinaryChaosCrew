CREATE TABLE recipe
(
    likes INT DEFAULT 0,
    ingredients VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    instructions VARCHAR NOT NULL,
    origin VARCHAR,
    category VARCHAR NOT NULL,
    specialTag VARCHAR,
    videoURL VARCHAR,
    recipeID VARCHAR NOT NULL,
    imageURL VARCHAR NOT NULL,
    preparationTime INT NOT NULL,
    userID VARCHAR NOT NULL,
    PRIMARY KEY (recipeID),
    FOREIGN KEY (userID) REFERENCES profile(userID)
);