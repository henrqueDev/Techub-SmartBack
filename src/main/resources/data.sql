use techub;

CREATE TABLE CLIENTS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_NAME VARCHAR(100),
    CPF VARCHAR(11)
);

CREATE TABLE PRODUCTS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_DESCRIPTION VARCHAR(100),
    PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE ORDERS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES CLIENTS (ID),
    ORDER_DATE TIMESTAMP,
    STATUS VARCHAR(20),
    TOTAL NUMERIC(20,2)
);

CREATE TABLE ORDER_ITEM (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID INTEGER REFERENCES ORDERS (ID),
    PRODUCT_ID INTEGER REFERENCES PRODUCTS (ID),
    QUANTIDADE INTEGER
);