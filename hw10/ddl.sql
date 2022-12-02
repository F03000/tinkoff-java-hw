create table Author(
    Id serial not null,
    "Name" varchar(255) not null,
    primary key(id)
)

create table Genre(
    Id serial not null,
    "Name" varchar(255) not null,
    primary key(id)
)

create table Publisher(
    Id serial not null,
    "Name" varchar(255) not null,
    Adress varchar(255) not null,
    primary key (id)
)

create table Book(
    Id serial not null,
    Title varchar(255) not null,
    AuthorId int references Author(id),
    primary key (id)
)

create table Publisher_Book(
    PublisherId int references Publisher(id),
    BookId int references Book(id),
    primary key(PublisherId, BookId)
)
