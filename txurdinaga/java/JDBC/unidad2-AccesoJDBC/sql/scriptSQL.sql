CREATE TABLE motos(
modelo varchar(15) not null,
marca varchar(20),
cilindrada int,
importe float(6,2));


INSERT INTO motos VALUES ('xrs', 'Yamaha', 250, '6525.99');
INSERT INTO motos VALUES ('xds', 'Yamaha', 250, '6525.99');
INSERT INTO motos VALUES ('dfs', 'Yamaha', 250, '675.99');

INSERT INTO motos VALUES ('hgj', 'Honda', 250, '3625.99');
INSERT INTO motos VALUES ('eee', 'Honda', 250, '9444.99');
INSERT INTO motos VALUES ('hjs', 'Honda', 250, '4325.99')

INSERT INTO motos VALUES ('kls', 'BMW', 250, '4555.99');
INSERT INTO motos VALUES ('fgf', 'BMW', 250, '9825.99');

commit;