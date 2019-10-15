create table trains
(
    id_train numeric(10) PRIMARY KEY,
    start_station VARCHAR(50) NOT NULL,
    end_station VARCHAR(50) NOT NULL,
    count_free_sits numeric(10) NOT NULL
);


create table passengers
(
    id_passenger serial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    birthday date not null,
    login varchar(50) not null,
    password varchar(50) not null,
    id_train numeric(10),
    foreign key (id_train) references trains (id_train)
);

insert into passengers (name, surname, birthday)
values ('Ivan','Ivanov','1982-10-12');