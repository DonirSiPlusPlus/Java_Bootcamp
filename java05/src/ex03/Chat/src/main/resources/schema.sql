DROP SCHEMA if EXISTS chat CASCADE;

CREATE SCHEMA chat;


CREATE TABLE chat.users (
    id SERIAL PRIMARY KEY,
    login varchar(255) UNIQUE,
    password varchar(255),
    created_rooms varchar(255)[],
    user_in_rooms varchar(255)[]
);

CREATE TABLE chat.chatrooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    owner VARCHAR(255) REFERENCES chat.users(login),
    messages TEXT[]
);

CREATE TABLE chat.messages (
    id SERIAL PRIMARY KEY,
    author VARCHAR(255) REFERENCES chat.users(login),
    chatroom VARCHAR(255) REFERENCES chat.chatrooms(name),
    message TEXT,
    date TIMESTAMP
);
