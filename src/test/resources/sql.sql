DROP TABLE IF EXISTS client;

CREATE TABLE client (
  id_client int(11) NOT NULL,
  name varchar(45) NOT NULL,
  age int(5) NOT NULL,
  PRIMARY KEY (id_client))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

INSERT INTO client(id_client, name, age) VALUES
  (1, 'Vasya', 18),
  (2, 'Petya', 19),
  (3, 'Kolya', 20),
  (4, 'Oleg', 21);

DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id_account int(11) NOT NULL AUTO_INCREMENT,
  amount int(5) NOT NULL,
  currency varchar(45) NOT NULL,
  id_client int(11) NOT NULL,
  PRIMARY KEY (id_account))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

INSERT INTO account(id_account, amount, currency, id_client) VALUES
  (default, 10, 'rub', 1),
  (default, 11, 'rub', 1),
  (default, 12, 'rub', 2),
  (default, 13, 'rub', 2),
  (default, 14, 'rub', 3),
  (default, 15, 'rub', 3),
  (default, 16, 'rub', 3),
  (default, 17, 'rub', 3),
  (default, 18, 'rub', 4),
  (default, 19, 'rub', 4),
  (default, 20, 'rub', 4),
  (default, 21, 'rub', 4),
  (default, 22, 'rub', 4),
  (default, 23, 'rub', 4),
  (default, 24, 'rub', 4),
  (default, 25, 'rub', 4),
  (default, 26, 'rub', 4),
  (default, 27, 'rub', 4),
  (default, 28, 'rub', 4),
  (default, 29, 'rub', 4);
