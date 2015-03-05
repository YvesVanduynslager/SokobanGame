/* Deze instructie wist eerst alle gegevens uit de tabel Spelers */
DELETE
FROM `sokobandatabase`.`speler`;

/* Deze instructie vult de tabel Spelers op met enkele gebruikers voor testdoeleinden */
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `voornaam`, `achternaam`) VALUES ('Yves', 'wachtwoord', true, 'Yves', 'Vanduynslager');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `voornaam`) VALUES ('Jeroen123', 'Test1234', true, 'Jeroen');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `achternaam`) VALUES ('Vanessa', 'wachtwoord', true, 'Dejonghe');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`) VALUES ('Ernie', 'banaaninjeoor', false);
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`) VALUES ('Bert', 'banaaninjeoor', false);