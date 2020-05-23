-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookstore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bookstore` ;

-- -----------------------------------------------------
-- Table `bookstore`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`publisher` (
  `Name` VARCHAR(100) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  `Phonenumber` CHAR(50) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`book` (
  `ISBNnumber` INT NOT NULL,
  `Title` VARCHAR(100) NOT NULL,
  `Author` VARCHAR(100) NOT NULL,
  `PublisherName` VARCHAR(100) NOT NULL,
  `PublicationYear` YEAR NOT NULL,
  `SellingPrice` INT NOT NULL,
  `Category` VARCHAR(100) NOT NULL,
  `Copies` INT NOT NULL,
  `threshold` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ISBNnumber`),
  INDEX `publisher_name` (`PublisherName` ASC) VISIBLE,
  INDEX `ISBN_ASC` (`ISBNnumber` ASC) VISIBLE,
  CONSTRAINT `publisher_name`
    FOREIGN KEY (`PublisherName`)
    REFERENCES `bookstore`.`publisher` (`Name`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`bookorders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`bookorders` (
  `ISBNnumber` INT NOT NULL,
  `Copies` INT NOT NULL,
  PRIMARY KEY (`ISBNnumber`),
  CONSTRAINT `ISBN_number`
    FOREIGN KEY (`ISBNnumber`)
    REFERENCES `bookstore`.`book` (`ISBNnumber`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`cart` (
  `ISBN` INT NOT NULL,
  `price` INT NOT NULL,
  `copies` INT NOT NULL,
  `totalPrice` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `cart_ibfk_1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookstore`.`book` (`ISBNnumber`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`users` (
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NULL DEFAULT NULL,
  `firstName` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `phoneNumber` CHAR(50) NULL DEFAULT NULL,
  `shippingAddress` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`purchases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`purchases` (
  `username` VARCHAR(100) NULL DEFAULT NULL,
  `buyingDate` DATE NULL DEFAULT NULL,
  `amountPaid` INT NULL DEFAULT NULL,
  INDEX `username` (`username` ASC) VISIBLE,
  CONSTRAINT `purchases_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `bookstore`.`users` (`username`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookstore`.`soldbooks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`soldbooks` (
  `ISBN` INT NOT NULL,
  `sellingDate` DATE NULL DEFAULT NULL,
  `soldCopies` VARCHAR(100) NULL DEFAULT NULL,
  INDEX `ISBN` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `soldbooks_ibfk_1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookstore`.`book` (`ISBNnumber`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `bookstore` ;

-- -----------------------------------------------------
-- procedure log_out
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`SAMPLE`@`%` PROCEDURE `log_out`(
	
)
BEGIN
	delete from cart;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure received_Book_Order
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`SAMPLE`@`%` PROCEDURE `received_Book_Order`(
	ISBN INT
)
BEGIN
	DELETE FROM BookOrders WHERE ISBN = ISBNnumber;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sell_book
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`SAMPLE`@`%` PROCEDURE `sell_book`(
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
    
    
    delete from purchases where (select DATEDIFF(curr_date, buyingDate) > 90);
    delete from soldBooks where (select DATEDIFF( curr_date, sellingDate) > 90);
    Insert into soldBooks values(ISBN, curr_date, quantity);
    Insert into purchases values(username, curr_date, amount * quantity);
    
END$$

DELIMITER ;
USE `bookstore`;

DELIMITER $$
USE `bookstore`$$
CREATE
DEFINER=`SAMPLE`@`%`
TRIGGER `bookstore`.`before_inserting_book`
BEFORE INSERT ON `bookstore`.`book`
FOR EACH ROW
BEGIN
	if new.Category != 'Science' AND new.Category != 'Art' AND new.Category != 'Religion'  AND new.Category != 'History'  AND new.Category != 'Geography'  then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted category in incorrect";
	end if;
END$$

USE `bookstore`$$
CREATE
DEFINER=`SAMPLE`@`%`
TRIGGER `bookstore`.`before_updating_book`
BEFORE UPDATE ON `bookstore`.`book`
FOR EACH ROW
BEGIN
	if new.Copies < 0 then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "can't update, the amount of book copies will be negative";
	end if;
    if new.Category != 'Science' AND new.Category != 'Art' AND new.Category != 'Religion'  AND new.Category != 'History'  AND new.Category != 'Geography'  then
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted category in incorrect";
	end if;
END$$

USE `bookstore`$$
CREATE
DEFINER=`SAMPLE`@`%`
TRIGGER `bookstore`.`placing_book_orders`
AFTER UPDATE ON `bookstore`.`book`
FOR EACH ROW
BEGIN
	if new.Copies < new.threshold then
		INSERT INTO BookOrders VALUES (new.ISBNnumber, new.threshold - new.Copies);
	end if;
END$$

USE `bookstore`$$
CREATE
DEFINER=`SAMPLE`@`%`
TRIGGER `bookstore`.`confirm_receiving_orders`
BEFORE DELETE ON `bookstore`.`bookorders`
FOR EACH ROW
BEGIN
	DECLARE ordered_copies INT;
    DECLARE ISBN INT;
    SET ordered_copies = old.Copies;
    SET ISBN = old.ISBNnumber;
	UPDATE BOOK
    SET Copies = Copies + ordered_copies
    WHERE ISBN = ISBNnumber;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
