INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('makkelijk'), ('gemiddeld'), ('moeilijk');

/* spelborden voor spel "makkelijk" */
INSERT INTO sokobandatabase.spelbord (spelbordNaam, Spel_spelID) VALUES
('1-1', 1), ('1-2', 1), ('1-3', 1),
('2-1', 2), ('2-2', 2), ('2-3', 2),
('3-1', 3), ('3-2', 3), ('3-3', 3);

INSERT INTO sokobandatabase.muur (positieX, positieY, Spelbord_spelbordID) VALUES
(0,0,1),(0,1,1),(0,2,1),(0,3,1),(0,4,1),(0,5,1),(0,6,1),(0,7,1),(0,8,1),(0,9,1),
(1,0,1),(1,1,1),(1,2,1),(1,4,1),(1,5,1),(1,6,1),(1,7,1),(1,8,1),(1,9,1),
(2,0,1),(2,1,1),(2,2,1),(2,4,1),(2,5,1),(2,6,1),(2,7,1),(2,8,1),(2,9,1),
(3,0,1),(3,1,1),(3,2,1),(3,7,1),(3,8,1),(3,9,1),
(4,0,1),(4,5,1),(4,6,1),(4,7,1),(4,8,1),(4,9,1),
(5,0,1),(5,1,1),(5,2,1),(5,3,1),(5,5,1),(5,6,1),(5,7,1),(5,8,1),(5,9,1),
(6,0,1),(6,1,1),(6,2,1),(6,3,1),(6,5,1),(6,6,1),(6,7,1),(6,8,1),(6,9,1),
(7,0,1),(7,1,1),(7,2,1),(7,3,1),(7,4,1),(7,5,1),(7,6,1),(7,7,1),(7,8,1),(7,9,1),
(8,0,1),(8,1,1),(8,2,1),(8,3,1),(8,4,1),(8,5,1),(8,6,1),(8,7,1),(8,8,1),(8,9,1),
(9,0,1),(9,1,1),(9,2,1),(9,3,1),(9,4,1),(9,5,1),(9,6,1),(9,7,1),(9,8,1),(9,9,1);

INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,1),(3,4,1),(4,2,1);

INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,1),(3,6,1),(4,1,1),(6,4,1);

INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) values
(3,3,1),(3,5,1),(4,3,1),(5,4,1);

INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(4,4,1);


/*
Rij1: *Muur: 00,01,02,03,04,05,06,07,08,09

Rij2: *Doel: 14
	*Muur: 10,11,12,13,15,16,17,18,19

Rij3: *Veld: 23
	*Muur: 20,21,22,24,25,26,27,28,29

Rij4: *Veld: 34
	*Kist: 33, 35
	*Doel: 36
    *Muur: 30,31,32,37,38,39
    
Rij5: *Muur: 40,45,46,47,48,49
	*Doel: 41
    *Veld: 42
    *Kist: 43
    *Mannetje: 44

Rij6: *Muur: 50,51,52,53,55,56,57,58,59
	*Kist: 54
    
Rij7: *Muur: 60,61,62,63,65,66,67,68,69
	*Doel: 64

Rij8: *Muur: 70,71,72,73,74,75,76,77,78,79

Rij9: *Muur: 80,81,82,83,84,85,86,87,88,89

Rij10: *Muur: 90,91,92,93,94,95,96,97,98,99*/
