create table app_user(
    id         bigint not null primary key,
    address    varchar(255),
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255)
);

alter table app_user owner to postgres;

create sequence seq_user increment by 10;

alter sequence seq_user owner to postgres;