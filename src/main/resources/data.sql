--password = 1234
INSERT INTO users (email, password, role) VALUES
('admin@test.com',   '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'ADMIN'),
('teacher1@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher2@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher3@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher4@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher5@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher6@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher7@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('teacher8@test.com','$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('parent@test.com',  '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'PARENT');

INSERT INTO class_room (name) VALUES
('5A'),('5B'),('6A'),('6B'),('7A'),
('7B'),('8A'),('8B'),('9A'),('9B');

INSERT INTO subjects (name) VALUES
('Mathematics'),
('Physics'),
('History'),
('Biology'),
('Chemistry'),
('English'),
('Geography'),
('Computer Science');

INSERT INTO teachers (name, user_id) VALUES
('Mr. Brown',(SELECT id FROM users WHERE email = 'teacher1@test.com')),
('Mrs. Green',(SELECT id FROM users WHERE email = 'teacher2@test.com')),
('Mr. White',(SELECT id FROM users WHERE email = 'teacher3@test.com')),
('Mrs. Black',(SELECT id FROM users WHERE email = 'teacher4@test.com')),
('Mr. Stone',(SELECT id FROM users WHERE email = 'teacher5@test.com')),
('Mrs. Hill',(SELECT id FROM users WHERE email = 'teacher6@test.com')),
('Mr. Wood',(SELECT id FROM users WHERE email = 'teacher7@test.com')),
('Mrs. King',(SELECT id FROM users WHERE email = 'teacher8@test.com'));

INSERT INTO teachers_subjects (teacher_id, subject_id) VALUES
((SELECT id FROM teachers WHERE name = 'Mr. Brown'),(SELECT id FROM subjects WHERE name = 'Mathematics')),((SELECT id FROM teachers WHERE name = 'Mr. Brown'),(SELECT id FROM subjects WHERE name = 'Physics')),
((SELECT id FROM teachers WHERE name = 'Mrs. Green'),(SELECT id FROM subjects WHERE name = 'History')),((SELECT id FROM teachers WHERE name = 'Mrs. Green'),(SELECT id FROM subjects WHERE name = 'Geography')),
((SELECT id FROM teachers WHERE name = 'Mr. White'),(SELECT id FROM subjects WHERE name = 'Biology')),((SELECT id FROM teachers WHERE name = 'Mr. White'),(SELECT id FROM subjects WHERE name = 'Chemistry')),
((SELECT id FROM teachers WHERE name = 'Mrs. Black'),(SELECT id FROM subjects WHERE name = 'English')),((SELECT id FROM teachers WHERE name = 'Mrs. Black'),(SELECT id FROM subjects WHERE name = 'Computer Science')),
((SELECT id FROM teachers WHERE name = 'Mr. Stone'),(SELECT id FROM subjects WHERE name = 'Mathematics')),((SELECT id FROM teachers WHERE name = 'Mr. Stone'),(SELECT id FROM subjects WHERE name = 'Chemistry')),
((SELECT id FROM teachers WHERE name = 'Mrs. Hill'),(SELECT id FROM subjects WHERE name = 'Physics')),((SELECT id FROM teachers WHERE name = 'Mrs. Hill'),(SELECT id FROM subjects WHERE name = 'Biology')),
((SELECT id FROM teachers WHERE name = 'Mr. Wood'),(SELECT id FROM subjects WHERE name = 'History')),((SELECT id FROM teachers WHERE name = 'Mr. Wood'),(SELECT id FROM subjects WHERE name = 'English')),
((SELECT id FROM teachers WHERE name = 'Mrs. King'),(SELECT id FROM subjects WHERE name = 'Geography')),((SELECT id FROM teachers WHERE name = 'Mrs. King'),(SELECT id FROM subjects WHERE name = 'Computer Science'));

INSERT INTO students (name, class_room_id) VALUES
('Ivan Petrov',1),
('Anna Smirnova',1),
('Pavel Ivanov',1),
('Olga Sidorova',1),
('Dmitry Kuznetsov',1),
('Maria Volkova',1),
('Sergey Orlov',1),
('Elena Morozova',1),
('Alexey Fedorov',1),
('Natalia Pavlova',1),

('Kirill Popov',2),
('Irina Lebedeva',2),
('Roman Kozlov',2),
('Svetlana Novikova',2),
('Maksim Egorov',2),
('Alina Vasilieva',2),
('Denis Zaitsev',2),
('Polina Antonova',2),
('Nikita Sorokin',2),
('Yulia Romanova',2);

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
