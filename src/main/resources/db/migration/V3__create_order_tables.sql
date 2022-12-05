CREATE TABLE order_books
(
    id        SERIAL,
    orders_id INTEGER NOT NULL,
    books_id  uuid NOT NULL,
    CONSTRAINT pk_order_books PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL,
    user_id VARCHAR(255) NOT NULL,
    created  VARCHAR(255) NOT NULL,
    status SMALLINT NOT NULL DEFAULT '1',
    CONSTRAINT pk_orders PRIMARY KEY (id)
);