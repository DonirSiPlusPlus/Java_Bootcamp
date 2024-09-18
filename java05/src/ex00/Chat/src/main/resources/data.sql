INSERT INTO chat.users (login, password, created_rooms, user_in_rooms)
VALUES
    ('birdpers', '123', '{"3DViewer", "devOps"}', '{"3DViewer", "devOps", "java"}'),
    ('bizarrol', '321', '{"smartCalc"}', '{"smartCalc"}'),
    ('annemari', 'qwerty', '{"containers"}', '{"containers", "devOps"}'),
    ('egregor', 'asdf', '{"java"}', '{"java", "smartCalc", "sql"}'),
    ('hbilly', 'ololoo', '{"sql"}', '{"sql", "3DViewer"}');

INSERT INTO chat.chatrooms (name, owner, messages)
VALUES 
    ('3DViewer', 'birdpers', '{"getting cocky"}'),
    ('smartCalc', 'bizarrol', '{"why do you come?", "me?"}'),
    ('containers', 'annemari', null),
    ('java', 'egregor', '{"well come"}'),
    ('sql', 'hbilly', '{"dig"}'),
    ('devOps', 'birdpers', '{"You can be come"}');

INSERT INTO chat.messages (author, chatroom, message, date)
VALUES
    ('birdpers', 'java', 'well come!', '2024-04-04 00:12:10'),
    ('bizarrol', 'smartCalc', 'why do you come?', '2024-04-09 00:20:01'),
    ('egregor', 'smartCalc', 'me?', '2024-04-09 00:20:02'),
    ('annemari', 'devOps', 'You can be come', '2024-04-09 00:20:09'),
    ('egregor', 'sql', 'dig', '2024-04-09 00:09:04'),
    ('hbilly', '3DViewer', 'getting cocky', '2024-04-09 00:07:47');

-- SELECT * FROM chat.users;
-- SELECT * FROM chat.chatrooms;
-- SELECT * FROM chat.messages ORDER BY 1;