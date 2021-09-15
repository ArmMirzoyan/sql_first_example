CREATE DATABASE MYSTERIOUS_DATABASE;
CREATE TABLE "group"
(
    id     INT PRIMARY KEY,
    name   varchar(255) NOT NULL,
    parent INT REFERENCES "group" (id)
);
CREATE TABLE configuration
(
    id         serial PRIMARY KEY,
    Resolution varchar(3) NOT NULL CHECK (Resolution IN ('HD', 'FHD', '_4K'))
);
CREATE TABLE basket
(
    id   serial PRIMARY KEY,
    name varchar(256)
);
CREATE TABLE item
(
    id               INT PRIMARY KEY,
    base_price       MONEY        NOT NULL,
    currency         varchar(15)  NOT NULL,
    name             varchar(255) NOT NULL,
    imageUrl         varchar(255),
    complexity       DOUBLE PRECISION,
    configuration_id INT REFERENCES configuration (id),
    parent_group     INT REFERENCES "group" (id)
);
CREATE TABLE items_basket
(
    item_id   INT REFERENCES item (id),
    basket_id INT REFERENCES basket (id),
    id        INT PRIMARY KEY,
    CONSTRAINT item FOREIGN KEY (basket_id) References item (id),
    CONSTRAINT "group" FOREIGN KEY (item_id) References basket (id)
);
--DROP DATABASE MYSTERIOUS_DATABASE;
--DROP TABLE configuration;