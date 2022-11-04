CREATE TABLE person (
                        person_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        full_name varchar(100) NOT NULL UNIQUE,
                        year_of_birth int NOT NULL
);

CREATE TABLE book (
                      book_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year_of_release int NOT NULL,
                      person_id int REFERENCES person(person_id) ON DELETE SET NULL
);