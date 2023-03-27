create table if not exists demo
(
    id          int not null primary key auto_increment,
    name        varchar(50),
    create_by   varchar(50),
    create_time datetime,
    update_by   varchar(50),
    update_time datetime
);
