create table person
(
    id           number primary key,
    name         nvarchar2(30)   not null,
    family       nvarchar2(30)   not null,
    national_id  char(10) unique not null,
    birth_date   date,
    phone_number nvarchar2(20)   not null,
    email        nvarchar2(40)   not null,
    city         nvarchar2(30)   not null,
    university   nvarchar2(30)   not null,
    address      nvarchar2(100)
);
create sequence person_seq start with 1 increment by 1;


create table books
(
    id       number primary key,
    title    nvarchar2(30) not null,
    author   nvarchar2(30) not null,
    isbn     char(16),
    category nvarchar2(30)

);
create sequence books_seq start with 1 increment by 1;

create table loans
(
    id          number primary key,
    person_id references person,
    book_id references books,
    loan_date   date default sysdate,
    return_date date default null

);

create sequence loans_seq start with 1 increment by 1;
--


create view loans_report as
select l.id           as loan_id,
       l.person_id,
       p.name         as person_name,
       p.family       as person_family,
       p.national_id  as person_national_id,
       p.birth_date   as person_birth_date,
       p.phone_number as person_phone_number,
       p.address      as person_address,
       b.title        as book_title,
       b.author       as book_author,
       b.isbn         as book_isbn,
       b.category     as book_category,
       l.loan_date,
       l.return_date
from loans l
         join person p
              on l.person_id = p.id

         join books b
              on l.book_id = b.id;