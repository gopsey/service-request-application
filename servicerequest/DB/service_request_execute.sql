CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(40) UNIQUE KEY,
    password VARCHAR(256),
    company_id INT,
    phone VARCHAR(20),
	FOREIGN KEY (company_id)
        REFERENCES company (id)
);
CREATE TABLE request (

    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_model VARCHAR(40),
    service_type VARCHAR(20),
    product_invoice_number VARCHAR(20),
    detailed_complaint TEXT,
    current_status CHAR,
    user_id INT,
	company_id INT,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)
        REFERENCES user (id),
	FOREIGN KEY (company_id)
        REFERENCES company (id)
);

CREATE TABLE role (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

CREATE TABLE user_role (
    user_id INT,
    FOREIGN KEY (user_id)
        REFERENCES user (id),
    role_id INT,
    FOREIGN KEY (role_id)
        REFERENCES role (id)
);

create table company (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40)
);

create table user_company (
	company_id INT,
    FOREIGN KEY (company_id)
        REFERENCES company (id),
    user_id INT,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

-- ALTER table user rename column company_name to company_id;
-- ALTER TABLE user ADD CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES company(id);
