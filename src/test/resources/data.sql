INSERT INTO users(id, first_name, last_name, email_address, address, phone_number, password, role)
values ('33f10c8b-7795-4fbc-adc3-cdea73f4fd4e', 'admin', 'admin', 'admin@admin', 'addressAdmin', 'dontCall', 'mdp',
        'ADMIN'),
       ('e159d9f0-9023-4e2c-8ec0-6df42e763cf8', 'firstNameCustomer1', 'LastNameCustomer1', 'emailCustomer1',
        'AddressCustomer1', 'phoneNumberCustomer1', 'mdp', 'CUSTOMER'),
       ('ec7658c0-36c1-4a62-b655-55226013228e', 'firstNameCustomer2', 'LastNameCustomer2', 'emailCustomer2',
        'AddressCustomer2', 'phoneNumberCustomer2', 'mdp', 'CUSTOMER');

INSERT INTO items(id, name, description, price, amount)
values ('550e8400-e29b-41d4-a716-446655440000', 'Produit A', 'Description du produit A', 19.99, 100),
       ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Produit B', 'Description du produit B', 29.99, 50),
       ('7b858aee-9dc0-4a1d-b63b-1a73271e3d7b', 'Produit C', 'Description du produit C', 9.99, 200),
       ('92c3c8eb-6c8e-4a9e-a14e-78e7d2cc9d99', 'Produit D', 'Description du produit D', 39.99, 30),
       ('a2b9ac2f-3f9c-47c5-b920-3b813e825a9a', 'Produit E', 'Description du produit E', 49.99, 80);
;
INSERT INTO orders(id, customer_id, creation_date)
values ('09b0bab2-4825-4a69-a200-fe4f8bc80c56', 'e159d9f0-9023-4e2c-8ec0-6df42e763cf8', null),
       ('3437e383-a973-4d88-bc4f-6b26d7ad9bd9', 'e159d9f0-9023-4e2c-8ec0-6df42e763cf8', null);
;
INSERT INTO item_groups(id, item_id, item_price, amount_ordered, shipping_date, order_id)
values ('fc556654-34af-4b2b-bdeb-f27c64d89092', '550e8400-e29b-41d4-a716-446655440000',19.99,5,NOW(),'09b0bab2-4825-4a69-a200-fe4f8bc80c56'),
       ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '550e8400-e29b-41d4-a716-446655440000',25.00,5,NOW(),'09b0bab2-4825-4a69-a200-fe4f8bc80c56'),
        ('6eb0f8ec-2451-47e3-a73d-f315a2a1e976', '550e8400-e29b-41d4-a716-446655440000',19.99,5,NOW(),'3437e383-a973-4d88-bc4f-6b26d7ad9bd9');
;
