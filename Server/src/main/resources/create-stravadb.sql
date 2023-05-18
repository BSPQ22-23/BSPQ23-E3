/* DELETE 'stravadb' database*/
DROP SCHEMA IF EXISTS cancioncitas;
/* DELETE USER 'sd' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'%';

/* CREATE 'stravadb' DATABASE */
CREATE SCHEMA cancioncitas;

CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';

GRANT ALL ON cancioncitas.* TO 'spq'@'%';