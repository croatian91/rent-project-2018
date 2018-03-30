DELIMITER |
CREATE TRIGGER `before_insert_booking`
  BEFORE INSERT
  ON `Booking`
  FOR EACH ROW
  BEGIN

    DECLARE Diff_days, N_DMinL, N_DMaxL, Products_count INT;

    SELECT timestampdiff(DAY, NEW.end, NEW.start)
    INTO Diff_days;

    SELECT `N_DMinL`
    INTO N_DMinL
    FROM Configuration
    ORDER BY id DESC
    LIMIT 1;

    SELECT `N_DMaxL`
    INTO N_DMaxL
    FROM Configuration
    ORDER BY id DESC
    LIMIT 1;

    SELECT COUNT(*)
    INTO Products_count
    FROM Booking
    WHERE `product_id` = NEW.product_id AND NEW.end BETWEEN `start` AND `end`;

    -- End date before start date
    IF (NEW.end < NEW.start)
    THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'End DATE cannot be earlier than start DATE';

    -- RG2
    ELSEIF (Diff_days < N_DMinL)
      THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'RG2: Rent duration can\'t be less than N_DmL';

    -- RG4
    ELSEIF (Diff_days > N_DMaxL)
      THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'RG2: Rent duration can\'t be more than N_DML';

    -- Product already booked
    ELSEIF (Products_count > 0)
      THEN SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Product already booked';
    END IF;
  END |