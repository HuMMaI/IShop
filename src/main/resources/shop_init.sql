DROP DATABASE IF EXISTS ishop;
CREATE DATABASE ishop;

USE ishop;

CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(30) NOT NULL UNIQUE,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    role VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL
);

CREATE TABLE bucket(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    CONSTRAINT user_bucket_FK FOREIGN KEY (user_id) REFERENCES users(id),
    product_id INT NOT NULL,
    CONSTRAINT product_bucket_FK FOREIGN KEY (product_id) REFERENCES products(id),
    purchase_date DATETIME NOT NULL
);