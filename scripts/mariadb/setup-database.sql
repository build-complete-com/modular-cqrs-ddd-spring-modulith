CREATE DATABASE IF NOT EXISTS `modularmonolith`;

CREATE USER IF NOT EXISTS 'modularmonolith' IDENTIFIED BY 'modularmonolith';
GRANT ALL PRIVILEGES ON modularmonolith.* TO modularmonolith@'%';