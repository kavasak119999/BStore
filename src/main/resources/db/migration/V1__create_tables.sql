CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS authors
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id            UUID NOT NULL,
    book_name     VARCHAR(255),
    book_title    VARCHAR(600),
    book_price    DOUBLE PRECISION,
    book_category VARCHAR(255),
    book_count    INTEGER,
    author_id     UUID,
    image_id      BIGINT,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS images
(
    id        BIGINT NOT NULL,
    file_name VARCHAR(255),
    CONSTRAINT pk_images PRIMARY KEY (id)
);

ALTER TABLE books DROP CONSTRAINT IF EXISTS FK_BOOKS_ON_AUTHOR;

ALTER TABLE books DROP CONSTRAINT IF EXISTS FK_BOOKS_ON_IMAGE;

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_IMAGE FOREIGN KEY (image_id) REFERENCES images (id);