insert into genre(id, name)
values (1, 'genre_test');

insert into author(id, full_name)
values (1, 'author_full_name');

insert into book(id, title, price, is_visible, genre_id)
values (1, 'book_test', 25.65, true, 1);

insert into book_author(book_id, author_id)
values (1, 1);
