-- create table trains (
--     id_train serial primary key,
--     count_sits numeric(10) not null
-- );
--
--
-- create table passengers
-- (
--     id_passenger serial primary key,
--     name varchar(50),
--     surname varchar(50),
--     birthday date,
--     login varchar(50),
--     password varchar(50),
--     email varchar(50)
-- );
--
-- create table tickets
-- (
--     id_ticket serial primary key,
--     departure_station varchar(50) not null,
--     arrival_station varchar(50) not null,
--     departure_date date not null,
--     arrival_date date not null,
--     id_train integer not null,
--     foreign key (id_train) references trains (id_train),
--     id_passenger integer not null,
--     foreign key (id_passenger) references passengers (id_passenger)
-- );
--
-- create table stations
-- (
--     id_station serial primary key,
--     name_station varchar(50) not null
-- );

-- Данные для таблиц

--Заполнение таблицы Stations
insert into stations (name_station) values('Rostov');
insert into stations (name_station) values ('Piter');
insert into stations (name_station) values ('Tver');
insert into stations (name_station) values ('Bologoe');
insert into stations (name_station) values ('Omsk');
insert into stations (name_station) values ('Kazan');
insert into stations (name_station) values ('Ufa');
insert into stations (name_station) values ('Tomsk');
insert into stations (name_station) values ('Rayzan');
insert into stations (name_station) values ('Moscow');

--заполнение таблицы Passengers
insert into passengers (name, surname, birthday, login, password, email, user_state)
values ('alex','smith','23-10-1987','alex','1234','alex@gmal.com','passenger');
insert into passengers (name, surname, birthday, login, password, email, user_state)
values ('bob','adams','19-05-1995','bob','1234','bob@gmal.com','passenger');
insert into passengers (name, surname, birthday, login, password, email, user_state)
values ('kate','johns','13-04-1990','kate','1234','kate@gmal.com','passenger');

--заполнение таблицы train_ways (маршруты)
--маршрут №1
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (10,1,'14:30:00','14:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (3,1,'16:30:00','16:40:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (4,1,'18:45:00','19:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (2,1,'21:05:00','21:05:00',1);
--маршрут №2
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (2,2,'10:05:00','10:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (3,2,'12:40:00','12:50:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (10,2,'14:10:00','14:40:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (7,2,'23:10:00','23:20:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (6,2,'01:05:00','01:20:00',2);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (5,2,'08:10:00','08:10:00',2);
--маршрут 3
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (1,3,'07:30:00','07:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (7,3,'12:15:00','12:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (6,3,'17:25:00','18:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way)
values (5,3,'23:10:00','23:10:00',1);

--заполнение таблицы passengers
insert into passengers (name, surname, birthday, login, password, email, user_state)
values ('alex','smith','20-10-1987','alex','1234','alex@gmail.com','passenger');


--заполнение таблицы trains
insert into trains (train_number,count_sits,departure_date,id_way)
values (1,800,'01-11-2019',1);
insert into trains (train_number,count_sits,departure_date,id_way)
values (2,800,'01-11-2019',7);
insert into trains (train_number,count_sits,departure_date,id_way)
values (3,800,'02-11-2019',13);
