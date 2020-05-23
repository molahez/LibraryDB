CREATE SCHEMA IF NOT EXISTS `bookstore` ;
USE `bookstore` ;

CREATE TABLE IF NOT EXISTS `bookstore`.`PUBLISHER` (
  `Name` VARCHAR(100) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  `Phonenumber` CHAR(50) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bookstore`.`BOOK` (
	`ISBNnumber` INT NOT NULL,
	`Title` VARCHAR(100) NOT NULL,
	`Author` VARCHAR(100) NOT NULL,
	`PublisherName` VARCHAR(100) NOT NULL,
	`PublicationYear` year NOT NULL,
	`SellingPrice` INT NOT NULL,
	`Category` VARCHAR(100) NOT NULL,
	`Copies` INT NOT NULL,
    
	threshold INT,
	PRIMARY KEY (`ISBNnumber`),
	CONSTRAINT `publisher_name`
    FOREIGN KEY (`PublisherName`)
    REFERENCES `bookstore`.`PUBLISHER` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `bookstore`.`BookOrders` (
  `ISBNnumber` INT NOT NULL,
  `Copies` INT NOT NULL,
  PRIMARY KEY (`ISBNnumber`),
  CONSTRAINT `ISBN_number`
    FOREIGN KEY (`ISBNnumber`)
    REFERENCES `bookstore`.`BOOK` (`ISBNnumber`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

Create table if not exists `bookstore`.`users`(
	`username` varchar(100) not null,
    `password` varchar(100) not null,
    `lastName` varchar(100),
    `firstName` varchar(100),
    `email` varchar(100),
    `phoneNumber` char(50),
    `shippingAddress` varchar(100),
    `status` varchar(100),
    PRIMARY KEY (`username`)
    )
ENGINE = InnoDB;

Create table if not exists `bookstore`.`cart`(
	`ISBN` INT not null,
    `price` INT not null,
    `copies` Int not null,
    `totalPrice` Int,
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookstore`.`BOOK` (`ISBNnumber`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

Create table if not exists `bookstore`.`soldBooks`(
	`ISBN` INT not null,
    `sellingDate` date,
    `soldCopies` varchar(100),
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookstore`.`BOOK` (`ISBNnumber`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

Create table if not exists `bookstore`.`purchases`(
	`username` varchar(100),
    `buyingDate` date,
	`amountPaid` INT,
    FOREIGN KEY (`username`)
    REFERENCES `bookstore`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


DELIMITER $$

CREATE PROCEDURE sell_book(
	ISBN INT,
    quantity INT,
    username varchar(100)
)
BEGIN
	DECLARE old_quantity INT;
    Declare curr_date date;
    declare amount int;
    
    SELECT CURRENT_TIMESTAMP into curr_date;
    
    SELECT Copies From BOOK where ISBN = ISBNnumber
    INTO old_quantity;
	UPDATE BOOK
	SET Copies = old_quantity - quantity WHERE ISBN = ISBNnumber;
    
    Select SellingPrice from BOOK where ISBN = ISBNnumber
    into amount;
    
    
    delete from purchases where (select DATEDIFF(curr_date, buyingDate) > 30);
    delete from soldBooks where (select DATEDIFF( curr_date, sellingDate) > 30);
    Insert into soldBooks values(ISBN, curr_date, quantity);
    Insert into purchases values(username, curr_date, amount * quantity);
    
END$$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE received_Book_Order(
	ISBN INT
)
BEGIN
	DELETE FROM BookOrders WHERE ISBN = ISBNnumber;
END$$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE log_out(
	
)
BEGIN
	delete from cart;
END$$

DELIMITER ;

 
DELIMITER $$
CREATE TRIGGER before_updating_book 
BEFORE UPDATE ON BOOK
FOR EACH ROW
BEGIN
	if new.Copies < 0 then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "can't update, the amount of book copies will be negative";
	end if;
    if new.Category != 'Science' AND new.Category != 'Art' AND new.Category != 'Religion'  AND new.Category != 'History'  AND new.Category != 'Geography'  then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted category in incorrect";
	end if;
END $$
DELIMITER ;  

DELIMITER $$
CREATE TRIGGER placing_book_orders 
AFTER UPDATE ON BOOK
FOR EACH ROW
BEGIN
	if new.Copies < new.threshold then
		INSERT INTO BookOrders VALUES (new.ISBNnumber, new.threshold - new.Copies);
	end if;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER confirm_receiving_orders 
BEFORE DELETE ON BookOrders
FOR EACH ROW
BEGIN
	DECLARE ordered_copies INT;
    DECLARE ISBN INT;
    SET ordered_copies = old.Copies;
    SET ISBN = old.ISBNnumber;
	UPDATE BOOK
    SET Copies = Copies + ordered_copies
    WHERE ISBN = ISBNnumber;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_inserting_book
BEFORE INSERT ON BOOK
FOR EACH ROW
BEGIN
	if new.Category != 'Science' AND new.Category != 'Art' AND new.Category != 'Religion'  AND new.Category != 'History'  AND new.Category != 'Geography'  then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted category in incorrect";
	end if;
END $$
DELIMITER ;

SET SQL_SAFE_UPDATES = 0;
