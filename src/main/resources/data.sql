--password = 1234
INSERT INTO users (id, email, password, role) VALUES
(1, 'admin@test.com',   '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'ADMIN'),
(2, 'teacher1@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(3, 'teacher2@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(4, 'teacher3@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(5, 'teacher4@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(6, 'teacher5@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(7, 'teacher6@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(8, 'teacher7@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(9, 'teacher8@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
(10,'parent@test.com',  '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'PARENT');

INSERT INTO class_room (id, name) VALUES
(1,'5A'),(2,'5B'),(3,'6A'),(4,'6B'),(5,'7A'),
(6,'7B'),(7,'8A'),(8,'8B'),(9,'9A'),(10,'9B');

INSERT INTO subjects (id, name) VALUES
(1,'Mathematics'),
(2,'Physics'),
(3,'History'),
(4,'Biology'),
(5,'Chemistry'),
(6,'English'),
(7,'Geography'),
(8,'Computer Science');

INSERT INTO teachers (id, name, user_id) VALUES
(1,'Mr. Brown',2),
(2,'Mrs. Green',3),
(3,'Mr. White',4),
(4,'Mrs. Black',5),
(5,'Mr. Stone',6),
(6,'Mrs. Hill',7),
(7,'Mr. Wood',8),
(8,'Mrs. King',9);

INSERT INTO teachers_subjects (teacher_id, subject_id) VALUES
(1,1),(1,2),
(2,3),(2,7),
(3,4),(3,5),
(4,6),(4,8),
(5,1),(5,5),
(6,2),(6,4),
(7,3),(7,6),
(8,7),(8,8);

INSERT INTO students (id, name, class_room_id) VALUES
(1,'Ivan Petrov',1),(2,'Anna Smirnova',1),(3,'Pavel Ivanov',1),(4,'Olga Sidorova',1),
(5,'Dmitry Kuznetsov',1),(6,'Maria Volkova',1),(7,'Sergey Orlov',1),(8,'Elena Morozova',1),
(9,'Alexey Fedorov',1),(10,'Natalia Pavlova',1),

(11,'Kirill Popov',2),(12,'Irina Lebedeva',2),(13,'Roman Kozlov',2),(14,'Svetlana Novikova',2),
(15,'Maksim Egorov',2),(16,'Alina Vasilieva',2),(17,'Denis Zaitsev',2),(18,'Polina Antonova',2),
(19,'Nikita Sorokin',2),(20,'Yulia Romanova',2);

INSERT INTO schedule (class_room_id, subject_id, teacher_id, day_of_week, lesson_number) VALUES
(1,1,1,'MONDAY',1),(1,6,4,'MONDAY',2),(1,3,2,'MONDAY',3),
(1,2,6,'TUESDAY',1),(1,4,3,'TUESDAY',2),(1,8,4,'TUESDAY',3),
(1,5,3,'WEDNESDAY',1),(1,7,2,'WEDNESDAY',2),(1,1,5,'WEDNESDAY',3),

(2,1,1,'MONDAY',1),(2,6,4,'MONDAY',2),(2,3,7,'MONDAY',3),
(2,2,6,'TUESDAY',1),(2,4,3,'TUESDAY',2),(2,8,8,'TUESDAY',3),
(2,5,5,'WEDNESDAY',1),(2,7,8,'WEDNESDAY',2),(2,1,5,'WEDNESDAY',3);

INSERT INTO homeworks (subject_id, class_room_id, description, homework_date) VALUES
(1,1,'Solve exercises 1-20 from textbook','2026-01-15'),
(6,1,'Learn 20 new words','2026-01-16'),
(3,1,'Read chapter 4','2026-01-17'),
(1,2,'Workbook page 10','2026-01-15'),
(8,2,'Prepare presentation about computers','2026-01-18');

INSERT INTO grades (student_id, subject_id, grade_value, grade_date) VALUES
(1,1,5,'2026-01-10'),
(2,1,4,'2026-01-10'),
(3,6,5,'2026-01-11'),
(11,1,3,'2026-01-10'),
(12,8,5,'2026-01-12');

INSERT INTO attendance (student_id, subject_id, attendance_date, present) VALUES
(1,1,'2026-01-10',true),
(2,1,'2026-01-10',false),
(3,6,'2026-01-11',true),
(11,1,'2026-01-10',true),
(12,8,'2026-01-12',true);
