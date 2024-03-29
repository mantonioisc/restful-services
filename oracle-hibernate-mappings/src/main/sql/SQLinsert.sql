--Clean up table data(truncate didn't work due to foreign key constaints)
DELETE FROM USERS_CONSOLES;
DELETE FROM GAMES_CONSOLES;
DELETE FROM USERS_GAMES;
DELETE FROM GAMES_TAGS;
DELETE FROM USERS;
DELETE FROM CONSOLES;
DELETE FROM GAMES;
DELETE FROM DEVELOPERS;
DELETE FROM COMPANIES;
DELETE FROM TAGS;
COMMIT;

--Insets to populate the tables with sample data
INSERT INTO USERS VALUES(USER_ID_SEQ.nextval, 'Marco', 'Delgado','10-AUG-85');
INSERT INTO DEVELOPERS VALUES(DEVELOPER_ID_SEQ.nextval, 'KONAMI', 'Japan', 'http://www.konami.com');
INSERT INTO GAMES VALUES('BLUS30109', 'Metal Gear Solid 4','Lead legendary hero Solid Snake in this final chapter of the Metal Gear Solid saga', 'BluRay', 2008, 849.00, 1, '4600 MB', 'M', 94, DEVELOPER_ID_SEQ.currval);
INSERT INTO DEVELOPERS VALUES(DEVELOPER_ID_SEQ.nextval, 'Sony Santa Monica Studio', 'United States', 'http://www.worldwidestudios.net/santamonica');
INSERT INTO GAMES VALUES('BCUS98111', 'God  Of War III','In the end, there will be only chaos', 'BluRay', 2010, 989.00, 1, '5 MB', 'M', 93, DEVELOPER_ID_SEQ.currval);
INSERT INTO DEVELOPERS VALUES(DEVELOPER_ID_SEQ.nextval, 'CAPCOM', 'Japan', 'http://capcom.com');
INSERT INTO GAMES VALUES('SLUS21115', 'Okami','You possess the power of a god, but face the world in the form of the wolf.', 'DVD', 2006, 232.60, 1, '170 KB', 'T', 93, DEVELOPER_ID_SEQ.currval);
INSERT INTO USERS_GAMES VALUES(USER_ID_SEQ.currval, 'BLUS30109', '01-JUN-08', 'NEW', 'OPENED', 'N');
INSERT INTO USERS_GAMES VALUES(USER_ID_SEQ.currval, 'BCUS98111', '19-MAR-10', 'NEW', 'OPENED', 'N');
INSERT INTO USERS_GAMES VALUES(USER_ID_SEQ.currval, 'SLUS21115', '15-APR-10', 'NEW', 'BRANDNEW', 'Y');

INSERT INTO TAGS VALUES(TAG_ID_SEQ.nextval, 'exclusive');
INSERT INTO GAMES_TAGS(GAME_CODE, TAG_ID) VALUES ('BLUS30109', TAG_ID_SEQ.currval);
INSERT INTO GAMES_TAGS(GAME_CODE, TAG_ID) VALUES ('BCUS98111', TAG_ID_SEQ.currval);
INSERT INTO TAGS VALUES(TAG_ID_SEQ.nextval, '1080p');
INSERT INTO GAMES_TAGS(GAME_CODE, TAG_ID) VALUES ('BLUS30109', TAG_ID_SEQ.currval);
INSERT INTO TAGS VALUES(TAG_ID_SEQ.nextval, '720p');
INSERT INTO GAMES_TAGS(GAME_CODE, TAG_ID) VALUES ('BCUS98111', TAG_ID_SEQ.currval);

INSERT INTO COMPANIES VALUES (COMPANY_ID_SEQ.nextval, 'Sony', 'Sony Computer Entertainment SCE', 'Japan', 'http://www.playstation.com');
INSERT INTO CONSOLES VALUES (CONSOLE_ID_SEQ.nextval, 'PS2', 'PlayStation 2', null, 4000, 4, 'N', 'N', 'N', 'DVD', COMPANY_ID_SEQ.currval);
INSERT INTO USERS_CONSOLES VALUES (USER_ID_SEQ.currval, CONSOLE_ID_SEQ.currval, '1-MAY-01', 'NEW');
INSERT INTO GAMES_CONSOLES VALUES ('SLUS21115', CONSOLE_ID_SEQ.currval);
INSERT INTO CONSOLES VALUES (CONSOLE_ID_SEQ.nextval, 'PS3', 'PlayStation 3', null, 8000, 7, 'Y', 'Y', 'Y', 'BluRay', COMPANY_ID_SEQ.currval);
INSERT INTO USERS_CONSOLES VALUES (USER_ID_SEQ.currval, CONSOLE_ID_SEQ.currval, '1-AUG-07', 'NEW');

INSERT INTO GAMES_CONSOLES VALUES ('BLUS30109', CONSOLE_ID_SEQ.currval);
INSERT INTO GAMES_CONSOLES VALUES ('BCUS98111', CONSOLE_ID_SEQ.currval);
COMMIT;
