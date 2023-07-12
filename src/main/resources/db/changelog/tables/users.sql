create table users(
    id BIGSERIAL PRIMARY KEY,
    login varchar (256) not null,
    password varchar (256) not null
);

insert into users (login, password) VALUES ('user1@yandex.ru','$2y$10$x6pkJCJ3wLzG3cHBFa5eieifaLrUg6dfjlnH6mMnoJuVkfgZj6MYi');

insert into users (login, password) VALUES ('user2@yandex.ru','$2y$10$A3HKkN2KljobzfDqOZKb1OSXjGtx3OpceTrxZJXGCQqLUuaJjzsXu');