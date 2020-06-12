\connect certificates_db

INSERT INTO certificates
    (name, description, price, creation_date, modification_date, duration)
VALUES
('Test certificate name 1', 'Test description 1', 100, '2020-06-01', '2020-06-01', 20),
('Test certificate name 2', 'Test description 2', 150, '2020-06-04', null, 30),
('Test certificate name 3', 'Test description 3', 170, '2020-06-02', null, 45);

INSERT INTO tags
    (name)
VALUES
('Test tag name 1'),
('Test tag name 2'),
('Test tag name 3');

INSERT INTO certificate_tag
    (certificate_id, tag_id)
VALUES
       (1, 1),
       (2, 2),
       (3, 3);



