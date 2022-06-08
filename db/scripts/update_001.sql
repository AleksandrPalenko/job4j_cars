CREATE TABLE if not exists drivers(
    id serial primary key,
    name varchar(200)
);

CREATE TABLE if not exists engines(
    id serial primary key,
    name varchar(200)
);

CREATE TABLE if not exists typeCar(
    id serial primary key,
    name varchar(200),
    countOfDoors int
);

CREATE TABLE if not exists brands(
    id serial primary key,
    name varchar(200)
);

CREATE TABLE if not exists cars(
    id serial primary key,
    description text,
    photo bytea,
    typeCar_id int not null unique references typeCar(id),
    brand_id int not null unique references brands(id),
    engine_id int not null unique references engines(id)
);

CREATE TABLE if not exists history_owner(
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

CREATE TABLE if not exists users(
     id serial primary key,
     name varchar(200),
     email VARCHAR,
     password TEXT,
     CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE if not exists advertisements(
     id serial primary key,
     status boolean,
     created timestamp,
     user_id int not null references users(id),
     car_id int not null references cars(id)
);
