USE Funn;

CREATE USER 'Funn'@'localhost' IDENTIFIED BY 'Funn1';

GRANT ALL PRIVILEGES ON Funn.* TO 'Funn'@'localhost';
