CREATE TABLE orders
(
    id               BIGSERIAL PRIMARY KEY,
    salesman         TEXT             NOT NULL,
    price            INT              NOT NULL CHECK ( price > 0 ),
    bitcoin          DOUBLE PRECISION NOT NULL CHECK ( bitcoin > 0 ),
    review           INT                                                       DEFAULT 0,
    min_amount       INT              NOT NULL CHECK ( min_amount >= 0),
    max_amount       INT              NOT NULL CHECK ( max_amount >= 50000),
    successful_deals INT              NOT NULL CHECK ( successful_deals >= 0 ) DEFAULT 0,
    status           TEXT             NOT NULL,
    proStatus        TEXT             NOT NULL,
    removed          BOOLEAN          NOT NULL                                 DEFAULT FALSE,
    created          timestamptz      NOT NULL                                 DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE coin
(
    id      BIGSERIAL PRIMARY KEY,
    name    TEXT        NOT NULL,
    price   INT         NOT NULL CHECK ( price >= 0 ),
    qty     INT         NOT NULL CHECK ( qty >= 0 ) DEFAULT 0,
    removed BOOLEAN     NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE deals
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT             NOT NULL,
    price       INT              NOT NULL CHECK ( price > 0 ),
    balance_btc DOUBLE PRECISION NOT NULL CHECK ( balance_btc > 0 ),
    balance_rub DOUBLE PRECISION,
    dealStatus  TEXT             NOT NULL CHECK ( dealStatus IN ('sale', 'buy') ),
    accept      BOOLEAN          NOT NULL DEFAULT FALSE,
    removed     BOOLEAN          NOT NULL DEFAULT FALSE,
    created     timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT        NOT NULL,
    balance_btc DOUBLE PRECISION,
    balance_rub INT,
    rating      DOUBLE PRECISION,
    removed     BOOLEAN     NOT NULL DEFAULT FALSE,
    created     timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);