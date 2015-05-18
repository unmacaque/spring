create table users (
username varchar_ignorecase(50) not null primary key,
password varchar_ignorecase(50) not null,
enabled boolean not null);

create table authorities (
username varchar_ignorecase(50) not null,
authority varchar_ignorecase(50) not null,
constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into users values ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', true);
insert into users values ('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', true);
