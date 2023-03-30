INSERT INTO chat.user (login, password) VALUES ('parina', 'petuhina');
INSERT INTO chat.user (login, password) VALUES ('karinmbo', 'petuhimbo');
INSERT INTO chat.user (login, password) VALUES ('karinda', 'mandarinda');
INSERT INTO chat.user (login, password) VALUES ('karim', 'karim');
INSERT INTO chat.user (login, password) VALUES ('valera', 'password');

INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat 1', 1);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat 2', 2);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat 3', 3);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat 4', 4);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat 5', 5);

INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 1_1', '2022-12-19 10:23:00', 1, 1);
INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 1_2', '2022-10-19 10:24:00', 2, 1);

INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 2_1', '2022-10-19 10:25:00', 2, 2);
INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 2_2', '2022-10-19 10:26:00', 3, 2);

INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 3_1', '2022-10-19 10:27:00', 3, 3);
INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 3_2', '2022-10-19 10:28:00', 4, 3);

INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 4_1', '2022-10-19 10:29:00', 4, 4);
INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 4_2', '2022-10-19 10:30:00', 5, 4);

INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 5_1', '2022-10-19 10:31:00', 5, 5);
INSERT INTO chat.message (message, time, author, room_id) VALUES ('message 5_2', '2022-10-19 10:32:00', 1, 5);