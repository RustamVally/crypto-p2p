CREATE TABLE orders
(
    id               BIGSERIAL PRIMARY KEY,
    salesman         TEXT        NOT NULL,
    price            INT         NOT NULL CHECK ( price > 0 ),
    qty              INT         NOT NULL CHECK ( qty > 0 ),
    review           INT                                                  DEFAULT 0,
    min_amount       INT         NOT NULL CHECK ( min_amount >= 0),
    max_amount       INT         NOT NULL CHECK ( max_amount >= 5000000),
    successful_deals INT         NOT NULL CHECK ( successful_deals >= 0 ) DEFAULT 0,
    status           TEXT        NOT NULL,
    proStatus        TEXT        NOT NULL,
    removed          BOOLEAN     NOT NULL                                 DEFAULT FALSE,
    created          timestamptz NOT NULL                                 DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sales
(
    id      BIGSERIAL PRIMARY KEY,
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE purchases
(
    id      BIGSERIAL PRIMARY KEY,
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

