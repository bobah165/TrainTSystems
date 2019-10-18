create table trains (
    id_train serial primary key,
    count_sits numeric(10) not null
);


create table passengers
(
    id_passenger serial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    birthday date not null,
    login varchar(50) not null,
    password varchar(50) not null,
    email varchar(50) not null
);

create table tickets
(
    id_ticket serial primary key,
    departure_station varchar(50) not null,
    arrival_station varchar(50) not null,
    departure_date date not null,
    arrival_date date not null,
    id_train integer not null,
    foreign key (id_train) references trains (id_train),
    id_passenger integer not null,
    foreign key (id_passenger) references passengers (id_passenger)
);

create table stations
(
    id_station serial primary key,
    name_station varchar(50) not null
);


create table time_table
(
    id serial primary key,
    id_train integer,
    id_station integer,
    arrival_time date not null,
    departure_time date not null,
    count_free_sits numeric(5) not null,
    UNIQUE (id_station,id_train),
    foreign key (id_train) references trains (id_train),
    foreign key (id_station) references stations (id_station)
);