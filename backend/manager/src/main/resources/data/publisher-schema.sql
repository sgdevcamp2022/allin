drop table if exists publisher_password_table CASCADE;
drop table if exists publisher_table CASCADE;

create table publisher_password_table
(
    password_id    bigint NOT NULL AUTO_INCREMENT,
    create_at      timestamp,
    password_used  boolean,
    password_value varchar(255),
    publisher_id   bigint,
    primary key (password_id)
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
