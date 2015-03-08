/**Spellen opmaken*/
INSERT INTO spel(spelNaam)
VALUES ('makkelijk'),('gemiddeld'),('moeilijk');

/**Spelborden opmaken*/
INSERT INTO spelbord(spelbordNaam, Spel_spelID)
VALUES ('1-1', 1), ('1-2', 1), ('1-3', 1),
		('2-1', 2), ('2-2', 2), ('2-3', 2),
        ('3-1', 3), ('3-2', 3), ('3-3', 3);

/**Spelbord 1-1 opvullen*/
/** MUREN */
INSERT INTO muur (positieX,positieY,Spelbord_spelbordID) VALUES
(0,0,1),(0,1,1), (0,2,1),(0,3,1),(0,4,1),(0,5,1),(0,6,1),(0,7,1),(0,8,1),(0,9,1),
(1,0,1),(1,1,1),(1,2,1),(1,3,1),(1,4,1),(1,5,1),		(1,7,1),(1,8,1),(1,9,1),
(2,0,1),(2,1,1),(2,7,1),(2,8,1),(2,9,1),
(3,0,1),(3,1,1),(3,7,1),(3,8,1),(3,9,1),
(4,0,1),(4,1,1),(4,5,1),(4,6,1),(4,7,1),(4,8,1),(4,9,1),
(5,0,1),(5,1,1),(5,2,1),(5,5,1),(5,6,1),(5,7,1),(5,8,1),(5,9,1),
(6,0,1),(6,1,1),(6,7,1),(6,8,1),(6,9,1),
(7,0,1),(7,1,1),(7,2,1),(7,3,1),(7,5,1),(7,7,1),(7,8,1),(7,9,1),
(8,0,1),(8,1,1),(8,1,1),(8,2,1),(8,3,1),(8,4,1),(8,5,1),(8,6,1),(8,7,1),(8,8,1),(8,9,1),
(9,0,1),(9,1,1),(9,2,1),(9,3,1),(9,4,1),(9,5,1),(9,6,1),(9,7,1),(9,8,1),(9,9,1);

INSERT INTO doel(positieX, positieY, Spelbord_spelbordID) VALUES(1,6,1),(7,6,1);

INSERT INTO kist(positieX, positieY, Spelbord_spelbordID) VALUES(2,4,1),(5,4,1);

INSERT INTO veld(positieX, positieY, Spelbord_spelbordID) VALUES(2,2,1),(2,3,1),(2,5,1),(2,6,1),(3,3,1),(3,4,1),(3,5,1),(3,6,1),(4,6,1),(4,7,1),(5,6,1),(6,5,1),(6,6,1),(6,7,1),(6,8,1),(7,4,1);

INSERT INTO mannetje(positieX, positieY, Spelbord_spelbordID) VALUES(3,2,1);

