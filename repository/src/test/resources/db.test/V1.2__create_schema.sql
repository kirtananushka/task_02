-- connect certificates_db_test

-- -------------------------------------
drop TABLE IF EXISTS certificate_tag;
drop TABLE IF EXISTS tags;
drop TABLE IF EXISTS certificates;
-- -------------------------------------
create table certificates
(
    id                bigserial   not null primary key,
    name              varchar(64) not null,
    description       varchar(64) not null,
    price             numeric     not null,
    creation_date     date        not null default now(),
    modification_date date,
    duration          integer     not null
);
alter TABLE certificates
    OWNER to postgres;
GRANT ALL ON TABLE certificates TO PUBLIC;
-- -------------------------------------
create table tags
(
    id   bigserial   not null primary key,
    name varchar(64) not null
);
alter TABLE tags
    OWNER to postgres;
GRANT ALL ON TABLE tags TO PUBLIC;
-- -------------------------------------
create table certificate_tag
(
    id             bigserial not null primary key,
    certificate_id integer   not null
        references certificates (id) on delete cascade,
    tag_id         integer   not null
        references tags (id) on delete cascade
)
    tablespace pg_default;
alter TABLE certificate_tag
    OWNER to postgres;
GRANT ALL ON TABLE certificate_tag TO postgres;
GRANT ALL ON TABLE certificate_tag TO PUBLIC;