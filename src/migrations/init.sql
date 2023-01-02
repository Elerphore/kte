-- CREATE DATABASE develop;

create table discounts(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    discount_value double precision
);

insert into discounts(discount_value) values (2), (4), (8);

create table customers(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name varchar(45) unique,
    discount_1 integer,
    discount_2 integer,

    constraint fk_discount1 foreign key (discount_1) references discounts(id) on delete cascade on update cascade,
    constraint fk_discount2 foreign key (discount_2) references discounts(id) on delete cascade on update cascade
);

insert into customers(name, discount_1, discount_2) values ('customer_1', null, null), ('customer_2', 1, null), ('customer_2', 2, 3);

create table storeitems(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    storeitem_name varchar(45) not null,
    price double precision not null,
    description varchar(200) not null DEFAULT '',
    average_rating double precision default 0 not null
);

insert into storeitems(storeitem_name, price) values ('Яблоко', 11), ('Банан', 25), ('Груша', 16);

create table store_item_customer_rating(
    customer_id integer,
    storeitem_id integer,
    rating double precision,

    constraint fk_storeitem foreign key (storeitem_id) references storeitems(id) on delete cascade on update cascade
);

create table orders(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    customer_id integer,
    create_date date,
    order_number varchar(10)
);

create table order_storeitem(
    order_id integer,
    storeitem_id integer,
    amount integer,

    constraint fk_order foreign key (order_id) references orders(id) on delete cascade on update cascade,
    constraint fk_storeitem foreign key (storeitem_id) references storeitems(id) on delete cascade on update cascade
);
