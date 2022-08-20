--role
create table if not exists roles
(
    role_id bigserial primary key not null,
    name    varchar
);


--User
create table if not exists users
(
    user_id  bigserial primary key not null,

    login    varchar(16) unique    not null,
    password varchar               not null,
    email    varchar,
    name     varchar               not null,
    surname  varchar               not null
);


create table if not exists users_roles
(
    role_id bigint references roles (role_id),
    user_id bigint references users (user_id)
);


create table if not exists users_colleagues
(
    user_id1 bigint references users (user_id) not null,
    user_id2 bigint references users (user_id) not null

);


--direct

create table if not exists chats
(
    chat_id bigserial primary key not null,
    name    varchar(32)           not null,
    dtype   varchar
);
create table if not exists posts
(
    post_id   bigserial primary key not null,
    user_id   bigint references users (user_id),
    title     varchar(64)           not null,
    text      text                  not null,
    post_time timestamp             not null,
    fixed     boolean
);


create table if not exists users_GC
(
    user_id bigint references users (user_id),
    chat_id bigint references chats (chat_id)
);


create table if not exists messages
(
    message_id           bigserial primary key             not null,
    answer_to_message_id bigint references messages (message_id),
    user_id              bigint references users (user_id) not null,
    chat_id              bigint references chats (chat_id) not null,
    text                 text                              not null,
    sending_time         timestamp                         not null,

    checked              boolean                           not null,
    fixed                boolean
);

create table if not exists users_chats
(
    user_id bigint references users (user_id) not null,
    chat_id bigint references chats (chat_id) not null
);


alter table chats
    add user_id_to bigint references users (user_id),
    add post_id    bigint references posts (post_id);

insert into roles(name)
values ('ROLE_USER');
insert into roles(name)
values ('ROLE_MODERATOR');
insert into roles(name)
values ('ROLE_ADMIN');
