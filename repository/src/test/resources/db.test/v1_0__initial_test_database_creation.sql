
DROP DATABASE IF EXISTS certificates_test_db;
 CREATE DATABASE certificates_test_db
     WITH
     OWNER = postgres
     ENCODING = 'UTF8'
     LC_COLLATE = 'Russian_Belarus.1251'
     LC_CTYPE = 'Russian_Belarus.1251'
     TABLESPACE = pg_default
     CONNECTION LIMIT = -1;
 GRANT ALL ON DATABASE certificates_test_db TO postgres;
 GRANT TEMPORARY, CONNECT ON DATABASE certificates_test_db TO PUBLIC;