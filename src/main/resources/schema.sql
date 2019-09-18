    CREATE TABLE the_order (
    id int not null AUTO_INCREMENT,
    type varchar(45),
     price bigint,
    quantity bigint,
    user_id varchar(45),
    primary key (id)
);