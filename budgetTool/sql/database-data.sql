
TRUNCATE TABLE "jobs" CASCADE;
TRUNCATE TABLE "rows" CASCADE;
TRUNCATE TABLE "user" CASCADE;
INSERT INTO "user" VALUES (1,1,'Boss',1);
INSERT INTO "user" VALUES (2,2,'Foreman1',1);
INSERT INTO "user" VALUES (3,2,'Foreman2',1);
INSERT INTO "jobs" VALUES (1,'Order 2075',2);
INSERT INTO "jobs" VALUES (2,'Warehouse',2);
INSERT INTO "jobs" VALUES (3,'Order 1148',2);
INSERT INTO "jobs" VALUES (4,'Order 1149',2);
INSERT INTO "jobs" VALUES (5,'Order 1150',3);
INSERT INTO "rows" VALUES (1, 1, 'Workers', 1200000, 20000, TRUE, FALSE, FALSE, 0, '');
INSERT INTO "rows" VALUES (2, 1, 'Materials', 2000000, 10000, TRUE, FALSE, FALSE, 0, '');
INSERT INTO "rows" VALUES (3, 1, 'Planning', 2000000, 3000000, FALSE, TRUE, FALSE, 0, '');
INSERT INTO "rows" VALUES (4, 2, 'Workers', 1200000, 20000, TRUE, FALSE, FALSE, 0, '');
INSERT INTO "rows" VALUES (5, 2, 'Materials', 2000000, 180000, FALSE, FALSE, TRUE, 1000000, 'Customers order 9811');
INSERT INTO "rows" VALUES (6, 3, 'Workers', 1200000, 20000, TRUE, FALSE, FALSE, 0, '');
INSERT INTO "rows" VALUES (7, 3, 'Materials', 2000000, 10000, TRUE, FALSE, FALSE, 0, '');
INSERT INTO "rows" VALUES (8, 3, 'Planning', 2000000, 3000000, FALSE, TRUE, FALSE, 0, '');


