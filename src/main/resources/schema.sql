drop table files;
create table files
(
    id integer not null,
    fileName varchar(255) not null,
    fileType varchar(255) not null,
    data blob not null,
    primary key(id)
);