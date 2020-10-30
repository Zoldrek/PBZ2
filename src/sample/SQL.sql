CREATE TABLE storage(
                        id_storage BIGSERIAL,
                        number INT,
                        name VARCHAR(20),
                        telephone VARCHAR(20),
                        PRIMARY KEY (id_storage)
);

--select sum(col) from table where date< and name=
CREATE TABLE inventory(
                        id_inventory BIGSERIAL,
                        name VARCHAR(20),
                        type VARCHAR(20),
                        PRIMARY KEY (id_inventory)
);
CREATE TABLE bill(
    id_bill BIGSERIAL,
    id_storage INT,
    id_inventory INT,
    quantity DOUBLE PRECISION,
    date DATE,
    name_employee VARCHAR(30),
    position_employee VARCHAR(20),
    PRIMARY KEY (id_bill),
    FOREIGN KEY (id_inventory) REFERENCES inventory(id_inventory)  ON delete  set null,
    FOREIGN KEY (id_storage) REFERENCES storage(id_storage)  ON delete  set null
);

select a.name,b.date,b.quantity from inventory a
inner join bill b on b.id_inventory=a.id_inventory WHERE a.name='inv1';

SELECT c.name,sum(quantity) FROM bill b
inner join  storage a on a.id_storage=b.id_storage
inner join  inventory c on c.id_inventory=b.id_inventory
where a.name='sklad2' AND b.date<='2019-12-04' AND c.type='спецодежда' group by c.name;

