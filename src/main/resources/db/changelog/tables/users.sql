create table users(
    id BIGSERIAL PRIMARY KEY,
    email varchar (256) not null,
    password varchar (256) not null
);

insert into users (email, password) VALUES ('user1@yandex.ru','user123');

insert into users (email, password) VALUES ('user2@yandex.ru','user456');