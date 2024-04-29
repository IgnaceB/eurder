CREATE TABLE users (
                       id uuid primary key,
                       first_name varchar,
                       last_name varchar,
                       email_address varchar,
                       address varchar,
                       phone_number varchar,
                       role varchar,
                        password varchar
);

CREATE TABLE items (
                       id uuid primary key ,
                       name varchar,
                       description varchar,
                       price float,
                       amount int
);

CREATE TABLE orders (
                        id uuid primary key ,
                        customer_id uuid,
                        creation_date timestamp
);

CREATE TABLE item_groups (
                             id uuid primary key ,
                             item_id uuid,
                             item_price float,
                             amount_ordered int,
                             shipping_date timestamp,
                             order_id uuid
);

ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES users (id);

ALTER TABLE item_groups ADD FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE item_groups ADD FOREIGN KEY (item_id) REFERENCES items (id);
