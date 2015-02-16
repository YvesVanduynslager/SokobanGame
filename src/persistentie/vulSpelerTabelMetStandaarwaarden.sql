/* Deze instructie wist eerst alle gegevens uit de tabel Spelers */
DELETE
FROM `sokobandb`.`Spelers`;

/* Deze instructie vult de tabel Spelers op met enkele gebruikers voor testdoeleinden */
INSERT INTO `sokobandb`.`Spelers`
VALUES
('yves', 'wachtwoord', 1, 'Yves', 'Vanduynslager'),
('jeroen', 'wachtwoord', 1, 'Jeroen', 'Ceulemans'),
('vanessa', 'wachtwoord', 1, null, null),
('peter', 'wachtwoord', 1, null, null),
('bert', 'wachtwoord', 0, null, null);