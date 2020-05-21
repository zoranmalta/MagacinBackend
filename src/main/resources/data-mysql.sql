-- Skripta koja se pokrece automatski pri pokretanju aplikacije
-- password je 123
-- Obe lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
set foreign_key_checks = 0;

-- delete all rows
truncate table mesto;
truncate table radnik;
truncate table preduzece;
truncate table magacin;
truncate table poslovni_partner;
truncate table jedinica_mere;
truncate table poslovna_godina;
truncate table grupa_roba;
truncate table roba;

insert into mesto (naziv,ptt) values
("Beograd",11000), ("Novi Sad",21000),("Nis",18000),("Valjevo",14000);

insert into preduzece ( naziv , pib , adresa , mesto_id) value
("GigaMarket", 11122233 , "Temerinska 1 ",2);

insert into magacin (naziv , preduzece_id) values
("centralni",1),("juzni",1),("severni",1);

insert into radnik (ime , prezime , preduzece_id , magacin_id ) values
("Petar" , "Petrovic",1,null),("Jovan","Maric",1,null),("Stefan" , "Popovic" , 1 , null)
,("Luka","Ilic",null,2),("Vuk","Kostic",null,2),("Sergej","Trifunovic",null,2),
("Leo", "Mesi",null,1),("Kristijano","Ronaldo",null,1),("Luis","Suarez",null,1),
("Rafael","Nadal",null,3),("Rodzer","Federer",null,3),("Novak","Djokovic",null,3);

insert into poslovni_partner ( naziv , adresa , pib , mesto_id , preduzece_id , tip_partnera)
values ("Industrija Mesa Beograd","Kisacka 3", 11223344, 1,1,1),
("Fabrika cokolade" , " Rumenacka 5" ,11223355, 2 ,1,3),
("Niska pekarska industrija","Kalca 2 " , 11223355, 3 , 1 , 3),
("Valjevska pivara","Sindjeliceva 22",11223366,4,1,2),
("Beogradska secerana ","Kneza Milosa 1",11223377,1,1,1),
("Novosadski mlin","Mileticeva 51",11223388,2,1,3),
("Mlekara Novi Sad","Industrijska BB",11223399,2,1,3),
("Minimarket Beograd","Kolarceva 12",11223300,1,1,2);

insert into jedinica_mere(naziv) values ("kom"),("kg"),("lit"),("t");
insert into poslovna_godina(godina) values ("2020:01:01"),("2019:01:01"),("2018:01:01");
insert into grupa_roba ( naziv , preduzece_id) values
("Zitarice i preradjevine od brasna" , 1),("Alkoholna pica" , 1),("Konditorski proizvodi",1),("Mesni proizvodi",1),
("Osnovne namirnice",1),("Bezalkoholna pica",1),("Mlecni proizvodi",1);

insert into roba(sifra,pakovanje,naziv,jedinica_mere_id,grupa_roba_id) values
("00001",50,"Brasno tip 500",2,1),("00002",25,"Brasno tip 400",2,1),
("00003",50,"Brasno Kukuruzno",2,1),("00004",25,"Fanta",3,6),
("00007",25,"Apakola",3,6),("00006",25,"Jabuka ",3,6),
("00009",50,"Mleko",3,7),("00010",25,"Jogurt",3,7);



set foreign_key_checks = 1;