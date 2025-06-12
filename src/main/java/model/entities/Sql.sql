create table Person
(
    id          number primary key,
    name        nvarchar2(30),
    family      nvarchar2(30),
    username    nvarchar2(30),
    password    nvarchar2(30),
    birth_date  date,
    phon_number nvarchar2(15) unique
);
create sequence person_seq start with 1 increment by 1;


create table account_info
(
    id               number primary key,
    person_id        number primary key,
    amount           number,
    transaction_type nvarchar2(6)
)

