INSERT INTO orders (salesman, price, bitcoin, review, min_amount, max_amount, successful_deals, status, proStatus)
VALUES ('Andrey', 3723470, 0.3, 100, 1000, 5000000, 0, 'online', 'image.jpeg'),
       ('Vanya', 3723470, 0.3, 100, 1000, 5000000, 0, 'online', 'image.jpeg');


INSERT INTO users (name, balance_btc, balance_rub, rating)
VALUES ('Andrey', 0.3, 10000, 4.8),
       ('Vanya', 0.2, 21000, 5.0);

INSERT INTO deals (salesman, price, balance_btc, balance_rub, dealstatus)
VALUES ('Andrey', 59000, 0.3, 10000, 'sale'),
       ('Vanya', 53000, 0.2, 21000,'buy');
