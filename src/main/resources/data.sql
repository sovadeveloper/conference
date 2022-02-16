INSERT INTO room (id, number) VALUES(1, 25);
INSERT INTO room (id, number) VALUES(2, 35);
INSERT INTO room (id, number) VALUES(3, 45);

INSERT INTO talk (id, text, title) VALUES (100, 'Какой-то текст', 'Методы HTTP');
INSERT INTO talk (id, text, title) VALUES (101, 'Какой-то текст', '3D графика и анимация');
INSERT INTO talk (id, text, title) VALUES (102, 'Какой-то текст', 'HTML и базы данных');

INSERT INTO schedule(id, start, end, room_id, talk_id) VALUES (1, '2022-02-24 10:30', '2022-02-24 13:30', 1, 100);
INSERT INTO schedule(id, start, end, room_id, talk_id) VALUES (2, '2022-02-23 14:30', '2022-02-23 15:30', 1, 101);
INSERT INTO schedule(id, start, end, room_id, talk_id) VALUES (3, '2022-02-25 16:00', '2022-02-25 18:30', 1, 102);

-- Пароль: 1
INSERT INTO user(id, username, password, active, role)
    VALUES (999, 'admin', '$2a$12$cq71TCSiZmEHhbLTrdjA8O2296KnaChUjA/6kHPxMOmlkVvXTPX6u', true, 'ADMIN');
INSERT INTO user(id, username, password, active, role)
    VALUES (998, 'listener', '$2a$12$cq71TCSiZmEHhbLTrdjA8O2296KnaChUjA/6kHPxMOmlkVvXTPX6u', true, 'LISTENER');
INSERT INTO user(id, username, password, active, role)
    VALUES (997, 'speaker', '$2a$12$cq71TCSiZmEHhbLTrdjA8O2296KnaChUjA/6kHPxMOmlkVvXTPX6u', true, 'SPEAKER');
INSERT INTO user(id, username, password, active, role)
VALUES (996, 'speaker2', '$2a$12$cq71TCSiZmEHhbLTrdjA8O2296KnaChUjA/6kHPxMOmlkVvXTPX6u', true, 'SPEAKER');

INSERT INTO user_talk(user_id, talk_id) VALUES (997, 100);
INSERT INTO user_talk(user_id, talk_id) VALUES (997, 101);
INSERT INTO user_talk(user_id, talk_id) VALUES (997, 102);
INSERT INTO user_talk(user_id, talk_id) VALUES (996, 100);

