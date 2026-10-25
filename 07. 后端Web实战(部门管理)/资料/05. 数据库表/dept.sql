-- Create the department table
CREATE TABLE dept (
    id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, Primary Key',
    name varchar(50) NOT NULL UNIQUE COMMENT 'Department Name',
    create_time datetime DEFAULT NULL COMMENT 'Creation Time',
    update_time datetime DEFAULT NULL COMMENT 'Last Update Time'
) COMMENT 'Department Table';

-- Insert data into the department table
INSERT INTO dept VALUES 
(1, 'Student Affairs', '2024-09-25 09:47:40', '2024-09-25 09:47:40'),
(2, 'Teaching & Research', '2024-09-25 09:47:40', '2024-09-09 15:17:04'),
(3, 'Consulting', '2024-09-25 09:47:40', '2024-09-30 21:26:24'),
(4, 'Career Services', '2024-09-25 09:47:40', '2024-09-25 09:47:40'),
(5, 'Human Resources', '2024-09-25 09:47:40', '2024-09-25 09:47:40'),
(6, 'Administration', '2024-11-30 20:56:37', '2024-09-30 20:56:37');