 create sequence hibernate_sequence start with 1 increment by 1;
 create table product (id integer not null,date_creation timestamp, date_modification timestamp, sequence integer, primary key (id));
 create table size (id integer not null, back_soon boolean, date_creation timestamp, date_modification timestamp, special boolean, product_id integer, primary key (id));
 create table stock (id integer not null, date_creation timestamp, date_modification timestamp, quantity integer, size_id integer, primary key (id));
 alter table size add constraint FKProduct foreign key (product_id) references product;
 alter table stock add constraint FKStock foreign key (size_id) references size;