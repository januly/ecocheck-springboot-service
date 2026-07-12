INSERT IGNORE INTO app_users (user_id, name, email, password, role)
VALUES
('USR001', 'John Silva', 'john@gmail.com', '$2a$10$5WiLPUhLN6eo.uQ3WdJd2O7VbE7BLOxbaIRUMXyKA2gIYlcVtFVzm', 'USER'),
('USR002', 'Admin User', 'admin@ecocheck.lk', '$2a$10$5WiLPUhLN6eo.uQ3WdJd2O7VbE7BLOxbaIRUMXyKA2gIYlcVtFVzm', 'ADMIN');

INSERT IGNORE INTO action_categories (category_id, name, description)
VALUES
('CAT001', 'Transport', 'Transportation related climate actions'),
('CAT002', 'Energy Saving', 'Actions related to reducing electricity and fuel consumption'),
('CAT003', 'Waste Management', 'Actions related to waste reduction and recycling');

INSERT IGNORE INTO climate_actions (climate_action_id, title, description, estimated_co2_reduction, points, category_id)
VALUES
('ACT001', 'Plant a Tree', 'Plant one tree in your community', 20.00, 50, 'CAT002'),
('ACT002', 'Use Public Transport', 'Use bus or train instead of private vehicle', 8.50, 20, 'CAT001');

INSERT IGNORE INTO goals (goal_id, title, target_reduction, target_date, status, user_id)
VALUES
('GOAL001', 'Reduce 500kg CO2', 500.00, '2026-12-31', 'IN_PROGRESS', 'USR001');
