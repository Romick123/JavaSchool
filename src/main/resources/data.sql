--(password = 1234)

INSERT INTO users (email, password, role) VALUES
('admin@test.com', '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'ADMIN'),
('teacher@test.com', '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'TEACHER'),
('parent@test.com', '$2a$10$5JIX0dRaCZhxFnzja2J9G.Sm55W6m/AZi3UxAZeJNDGq6TrEUdhg2', 'PARENT');

INSERT INTO class_room (name) VALUES
('5A'),
('6B');

INSERT INTO students (name, class_room_id) VALUES
('Ivan Petrov', 1),
('Anna Smirnova', 1),
('Pavel Ivanov', 2);

INSERT INTO subjects (name) VALUES
('Mathematics'),
('History'),
('Physics');

INSERT INTO teachers (name, user_id) VALUES
('Mr. Brown', 2);

INSERT INTO schedule (class_room_id, subject_id, teacher_id, day_of_week, lesson_number) VALUES
(1, 1, 1, 'MONDAY', 1),
(1, 2, 1, 'MONDAY', 2),
(2, 3, 1, 'TUESDAY', 1);

INSERT INTO grades (student_id, subject_id, grade_value, type_of_work, grade_date) VALUES
(1, 1, 5, 'Test', '2026-01-10'),
(1, 2, 4, 'Homework', '2026-01-11'),
(2, 1, 3, 'Quiz', '2026-01-12');

INSERT INTO attendance (student_id, date, present) VALUES
(1, '2026-01-10', true),
(2, '2026-01-10', false),
(3, '2026-01-11', true);

INSERT INTO homeworks (subject_id, class_room_id, description, due_date) VALUES
(1, 1, 'Solve exercises 1-10 from textbook', '2026-01-15'),
(2, 1, 'Read chapter 3', '2026-01-16');
