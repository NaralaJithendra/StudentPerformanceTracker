DROP TABLE IF EXISTS students;

CREATE TABLE students (
                          studentId INT PRIMARY KEY,
                          name VARCHAR(100),
                          course VARCHAR(100),
                          marks DOUBLE
);


INSERT INTO students VALUES (1, 'Alice', 'Math', 85.5);
INSERT INTO students VALUES (2, 'Bob', 'Science', 72.0);
INSERT INTO students VALUES (3, 'Charlie', 'Math', 90.0);
INSERT INTO students VALUES (4, 'David', 'History', 65.5);
INSERT INTO students VALUES (5, 'Eva', 'Science', 78.0);
INSERT INTO students VALUES (6, 'Frank', 'History', 81.0);
INSERT INTO students VALUES (7, 'Grace', 'Math', 88.0);
INSERT INTO students VALUES (8, 'Hannah', 'Science', 92.5);
INSERT INTO students VALUES (9, 'Ivan', 'History', 70.0);
INSERT INTO students VALUES (10, 'Judy', 'Math', 60.0);
