drop table if exists broad_cast_table CASCADE;

create table broad_cast_table
(
    broadcast_id     bigint NOT NULL AUTO_INCREMENT,
    broadcast_state varchar(255),
    create_at        timestamp,
    modified_at      timestamp,
    publisher_id     bigint,
    primary key (broadcast_id)
);