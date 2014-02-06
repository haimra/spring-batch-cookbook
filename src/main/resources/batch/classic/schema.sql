CREATE TABLE CUSTOMER
(
  id BIGINT IDENTITY NOT NULL PRIMARY KEY ,
  first_name VARCHAR(40),	
  last_name VARCHAR(40),
  card_number VARCHAR(100),
 );