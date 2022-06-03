CREATE TABLE if not exists driver(
    id serial primary key,
    name varchar(200)
);

CREATE TABLE if not exists engine(
    id serial primary key,
    name varchar(200)
);

CREATE TABLE if not exists car(
    id serial primary key,
    brand varchar(200),
    model varchar(200),
    engine_id int not null unique references engine(id)
);

CREATE TABLE if not exists history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);