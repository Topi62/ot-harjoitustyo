CREATE TABLE IF NOT EXISTS "user" (id INT PRIMARY KEY NOT NULL, type INT, name varchar(12), boss INT);
CREATE TABLE IF NOT EXISTS "jobs" (id INT PRIMARY KEY NOT NULL, name varchar(12),owner INT REFERENCES "user" (id));
CREATE TABLE IF NOT EXISTS "rows" (id INT PRIMARY KEY NOT NULL, jobid INT REFERENCES "jobs" (id), resurs varchar(15),
    budgetsum INT, usedsum INT, approved BOOLEAN, exceeded BOOLEAN, request BOOLEAN, requestsum INT, reason varchar(50));