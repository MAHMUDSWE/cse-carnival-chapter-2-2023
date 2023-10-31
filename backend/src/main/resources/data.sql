SELECT SCHEMA_NAME
FROM INFORMATION_SCHEMA.SCHEMATA
WHERE SCHEMA_NAME = 'reachout';

INSERT INTO district (name)
VALUES
    ('sylhet'),
    ('dhaka'),
    ('chittagong')
    ON DUPLICATE KEY UPDATE name = name;

INSERT INTO thana (name)
VALUES
    ('x'),
    ('y'),
    ('z')
    ON DUPLICATE KEY UPDATE name = name;

INSERT INTO roles(name)
VALUES ("ROLE_ADMIN"), ("ROLE_DOCTOR"), ("ROLE_PATIENT") ON DUPLICATE KEY UPDATE name = name;

INSERT INTO doctor_type (name)
VALUES
    ('General Practitioner'),
    ('Cardiologist'),
    ('Dermatologist'),
    ('Pediatrician'),
    ('Orthopedic Surgeon'),
    ('Gynecologist'),
    ('Oncologist'),
    ('Neurologist')
    ON DUPLICATE KEY UPDATE name = name;
