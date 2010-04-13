select * from games, developers where games.developer_id=developers.id;
select * from tags, games_tags, games where tags.id = games_tags.tag_id and games_tags.game_code=games.code;
select * from consoles, companies where consoles.company_id = companies.id;
select * from consoles, games_consoles, games where consoles.id=games_consoles.console_id and games_consoles.game_code=games.code;
select * from users, users_consoles, consoles where users.id=users_consoles.user_id and users_consoles.console_id=consoles.id;
select * from users, users_games, games where users.id=users_games.user_id and users_games.game_code=games.code;