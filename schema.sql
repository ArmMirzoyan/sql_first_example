CREATE DATABASE item_groups;
CREATE TABLE group_repository
(
    group_id   bigint,
    group_name varchar(255) NOT NULL,
    PRIMARY KEY (group_id)
);
CREATE TABLE item_repository
(
    item_id         bigint,
    item_base_price MONEY,
    item_name       varchar(255) NOT NULL,
    item_imageUrl   varchar,
    PRIMARY KEY (item_id)
);
CREATE TABLE item_groups_relation
(
    group_id bigint,
    item_id  bigint,
    CONSTRAINT id_item FOREIGN KEY (group_id) References item_repository(item_id),
    CONSTRAINT id_group FOREIGN KEY (item_id) References group_repository(group_id)
);

DROP TABLE group_repository;
DROP TABLE item_repository;
DROP DATABASE item_groups;