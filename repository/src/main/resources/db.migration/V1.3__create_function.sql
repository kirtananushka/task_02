\connect certificates_db

CREATE OR REPLACE FUNCTION search_descr(IN search_for VARCHAR) RETURNS certificates AS
$$
BEGIN
    SELECT *
    FROM certificates
    WHERE description SIMILAR TO '%' || search_for || '%';
END;
$$
    LANGUAGE plpgsql;

SELECT search_descr('lorem');
