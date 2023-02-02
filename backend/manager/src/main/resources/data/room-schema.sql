drop table if exists room_table CASCADE;
drop table if exists room_info_table CASCADE;

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