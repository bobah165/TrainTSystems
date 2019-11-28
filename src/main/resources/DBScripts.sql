

--filling table Stations
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
insert into stations (name_station) values ('Murmansk');

--filling table Users
insert into users (name, surname, birthday, login, password, email, user_state) values ('Alex','Smith','23-10-1987','alex','123456','alex@gmal.com','passenger');
insert into users (name, surname, birthday, login, password, email, user_state) values ('Bob','Adams','19-05-1995','bob','123456','bob@gmal.com','passenger');
insert into users (name, surname, birthday, login, password, email, user_state) values ('Kate','Johns','13-04-1990','kate','123456','kate@gmal.com','passenger');
insert into users (name, surname, birthday, login, password, email, user_state) values ('James','Scott','13-04-1990','james','123456','james@gmal.com','employee');
insert into users (name, surname, birthday, login, password, email, user_state) values ('Admin','Admin','12-12-1990','admin','123456','admin@gmal.com','employee');

--filling table Train_ways
--route №1 (Moscow - Tver - Bologoe - Piter)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,1,'14:30:00','14:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (3,1,'16:40:00','16:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (4,1,'19:05:00','18:45:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,1,'21:05:00','21:05:00',1);
--route №2 (Piter - Tver - Moscow - Ufa - Kazan - Omsk)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,2,'10:05:00','10:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (3,2,'12:50:00','12:40:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,2,'14:40:00','14:10:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (7,2,'23:20:00','23:10:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (6,2,'01:20:00','01:05:00',2);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (5,2,'08:10:00','08:10:00',2);
--route 3 (Rostov - Ufa - Kazan - Omsk)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (1,3,'07:30:00','07:30:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (7,3,'12:30:00','12:15:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (6,3,'18:05:00','17:35:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (5,3,'23:10:00','23:10:00',1);
--route 4 (Piter - Bologoe - Moscow)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,4,'00:05:00','00:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (4,4,'03:30:00','03:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,4,'06:10:00','06:10:00',1);
--route 5 (Moscow - Piter - Murmansk)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,5,'13:25:00','13:25:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,5,'20:30:00','20:05:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (11,5,'07:15:00','07:15:00',2);
--route 6 (Murmansk - Piter - Moscow - Ufa - Kazan)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (11,6,'11:00:00','11:00:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,6,'23:30:00','23:00:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,6,'08:10:00','07:30:00',2);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (7,6,'14:22:00','14:03:00',2);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (6,6,'17:00:00','17:00:00',2);
--route 7 (Moscow - Piter)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,7,'06:00:00','06:00:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,7,'13:30:00','13:30:00',1);
--route 8 (Piter - Moscow)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,8,'10:10:00','10:10:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,8,'22:10:00','22:10:00',1);
--route 9 (Rayzan - Moscow - Tver - Piter)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (9,9,'09:09:00','09:09:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,9,'13:27:00','13:07:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,9,'15:10:00','15:00:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,9,'23:13:00','23:13:00',1);
--route 10 (Piter - Tver - Moscow - Rostov)
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (2,10,'05:22:00','05:22:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (3,10,'09:45:00','09:35:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (10,10,'11:32:00','11:02:00',1);
insert into train_ways (id_station, number_way, departure_time, arrival_time, days_in_way) values (1,10,'23:10:00','23:10:00',1);


--filling table trains
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (1,800,'28-11-2019',1,'everyday');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (2,800,'27-11-2019',7,'odd');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (3,800,'28-11-2019',13,'even');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (4,800,'28-11-2019',16,'even');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (5,800,'28-11-2019',19,'everyday');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (6,800,'28-11-2019',23,'everyday');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (7,800,'28-11-2019',27,'even');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (8,800,'28-11-2019',29,'everyday');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (9,800,'28-11-2019',31,'everyday');
insert into trains (train_number,count_sits,departure_date,id_way,schedule) values (10,800,'28-11-2019',36,'everyday');


--filling table tickets
insert into tickets (id_passenger, id_train) values (1,1);
insert into tickets (id_passenger, id_train) values (2,2);
insert into tickets (id_passenger, id_train) values (3,3);
insert into tickets (id_passenger, id_train) values (1,4);
insert into tickets (id_passenger, id_train) values (2,5);
insert into tickets (id_passenger, id_train) values (3,6);
insert into tickets (id_passenger, id_train) values (1,7);
insert into tickets (id_passenger, id_train) values (2,8);
insert into tickets (id_passenger, id_train) values (3,9);
insert into tickets (id_passenger, id_train) values (3,10);
