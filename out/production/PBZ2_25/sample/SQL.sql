
CREATE TABLE price_date(
    id_price_date BIGSERIAL,
    id_factory INT,
    id_product INT,
    purchase_price double precision,
    selling_price double precision,
    date DATE,
    name_employee VARCHAR(30),
    position_employee VARCHAR(20),
    PRIMARY KEY (id_price_date),
    FOREIGN KEY (id_factory) REFERENCES factory(id_factory)  ON delete  set null,
    FOREIGN KEY (id_product) REFERENCES product(id_product)  ON delete  set null
);

CREATE TABLE product(
    id_product BIGSERIAL,
    code INT,
    name VARCHAR(20),
    sort VARCHAR(20),
    groups VARCHAR(20),
    type VARCHAR(20),
    PRIMARY KEY (id_product)
);

CREATE TABLE factory(
    id_factory BIGSERIAL,
    code INT,
    type VARCHAR(20),
    name VARCHAR(20),
    address VARCHAR(20),
    telephone VARCHAR(20),
    id_region INT,
    PRIMARY KEY (id_factory),
    FOREIGN KEY (id_region) REFERENCES region(id_region)  ON delete  set null
);

CREATE TABLE region(
    id_region BIGSERIAL,
    city VARCHAR(20),
    area VARCHAR(20),
    PRIMARY KEY (id_region)
);


select * from price_date p
inner join product pr on p.id_product=pr.id_product
inner join factory f on p.id_factory = f.id_factory WHERE p.date=?;

SELECT * FROM price_date p
inner join product pr on p.id_product=pr.id_product
where date>=? and date <=?;


select AVG(purchase_price),AVG(selling_price),r.id_region from price_date p
inner join product pr on p.id_product=pr.id_product
inner join factory f on p.id_factory = f.id_factory
inner join region r on f.id_region = r.id_region
WHERE p.date=? and pr.type=? GROUP BY r.id_region;

