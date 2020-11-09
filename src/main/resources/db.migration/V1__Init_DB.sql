create table contact (
    id bigint not null,
    name varchar(70) not null,
    phone varchar(20) not null,
    photo varchar,
    primary key (id)
);

create table contact_sequences (
    next_val bigint
);

insert into contact_sequences values ( 0 );