-- DROP DATABASE IF EXISTS certificates_db;
--
-- CREATE DATABASE certificates_db
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'Russian_Belarus.1251'
--     LC_CTYPE = 'Russian_Belarus.1251'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;
--
-- GRANT ALL ON DATABASE certificates_db TO postgres;
--
-- GRANT TEMPORARY, CONNECT ON DATABASE certificates_db TO PUBLIC;
-- ----------------------------------------------------------------
-- DROP DATABASE IF EXISTS certificates_db;
--
-- CREATE DATABASE certificates_db
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'Russian_Belarus.1251'
--     LC_CTYPE = 'Russian_Belarus.1251'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;
--
-- GRANT ALL ON DATABASE certificates_db TO postgres;
--
-- GRANT TEMPORARY, CONNECT ON DATABASE certificates_db TO PUBLIC;

\connect certificates_db

DROP TABLE IF EXISTS public.linkage;
DROP TABLE IF EXISTS public.tags;
DROP TABLE IF EXISTS public.certificates;

CREATE TABLE public.certificates
(
cert_id bigserial NOT NULL,
cert_name character varying NOT NULL,
description character varying NOT NULL,
price numeric NOT NULL,
creation_date date NOT NULL DEFAULT now(),
modification_date date,
duration integer NOT NULL,
    PRIMARY KEY (cert_id)
)
    WITH (
        OIDS = FALSE
    );

ALTER TABLE public.certificates
    OWNER to postgres;

GRANT ALL ON TABLE public.certificates TO PUBLIC;

INSERT INTO public.certificates(
    cert_name, description, price, creation_date, modification_date, duration)
VALUES
('Test name 1', 'Test description 1', 100, '2020-06-01', '2020-06-01', 20),
('Test name 2', 'Test description 2', 150, '2020-06-04', null, 30),
('Test name 3', 'Test description 3', 170, '2020-06-02', null, 45);
-- ------------------------------------------
DROP TABLE IF EXISTS public.tags;

CREATE TABLE public.tags
(
    tag_id bigserial NOT NULL,
    tag_name character varying NOT NULL,
    PRIMARY KEY (tag_id)
)
    WITH (
        OIDS = FALSE
    );

ALTER TABLE public.tags
    OWNER to postgres;

GRANT ALL ON TABLE public.tags TO PUBLIC;

INSERT INTO public.tags (tag_name)
VALUES
('Test tag-name 1'),
('Test tag-name 2'),
('Test tag-name 3');
-- ------------------------------------------
DROP TABLE IF EXISTS public.linkage;

CREATE TABLE public.linkage
(
    linkage_id bigserial NOT NULL,
    cert_id_fk bigserial NOT NULL,
    tag_id_fk bigserial NOT NULL,
    CONSTRAINT linkage_pkey PRIMARY KEY (linkage_id),
    CONSTRAINT cert_id_fk FOREIGN KEY (cert_id_fk)
        REFERENCES public.certificates (cert_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tag_id_fk FOREIGN KEY (tag_id_fk)
        REFERENCES public.tags (tag_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.linkage
    OWNER to postgres;

GRANT ALL ON TABLE public.linkage TO postgres;

GRANT ALL ON TABLE public.linkage TO PUBLIC;

INSERT INTO public.linkage(cert_id_fk, tag_id_fk)
VALUES
       (1, 2),
       (2, 2),
       (3, 1);