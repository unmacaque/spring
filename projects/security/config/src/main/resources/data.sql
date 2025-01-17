create table if not exists users ( username varchar, password varchar, enabled boolean );
create table if not exists authorities ( username varchar, authority varchar );

insert into users ( username, password, enabled ) values ('admin', '{bcrypt}$2a$10$.FfreZq6sZGf10HtoCWzleScSFf8cpBdztKp7UDbPwFALpcmU48Mq', true);
insert into users ( username, password, enabled ) values ('user', '{bcrypt}$2a$10$ht3HlqXDNHQefB253YY9uuXpYAgJPQAuJk2URvheUBKKZ6hrvpd1m', true);

insert into authorities ( username, authority ) values ('admin', 'ROLE_ADMIN');
insert into authorities ( username, authority ) values ('admin', 'ROLE_USER');
insert into authorities ( username, authority ) values ('user', 'ROLE_USER');
