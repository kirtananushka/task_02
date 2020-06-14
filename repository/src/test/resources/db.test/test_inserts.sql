

--\connect certificates_test_db

INSERT INTO certificates
    (name, description, price, creation_date, modification_date, duration)
VALUES
    ('Test lorem ipsum', 'Lorem ipsum dolor sit amet elit', 70, '01.03.2020',  null, 270),
    ('Maecenas consectetuer', 'Maecenas consectetuer porttitor commodo dolor enim', 150, '25.05.2020',  null, 50),
    ('Magna porttitor', 'Magna porttitor nunc imperdiet dolor dolor', 70, '05.03.2020',  null, 5),
    ('Porttitor lectus', 'Porttitor lectus sed massa lectus congue', 130, '17.03.2020',  null, 10),
    ('Nunc sit', 'Nunc sit maecenas congue viverra dolor', 100, '19.05.2020',  null, 50),
    ('Pulvinar dolor', 'Pulvinar dolor consectetuer viverra libero purus', 70, '18.05.2020',  null, 40),
    ('Pulvinar eros', 'Pulvinar eros maecenas purus purus enim', 30, '06.04.2020',  null, 30),
    ('Adipiscing consectetuer', 'Adipiscing consectetuer sed fusce ipsum malesuada', 30, '21.04.2020',  null, 80),
    ('Fusce sit', 'Fusce sit consectetuer sit consectetuer congue', 160, '18.04.2020',  null, 30),
    ('Pulvinar viverra', 'Pulvinar viverra pulvinar imperdiet fusce elit', 190, '12.04.2020',  null, 75);

INSERT INTO tags
    (name)
VALUES
    ('Suspendisse'),
    ('Donec'),
    ('Habitant'),
    ('Laoreet'),
    ('Aenean'),
    ('Egestas'),
    ('Neque'),
    ('Venenatis'),
    ('Vulputate'),
    ('Mattis'),
    ('Netus'),
    ('Nonummy'),
    ('Proin'),
    ('Lorem'),
    ('Turpis'),
    ('Porttitor'),
    ('Fames'),
    ('Mauris'),
    ('Morbi'),
    ('Tristique'),
    ('Purus'),
    ('Pharetra'),
    ('Malesuada'),
    ('Eleifend'),
    ('Pellente'),
    ('Pretium'),
    ('Senectus'),
    ('Scelerisque'),
    ('Vitae'),
    ('Augue');

INSERT INTO certificate_tag
    (certificate_id, tag_id)
VALUES
    (1, 21),
    (1, 24),
    (2, 2),
    (2, 15),
    (3, 2),
    (3, 6),
    (3, 12),
    (3, 14),
    (4, 18),
    (4, 27),
    (5, 1),
    (5, 14),
    (5, 16),
    (5, 17),
    (5, 25),
    (5, 28),
    (6, 2),
    (6, 11),
    (6, 20),
    (7, 18),
    (7, 21),
    (8, 13),
    (9, 4),
    (9, 20),
    (9, 21),
    (10, 20);
