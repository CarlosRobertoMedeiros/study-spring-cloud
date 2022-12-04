CREATE USER calendario with PASSWORD 'calendario' CREATEDB;
CREATE DATABASE calendario_service
	WITH
	OWNER = calendario
	ENCODING = 'UTF-8'
	LC_COLLATE='en_US.utf8'
	LC_CTYPE='en_US.utf8'
	TABLESPACE = pg_default
	CONNECTION LIMIT = -1
	
	