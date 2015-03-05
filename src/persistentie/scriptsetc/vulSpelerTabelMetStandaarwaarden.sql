DELETE FROM speler;

/* Deze instructies vullen de tabel Speler op met enkele admingebruikers */
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `voornaam`, `achternaam`) VALUES ('Yves', 'Wachtwoord1234', true, 'Yves', 'Vanduynslager');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `voornaam`, `achternaam`) VALUES ('Jeroen', 'Wachtwoord1234', true, 'Jeroen', 'Ceulemans');
INSERT INTO `sokobandatabase`.`speler` (`gebruikernaam`, `wachtwoord`, `isAdmin`, `voornaam`, `achternaam`) VALUES ('Michiel', 'Wachtwoord1234', true, 'Michiel', 'Mertens');