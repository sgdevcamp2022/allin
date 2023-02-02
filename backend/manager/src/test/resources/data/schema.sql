drop table if exists broadcast_table CASCADE;
drop table if exists publisher_password_table CASCADE;
drop table if exists publisher_table CASCADE;
drop table if exists room_table CASCADE;
drop table if exists room_info_table CASCADE;

create table broadcast_table
(
    broadcast_id     bigint NOT NULL AUTO_INCREMENT,
    broadcast_state varchar(255),
    create_at        timestamp,
    modified_at      timestamp,
    publisher_id     bigint,
    primary key (broadcast_id)
);

create table publisher_password_table
(
    publisher_password_id    bigint NOT NULL AUTO_INCREMENT,
    create_at      timestamp,
    publisher_password_used  boolean,
    publisher_password_value varchar(255),
    publisher_id   bigint,
    primary key (publisher_password_id)
);

create table publisher_table
(
    publisher_id  bigint NOT NULL AUTO_INCREMENT,
    publisher_key varchar(255),
    member_id     bigint,
    create_at     timestamp,
    delete_at     timestamp,
    modified_at   timestamp,
    primary key (publisher_id)
);

create table room_table
(
    room_id      bigint NOT NULL AUTO_INCREMENT,
    create_at    timestamp,
    delete_at    timestamp,
    publisher_id bigint,
    primary key (room_id)
);

create table room_info_table
(
    room_info_id          bigint NOT NULL AUTO_INCREMENT,
    room_info_description varchar(255),
    room_info_end_time    time,
    room_info_start_time  time,
    delete_at   timestamp,
    modified_at timestamp,
    room_info_title       varchar(255),
    room_id     bigint,
    primary key (room_info_id)
);
