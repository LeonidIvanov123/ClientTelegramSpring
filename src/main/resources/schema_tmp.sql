create table if not exists telegram_update(
    update_id bigint PRIMARY KEY
    );

create table if not exists telegram_user(
    id bigint PRIMARY KEY,
    first_name varchar(100),
    last_name varchar(100),
    username varchar(100));

create table if not exists telegram_chat(
    id bigint PRIMARY KEY,
    first_name varchar(100),
    last_name varchar(100),
    username varchar(100),
    type varchar(50));

create table if not exists telegram_message(
    message_id bigint PRIMARY KEY,
    senddate varchar(50),
    from_user bigint,
    chat bigint);
