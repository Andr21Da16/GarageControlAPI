CREATE TABLE vehicle_types(
    id_type SERIAL NOT NULL,
    name_type VARCHAR(120) NOT NULL,
    price INTEGER NOT NULL,
    CONSTRAINT id_type_pk PRIMARY KEY (id_type),
    CONSTRAINT name_type UNIQUE (name_type)
);

CREATE TABLE companies(
    id_company SERIAL NOT NULL,
    name_company VARCHAR(120) NOT NULL,
    phone VARCHAR(20),
    CONSTRAINT id_company_pk PRIMARY KEY (id_company),
    CONSTRAINT name_company UNIQUE (name_company)
);

CREATE TABLE drivers(
    id_driver SERIAL NOT NULL,
    name_driver VARCHAR(120) NOT NULL,
    phone VARCHAR(20),
    id_company INTEGER NOT NULL,
    CONSTRAINT id_driver_pk PRIMARY KEY (id_driver),
    CONSTRAINT id_driver_id_company_fk FOREIGN KEY (id_company)
                        REFERENCES companies(id_company)
                        ON UPDATE RESTRICT
                        ON DELETE RESTRICT,
    CONSTRAINT uk_driver_company_phone
        UNIQUE (id_company, phone)
);

CREATE TABLE vehicles (
    id_vehicle SERIAL NOT NULL,
    name_vehicle VARCHAR(120),
    license_plate VARCHAR(20) NOT NULL,
    description TEXT,
    id_type INTEGER NOT NULL,
    id_company INTEGER NOT NULL,
    id_driver INTEGER NOT NULL,
    CONSTRAINT id_vehicle_pk PRIMARY KEY (id_vehicle),
    CONSTRAINT id_vehicle_id_type_fk FOREIGN KEY (id_type)
                      REFERENCES vehicle_types(id_type)
                      ON UPDATE RESTRICT
                      ON DELETE RESTRICT,
    CONSTRAINT id_vehicle_id_company_fk FOREIGN KEY (id_company)
                      REFERENCES companies(id_company)
                      ON UPDATE RESTRICT
                      ON DELETE RESTRICT,
     CONSTRAINT id_vehicle_id_driver_fk FOREIGN KEY (id_driver)
                      REFERENCES drivers(id_driver)
                      ON UPDATE RESTRICT
                      ON DELETE RESTRICT

);


INSERT INTO vehicle_types (name_type, price) VALUES
('Auto', 50),
('Camioneta', 80),
('Camión', 150),
('Bus', 200);


INSERT INTO companies (name_company, phone) VALUES
('Transporte Andree SAC', '999111222'),
('Logística Norte SRL', '988777666');


INSERT INTO drivers (name_driver, phone, id_company) VALUES
('Juan Pérez', '900111222', 1),
('Carlos López', '900333444', 1),
('Miguel Torres', '901555666', 2);


INSERT INTO vehicles (
    name_vehicle,
    license_plate,
    description,
    id_type,
    id_company,
    id_driver
) VALUES
(
    'Toyota Corolla',
    'ABC-123',
    'Auto para transporte ligero',
    1,  -- Auto
    1,  -- Transporte Andree SAC
    1   -- Juan Pérez
),
(
    'Hilux 4x4',
    'DEF-456',
    'Camioneta de carga media',
    2,  -- Camioneta
    1,
    2   -- Carlos López
),
(
    'Volvo FH',
    'GHI-789',
    'Camión de carga pesada',
    3,  -- Camión
    2,  -- Logística Norte SRL
    3   -- Miguel Torres
);


SELECT v.id_vehicle, v.license_plate, vt.name_type, c.name_company, d.name_driver
FROM vehicles v
JOIN vehicle_types vt ON vt.id_type = v.id_type
JOIN companies c ON c.id_company = v.id_company
JOIN drivers d ON d.id_driver = v.id_driver
WHERE d.id_company = 1;

SELECT * from vehicle_types;