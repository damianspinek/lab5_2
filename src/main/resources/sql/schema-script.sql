drop table if EXISTS person;
create table person (
id bigint not null,
first_name varchar(255),
last_name varchar(255),
primary key (id)
);