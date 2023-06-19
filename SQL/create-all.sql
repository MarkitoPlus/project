CREATE DATABASE IF NOT EXISTS SE;
USE SE;

CREATE TABLE IF NOT EXISTS Accounts(
	id INT PRIMARY KEY auto_increment,
	account VARCHAR(30) UNIQUE NOT NULL,
	password VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Patients(
	patient_id INT PRIMARY KEY auto_increment,
	patient_name VARCHAR(20) not NULL,
	age INT not NULL,
	phone VARCHAR(30) NOT NULL,
	mail VARCHAR(40) NOT NULL,
	account_id INT NOT NULL,
	FOREIGN KEY(account_id) REFERENCES Accounts(id) 
);

CREATE TABLE IF NOT EXISTS Services (
	id INT PRIMARY KEY auto_increment,
	service_name VARCHAR(20) NOT NULL,
  phone VARCHAR(12) NOT NULL,
  mail VARCHAR(40) NOT NULL,
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

CREATE TABLE IF NOT EXISTS Dept(
	id INT PRIMARY KEY auto_increment, 
	dept_name VARCHAR (20) NOT NULL, 
	description VARCHAR (50) 
);

CREATE TABLE IF NOT EXISTS Doctors(
	id INT PRIMARY KEY auto_increment,
	doctor_name VARCHAR(20) NOT NULL,
	age INT NOT NULL,
	phone VARCHAR(30) NOT NULL,
	mail varchar(40) NOT NULL,
	dept_id INT NOT NULL,
  account_id INT NOT NULL,
	status TINYINT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES Accounts(id),
	FOREIGN KEY (dept_id) REFERENCES Dept(id)
);

CREATE TABLE IF NOT EXISTS Prices(
	dept_id INT NOT NULL,
	status TINYINT NOT NULL,
	price INT NOT NULL,
	FOREIGN KEY (dept_id) REFERENCES Dept(id)
);

CREATE TABLE IF NOT EXISTS Managers(
 id INT PRIMARY KEY auto_increment,
 manager_name VARCHAR(20) NOT NULL,
 age INT not NULL,
 phone VARCHAR(30) NOT NULL,
 mail VARCHAR(40) NOT NULL,
 account_id INT NOT NULL,
 FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

CREATE TABLE IF NOT EXISTS TimeSlot(
	id INT PRIMARY KEY auto_increment,
	doctor_id INT NOT NULL,
	time_slot TINYINT NOT NULL,
	month_day DATE NOT NULL,
	allowance INT NOT NULL,
	FOREIGN KEY (doctor_id) REFERENCES Doctors(id)
);

CREATE TABLE IF NOT EXISTS Appointment(
	id INT PRIMARY KEY auto_increment,
	patient_id INT NOT NULL,
	timeslot_id INT NOT NULL,
	status TINYINT NOT NULL,
	FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),
	FOREIGN KEY (timeslot_id) REFERENCES TimeSlot(id)
);

CREATE TABLE IF NOT EXISTS Cases(
	id INT PRIMARY KEY auto_increment, 
  doctor_id INT NOT NULL,
  patient_id INT NOT NULL,
  prescription VARCHAR(50),
  description VARCHAR(200),
  FOREIGN KEY (doctor_id) REFERENCES Doctors(id),
  FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
);

CREATE TABLE IF NOT EXISTS Chats(
	id INT PRIMARY KEY auto_increment,
	title VARCHAR(100) NOT NULL,
	service_id INT NOT NULL,
	patient_id INT NOT NULL,
	start_time DATETIME NOT NULL,
	end_time DATETIME,
	star TINYINT,
	FOREIGN KEY (service_id) REFERENCES Services(id),
  FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
);

CREATE TABLE IF NOT EXISTS ChatHistory(
	id INT PRIMARY KEY auto_increment,
	chat_id INT NOT NULL,
	seq INT NOT NULL,
	content VARCHAR(100) NOT NULL,
	from_type INT NOT NULL,
  to_type INT NOT NULL,
  time DATETIME NOT NULL,
	FOREIGN KEY (chat_id) REFERENCES Chats(id)
);
