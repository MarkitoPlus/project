USE SE;
INSERT INTO Accounts(account, password) VALUES ('snovel00', '00000000');
INSERT INTO Accounts(account, password) VALUES ('snovel01', '00000000');
INSERT INTO Accounts(account, password) VALUES ('snovel02', '00000000');
INSERT INTO Accounts(account, password) VALUES ('pnovel00', '00000000');
INSERT INTO Accounts(account, password) VALUES ('pnovel01', '00000000');
INSERT INTO Accounts(account, password) VALUES ('pnovel02', '00000000');

INSERT INTO Services(service_name, phone, mail, account_id) VALUES ('snovel00n', '18888918780', 'snovel00@gmail.com', 1);
INSERT INTO Services(service_name, phone, mail, account_id) VALUES ('snovel01n', '18888918781', 'snovel01@gmail.com', 2);
INSERT INTO Services(service_name, phone, mail, account_id) VALUES ('snovel02n', '18888918782', 'snovel02@gmail.com', 3);

INSERT INTO Patients(patient_name, age, phone, mail, account_id) VALUES ('pnovel00n', 25, '18888918700', 'pnovel00@gmail.com', 4);
INSERT INTO Patients(patient_name, age, phone, mail, account_id) VALUES ('pnovel01n', 28, '18888918710', 'pnovel01@gmail.com', 5);
INSERT INTO Patients(patient_name, age, phone, mail, account_id) VALUES ('pnovel02n', 35, '18888918720', 'pnovel02@gmail.com', 6);
