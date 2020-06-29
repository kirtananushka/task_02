DROP DATABASE IF EXISTS certificates_db_test;
CREATE DATABASE certificates_db_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Belarus.1251'
    LC_CTYPE = 'Russian_Belarus.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
