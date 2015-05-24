insert into users ( username, password, enabled ) values ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', true);
insert into users ( username, password, enabled ) values ('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', true);

insert into authorities ( username, authority ) values ('admin', 'ROLE_ADMIN');
insert into authorities ( username, authority ) values ('admin', 'ROLE_USER');
insert into authorities ( username, authority ) values ('user', 'ROLE_USER');
