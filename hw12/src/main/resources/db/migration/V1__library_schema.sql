create table genre(
    id bigserial primary key,
    name text not null
);

create table address(
    id bigserial primary key,
    street text not null,
    city text not null,
    state text not null,
    country text not null
);

create table publisher(
    id bigserial primary key,
    name text not null,
    address_id bigint,

    foreign key(address_id) references address(id)
);

create table author(
     id bigserial primary key,
     full_name text not null
);

create table book(
    id bigserial primary key,
    title text not null,
    price numeric(20, 2),
    is_visible boolean not null default false,
    genre_id bigint not null,
    publisher_id bigint,

    foreign key(genre_id) references genre(id),
    foreign key(publisher_id) references publisher(id)
);

create table book_author(
    book_id bigint not null,
    author_id bigint not null,

    primary key(book_id, author_id),
    foreign key(author_id) references author(id),
    foreign key(book_id) references book(id)
);