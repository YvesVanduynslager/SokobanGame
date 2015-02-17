/* Deze instructie wist eerst alle gegevens uit de tabel Spelers */
DELETE
FROM `sokobandatabase`.`speler`;

/* Deze instructie vult de tabel Spelers op met enkele gebruikers voor testdoeleinden */
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `adminrechten`, `voornaam`, `achternaam`) VALUES ('Yves', 'wachtwoord', '1', 'Yves', 'Vanduynslager');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `adminrechten`, `voornaam`) VALUES ('Jeroen', 'wachtwoord', '1', 'Jeroen');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `adminrechten`, `achternaam`) VALUES ('Vanessa', 'wachtwoord', '1', 'Dejonghe');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `adminrechten`) VALUES ('Ernie', 'banaaninjeoor', '0');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `adminrechten`) VALUES ('Bert', 'banaaninjeoor', '0');