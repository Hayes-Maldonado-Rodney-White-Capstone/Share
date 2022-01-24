USE share_db;

INSERT INTO category (name) VALUES ('Automotive'),
                                   ('Electronics'),
                                   ('Fashion'),
                                   ('Home and Garden'),
                                   ('Sporting Goods'),
                                   ('Toys and Hobbies'),
                                   ('Other');

# please do not add any categories. If you do, everyone will need to update their db and we'll need to test the create form again.

INSERT INTO rating (rating) VALUES (1),
                                   (2),
                                   (3),
                                   (4),
                                   (5);


INSERT INTO role (role_type) VALUES ('admin'),
                                    ('user');