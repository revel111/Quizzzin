INSERT INTO "APPUSER" (name, surname, nickname, email, date_of_birth, password, is_verified)
VALUES ('hoe', 'hoe', 'hoe', 'hoe@gmail.com', '2011-01-01',
        '$2a$10$SkaiWGY23HeNo0yyqxtdjuxMV1DaHAeuQcTu77TMsBJd16p2GwDRm', TRUE),
       ('bitch', 'hoe', 'bitch', 'bitch@gmail.com', '2001-11-11',
        '$2a$10$SkaiWGY23HeNo0yyqxtdjuxMV1DaHAeuQcTu77TMsBJd16p2GwDRm', TRUE),
       ('idiot', 'fool', 'fool', 'fool@gmail.com', '2001-11-09',
        '$2a$10$SkaiWGY23HeNo0yyqxtdjuxMV1DaHAeuQcTu77TMsBJd16p2GwDRm', TRUE);

INSERT INTO "APPUSER_AUTHORITY" (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3);