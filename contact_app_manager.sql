--   create table contact in the contact_app_manager database
CREATE TABLE contact 
(
  contact_id int NOT NULL AUTO_INCREMENT,
  Fname VARCHAR(15) DEFAULT NULL,
  Mname VARCHAR(15) DEFAULT NULL,
  Lname VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (contact_id)
);

--   create address table in the contact_app_manager database
CREATE TABLE address 
(
  address_id INT NOT NULL AUTO_INCREMENT,
  contac_id INT DEFAULT NULL,
  address_type VARCHAR(4) DEFAULT NULL,
  address_street VARCHAR(45) DEFAULT NULL,
  city VARCHAR(15) DEFAULT NULL,
  state VARCHAR(15) DEFAULT NULL,
  zipcode VARCHAR(5) DEFAULT NULL,
  PRIMARY KEY (address_id),
  KEY contac_id_idx (contac_id),
  CONSTRAINT contac_id FOREIGN KEY (contac_id) REFERENCES contact (contact_id) ON DELETE CASCADE ON UPDATE CASCADE
  );

-- create table date in the contact_app_manager database
CREATE TABLE date 
(
  date_id INT NOT NULL AUTO_INCREMENT,
  con_id INT DEFAULT NULL,
  date_type VARCHAR(10) DEFAULT NULL,
  date_birth VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (date_id),
  KEY con_id_idx (con_id),
  CONSTRAINT con_id FOREIGN KEY (con_id) REFERENCES contact (contact_id) ON DELETE CASCADE ON UPDATE CASCADE
  );

--  create table phone in the contact_app_manager database
CREATE TABLE phone 
(
  phone_id int NOT NULL AUTO_INCREMENT,
  c_id int DEFAULT NULL,
  phone_type VARCHAR(4) DEFAULT NULL,
  area_code VARCHAR(3) DEFAULT NULL,
  pnumber VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (phone_id),
  KEY c_id_idx (c_id),
  CONSTRAINT c_id FOREIGN KEY (c_id) REFERENCES contact (contact_id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

create table contacts
(
	contact_id INT,
	first_name varchar(50),
    middle_name varchar(50),
    last_name varchar(50),
    home_phone VARCHAR(15),
    cell_phone VARCHAR(15),
    home_address text,
    home_city VARCHAR(50),
    home_state VARCHAR(50),
    home_zip INT null,
    work_phone VARCHAR(15),
    work_address text,
    work_city VARCHAR(50),
    work_state VARCHAR(50),
    work_zip INT null,
    birth_date date null
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Contacts.csv' 
INTO TABLE contacts 
FIELDS TERMINATED BY "," 
LINES TERMINATED BY "\n" 
IGNORE 1 ROWS
(contact_id, first_name, middle_name, last_name, home_phone, cell_phone, home_address, home_city, home_state, @vhome_zip, work_phone, work_address, work_city, work_state, @vwork_zip, @vbirth_date) 
SET 
home_zip = NULLIF(@vhome_zip,''),
work_zip = NULLIF(@vhome_zip,''),
birth_date = NULLIF(@vbirth_date, '')
;
--    STR_TO_DATE(@vbirth_date,'%m-%d-%Y')

-- Inserting Contact info from CSV
insert into contact (Fname, Mname, Lname)
select first_name, middle_name, last_name
from contacts;

-- Inserting Home Address into DB table Address
insert into address (contac_id, address_type, address_street, city, state, zipcode)
select contact_id, 'home', home_address, home_city, home_state, home_zip
from contacts;

-- Inserting Work Address into DB table Address
insert into address (contac_id, address_type, address_street, city, state, zipcode)
select contact_id, 'work', work_address, work_city, work_state, work_zip
from contacts;

-- Inserting Date into DB table Date
insert into date (con_id, date_type, date_birth)
select contact_id, 'birth date', birth_date
from contacts;


-- Inserting Phone numbers in DB table Phone
insert into phone (c_id, phone_type, area_code, pnumber)
select contact_id, 'work', left(work_phone,3), work_phone
from contacts;

insert into phone (c_id, phone_type, area_code, pnumber)
select contact_id, 'home', left(home_phone,3), home_phone
from contacts;

insert into phone (c_id, phone_type, area_code, pnumber)
select contact_id, 'cell', left(cell_phone,3), cell_phone
from contacts;
