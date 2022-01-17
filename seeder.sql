USE share_db;

INSERT INTO category (name) VALUES ('automotive'),
                                   ('electronics'),
                                   ('fashion'),
                                   ('home and garden'),
                                   ('sporting goods'),
                                   ('toys and hobbies'),
                                   ('other');

# please do not add any categories. If you do, everyone will need to update their db and we'll need to test the create form again.

INSERT INTO user (first_name, last_name, email, date_of_birth, city, state, zip_code, phone_number, password, username)
VALUES ('Mary', 'White', 'mwhite@email.com', '12-18-1984', 'Atlanta', 'GA', 30341, 4046418784, 'password', 'mwhite');


