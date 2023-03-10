CREATE TABLE IF NOT EXISTS `report` (
    id bigint not null auto_increment,
    topic_id varchar(32) not null,
    reported_user varchar(8) not null,
    reporter varchar(8) not null,
    message varchar(100) not null,
    reason varchar(50) not null,
    count bigint not null,
    create_at timestamp not null,
    update_at timestamp,
    primary key(id)
);
