CREATE TABLE IF NOT EXISTS `report` (
    id bigint not null auto_increment,
    reported_user varchar(255) not null,
    message varchar(100) not null,
    reason varchar(50) not null,
    create_at timestamp not null,
    update_at timestamp,
    primary key(id)
);
