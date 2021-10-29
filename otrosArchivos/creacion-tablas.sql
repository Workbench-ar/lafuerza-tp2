
DROP TABLE IF EXISTS "intinerarios";

DROP TABLE IF EXISTS "atracciones_de_promos_AXB";

DROP TABLE IF EXISTS "atracciones_de_promociones";

DROP TABLE IF EXISTS "usuarios";

DROP TABLE IF EXISTS "promociones";

DROP TABLE IF EXISTS "atracciones";

DROP TABLE IF EXISTS "tipo_promocion";

DROP TABLE IF EXISTS "tipo_atracciones";

CREATE TABLE "tipo_atracciones" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "tipo_promocion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "atracciones" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL UNIQUE,
	"costo"	INTEGER NOT NULL CHECK("costo" >= 0),
	"tiempo"	REAL NOT NULL CHECK("tiempo" >= 0),
	"cupo"	INTEGER NOT NULL CHECK("cupo" >= 0),
	"tipo_atraccion"	INTEGER NOT NULL,
	FOREIGN KEY("tipo_atraccion") REFERENCES "tipo_atracciones",
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "promociones" (
	"id"	INTEGER,
	"tipo_promocion"	INTEGER NOT NULL,
	"tipo_atracciones"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL UNIQUE,
	"descripccion"	TEXT,
	"variable"	REAL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("tipo_atracciones") REFERENCES "tipo_atracciones"("id"),
	FOREIGN KEY("tipo_promocion") REFERENCES "tipo_promocion"("id")
);

CREATE TABLE "usuarios" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"tipo_atraccion"	INTEGER NOT NULL,
	"presupuesto"	INTEGER NOT NULL CHECK(presupuesto>=0),
	"tiempo"	REAL NOT NULL CHECK(tiempo >=0),
	FOREIGN KEY("tipo_atraccion") REFERENCES "tipo_atracciones"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "atracciones_de_promociones" (
	"id_promocion"	INTEGER NOT NULL,
	"id_atraccion"	INTEGER NOT NULL,
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id"),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id"),
	PRIMARY KEY("id_promocion","id_atraccion")
);

CREATE TABLE "atracciones_de_promos_AXB" (
	"id_promocion"	INTEGER NOT NULL,
	"id_atraccion"	INTEGER NOT NULL,
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id"),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id"),
	PRIMARY KEY("id_promocion","id_atraccion")
);

CREATE TABLE "intinerarios" (
	"id_usuario"	INTEGER NOT NULL,
	"id_atraccion"	INTEGER CHECK(("id_atraccion" IS NULL AND "id_promocion" IS NOT NULL) OR ("id_atraccion" IS NOT NULL AND "id_promocion" IS NULL)),
	"id_promocion"	INTEGER CHECK(("id_atraccion" IS NULL AND "id_promocion" IS NOT NULL) OR ("id_atraccion" IS NOT NULL AND "id_promocion" IS NULL)),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id"),
	FOREIGN KEY("id_usuario") REFERENCES "usuarios"("id"),
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id"),
	PRIMARY KEY("id_usuario","id_atraccion","id_promocion")
);

INSERT INTO "tipo_atracciones" VALUES
(1,'LADO_LUMINOSO'),
(2,'LADO_OSCURO'),
(3,'LADO_GRIS');

INSERT INTO "tipo_promocion" VALUES
(1,'PORCENTUAL'),
(2,'ABSOLUTA'),
(3,'AXB');

INSERT INTO "usuarios" VALUES
(1,'Yoda',1,37,10),
(2,'Luke Skywalker',1,54,12),
(3,'Obi Wan Kenobi',1,30,6),
(4,'Anakin Skywalker',1,21,8),
(5,'Darth Vader',2,82,16),
(6,'Darth Sidious',2,43,8),
(7,'Darth Maul',2,38,5),
(8,'Jefe Jawa',3,21,4),
(9,'Garindan',3,28,6),
(10,'Takeel',3,17,3);

INSERT INTO "atracciones" VALUES
(1,'La Amenaza Fantasma',5,25,10,2),
(2,'La Venganza de los Sith',15,3.5,8,2),
(3,'El Imperio contaataca',14,3,9,2),
(4,'El regreso del Jedi',7,2,6,1),
(5,'El despertar de la Fuerza',10,2,8,1),
(6,'El ataque de los Clones',11,2.5,10,2),
(7,'Una nueva Esperanza',5,3,5,1),
(8,'El ascenso de Skywalker',8,2,12,1),
(9,'Tatooine',6,1,4,3),
(10,'Naboo',7,1.5,5,3),
(11,'Endor',5,2,4,3);

INSERT INTO "promociones" VALUES 
(1,1,1,'Pack Luminoso 1','El regreso del Jedi y Una nueva Esperanza con un 20% de descuento comprando ambas.',0.2),
(2,2,1,'Pack Luminoso 2','El despertar de la Fuerza y El ascenso de Skywalker a 15 créditos galácticos.',15),
(3,3,2,'Pack Oscuro 1','El Imperio contaataca es gratis comprando La Venganza de los Sith y El ataque de los Clones.',NULL),
(4,2,2,'Pack Oscuro 2','El ataque de los Clones y El Imperio contaataca a 21 créditos galácticos.',21),
(5,3,3,'Pack Gris 1','Tatooine gratis comprando Naboo y Endor.',NULL);

INSERT INTO "atracciones_de_promociones" VALUES
(1,4),
(1,7),
(2,5),
(2,8),
(3,3),
(3,2),
(3,6),
(4,6),
(4,3),
(5,9),
(5,10),
(5,11);

INSERT INTO "atracciones_de_promos_AXB" VALUES
(3,3),
(5,3);