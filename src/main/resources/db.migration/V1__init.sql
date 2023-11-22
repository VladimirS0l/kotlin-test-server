create table answer(
                       id varchar(255) PRIMARY KEY NOT NULL,
                       content text
);

insert into  answer (id, content) VALUES
                                      ('localhost:5555', 'test content 1'),
                                      ('localhost:6666', 'test content 2'),
                                      ('localhost:7777', 'test content 3'),
                                      ('localhost:8888', 'test content 4'),
                                      ('localhost:9999', 'test content 5'),
                                      ('1', 'test content 10'),
                                      ('2', 'test content 22'),
                                      ('3', 'test content 33'),
                                      ('4', 'test content 44'),
                                      ('5', 'test content 55');