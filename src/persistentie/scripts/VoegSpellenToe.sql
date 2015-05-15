/* spellen toevoegen */
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('easy');
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('medium');
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('hard');

/* spelborden aan spellen toevoegen*/
INSERT INTO sokobandatabase.spelbord (Spel_spelID) VALUES
(1), (1), (1),
(2), (2),
(3);

/* elementen aan spelborden toevoegen */
/* SPEL: easy */
/* SPELBORD 1-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(4,4,1);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(2,4,1);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) values
(3,4,1);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(5,4,1);
/* EINDE SPELBORD 1-1 */
/* SPELBORD 1-2 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,2),(3,4,2),(4,2,2);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,2),(3,6,2),(4,1,2),(6,4,2);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) values
(3,3,2),(3,5,2),(4,3,2),(5,4,2);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(4,4,2);
/* EINDE SPELBORD 1-2 */
/* SPELBORD 1-3 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,1,3),(2,2,3),(3,3,3),(3,4,3),(4,1,3),(4,2,3),(4,4,3),(5,1,3),(5,2,3);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,1,3),(1,2,3);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(3,1,3),(4,3,3);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(3,2,3);
/*EINDE SPELBORD 1-3 */
/* EINDE SPEL: easy */

/* SPEL: gemiddeld */
/* SPELBORD 2-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(1,2,4),(1,3,4),(1,4,4),(1,5,4),(2,1,4),(2,6,4),(4,1,4),(4,4,4),(4,6,4),(5,2,4),(5,3,4),(5,4,4),(5,5,4),(3,6,4),(4,5,4);

INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(3,1,4),(3,2,4),(3,3,4),(3,4,4),(3,5,4);

INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,4),(2,4,4),(2,5,4),(4,2,4),(4,3,4);

INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(2,2,4);
/* EINDE SPELBORD 2-1 */

/* SPELBORD 2-2 */ /*VERPLAATSTEN NAAR MOEILIJK*/
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,5),(3,1,5),(3,2,5),(3,3,5),(4,1,5),(4,2,5),(4,4,5),(4,5,5),(5,1,5),(5,2,5),(5,6,5),(6,1,5),(6,2,5),(6,4,5),(6,5,5),(6,6,5);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,5),(1,4,5),(2,4,5),(3,5,5);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(3,4,5),(4,3,5),(5,4,5),(5,5,5);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(6,3,5);
/* EINDE SPELBORD 2-2 */

/* EINDE SPEL: gemiddeld */

/* SPEL: moeilijk */
/* SPELBORD 3-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,2,6),(2,3,6),(2,5,6),(2,6,6),(3,2,6),(3,6,6),(4,2,6),(4,3,6),(4,4,6),(4,6,6),(5,5,6),(6,2,6),(6,3,6),(3,3,6);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(2,4,6),(3,4,6),(5,2,6);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(4,5,6),(5,3,6),(5,4,6);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(2,5,6);
/* EINDE SPELBORD 3-1 */
/* EINDE SPEL: moeilijk */