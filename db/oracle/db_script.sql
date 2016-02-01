DROP TABLE Products cascade constraint;
CREATE TABLE Products (
	id INTEGER,
	name VARCHAR(35),
	unitPriceET DECIMAL(6,2),
	amount INTEGER,
	catalogId INTEGER,
	CONSTRAINT PK_PRODUCTS PRIMARY KEY (id),
	CONSTRAINT NN_PRODUCTS_NAME CHECK (name IS NOT NULL),
	CONSTRAINT NN_PRODUCTS_PRICE CHECK (unitPriceET IS NOT NULL),
	CONSTRAINT NN_PRODUCTS_AMOUNT CHECK (amount IS NOT NULL),
	CONSTRAINT CK_PRODUCTS_PRICE CHECK (unitPriceET >= 0),
	CONSTRAINT CK_PRODUCTS_AMOUNT CHECK (amount >= 0),
	CONSTRAINT FK_PRODUCTS_CATALOGS FOREIGN KEY (catalogId) REFERENCES Catalogs (id)
);

DROP TABLE Catalogs cascade constraint;
CREATE TABLE Catalogs (
	id INTEGER,
	name VARCHAR(35),
	CONSTRAINT PK_CATALOGS PRIMARY KEY (id),
	CONSTRAINT UN_CATALOGS_NAME UNIQUE (name),
	CONSTRAINT NN_CATALOGS_NAME CHECK (name IS NOT NULL)
);

CREATE SEQUENCE seqCatalogs
START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE seqProducts
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER checkProductName BEFORE INSERT ON Products
FOR EACH ROW
DECLARE
	v_sameNameNb INTEGER;
BEGIN
	SELECT COUNT(id) INTO v_sameNameNb
	FROM Products
	WHERE name = :NEW.name
	AND catalogId = :NEW.catalogId;

	IF v_sameNameNb > 0 THEN
		RAISE_APPLICATION_ERROR(-20001, 'Un produit du meme nom existe deja dans ce catalogue.');
	END IF;
END;

CREATE OR REPLACE PROCEDURE addProduct (
	p_name Products.name%TYPE,
	p_unitPriceET Products.unitPriceET%TYPE,
	p_amount Products.amount%TYPE,
	p_catalogName Catalogs.name%TYPE
) IS

v_catalogId Products.catalogId%TYPE;
v_sameNameNb INTEGER;

BEGIN
	SELECT id INTO v_catalogId FROM Catalogs WHERE name = p_catalogName;
	INSERT INTO Products(id, name, unitPriceET, amount, catalogId) VALUES (seqProducts.NEXTVAL, p_name, p_unitPriceET, p_amount, v_catalogId);
END;

CREATE OR REPLACE PROCEDURE addCatalog (
	p_name Catalogs.name%TYPE
) IS

BEGIN
	INSERT INTO Catalogs(id, name) VALUES (seqProducts.NEXTVAL, p_name);
END;

CALL addProduct ('Mars', 10, 1, 1);
CALL addProduct ('Treets', 10, 1, 1);
CALL addProduct ('Raider', 12, 2, 1);
CALL addProduct ('Bounty', 12, 2, 1);
CALL addProduct ('Nuts', 1, 2, 1);
CALL addProduct ('Twix', 12.6, 1, 1);

CALL addCatalog ('Biscuits');
