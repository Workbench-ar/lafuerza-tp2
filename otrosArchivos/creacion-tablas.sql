

DROP TABLE IF EXISTS "propuestas_compradas_por_usuarios";

DROP TABLE IF EXISTS "atracciones_de_promos_AXB";

DROP TABLE IF EXISTS "atracciones_de_promociones";

DROP TABLE IF EXISTS "usuarios";

DROP TABLE IF EXISTS "promociones";

DROP TABLE IF EXISTS "atracciones";

DROP TABLE IF EXISTS "tipo_promocion";

DROP TABLE IF EXISTS "tipo_atracciones";

CREATE TABLE "tipo_atracciones" (
	"id"	INTEGER,
	"Nombre"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "tipo_promocion" (
	"id"	INTEGER,
	"Nombre"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "atracciones" (
	"id"	INTEGER,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Costo"	INTEGER NOT NULL,
	"Tiempo"	REAL NOT NULL,
	"Cupo"	INTEGER,
	"Tipo_Atraccion"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("Tipo_Atraccion") REFERENCES "tipo_atracciones"
);

CREATE TABLE "promociones" (
	"id"	INTEGER,
	"Tipo_promocion"	INTEGER NOT NULL,
	"Tipo_atracciones"	INTEGER NOT NULL,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Descripccion"	TEXT,
	"Variable"	REAL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("Tipo_atracciones") REFERENCES "tipo_atracciones"("id"),
	FOREIGN KEY("Tipo_promocion") REFERENCES "tipo_promocion"("id")
);

CREATE TABLE "usuarios" (
	"id"	INTEGER,
	"Nombre"	TEXT NOT NULL,
	"Tipo_atraccion"	INTEGER NOT NULL,
	"Presupuesto"	INTEGER NOT NULL,
	"Tiempo"	REAL NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("Tipo_atraccion") REFERENCES "tipo_atracciones"("id")
);

CREATE TABLE "atracciones_de_promociones" (
	"id"	INTEGER,
	"id_promocion"	INTEGER,
	"id_atraccion"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id"),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id")
);

CREATE TABLE "atracciones_de_promos_AXB" (
	"id"	INTEGER,
	"id_promocion"	INTEGER NOT NULL,
	"id_atraccion"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id"),
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id")
);

CREATE TABLE "propuestas_compradas_por_usuarios" (
	"id"	INTEGER,
	"id_usuario"	INTEGER NOT NULL,
	"id_atraccion"	INTEGER,
	"id_promocion"	INTEGER,
	FOREIGN KEY("id_atraccion") REFERENCES "atracciones"("id"),
	FOREIGN KEY("id_promocion") REFERENCES "promociones"("id"),
	FOREIGN KEY("id_usuario") REFERENCES "usuarios"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
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
(3,'Obi-Wan Kenobi',1,30,6),
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
(1,1,4),
(2,1,7),
(3,2,5),
(4,2,8),
(5,3,3),
(6,3,2),
(7,3,6),
(8,4,6),
(9,4,3),
(10,5,9),
(11,5,10),
(12,5,11);

INSERT INTO "atracciones_de_promos_AXB" VALUES
(1,3,3),
(2,5,3);