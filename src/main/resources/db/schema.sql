CREATE TABLE IF NOT EXISTS app_users (
    user_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS action_categories (
    category_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS climate_actions (
    climate_action_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    estimated_co2_reduction DECIMAL(10,2) NOT NULL,
    points INT NOT NULL,
    category_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_climate_action_category
        FOREIGN KEY (category_id) REFERENCES action_categories(category_id)
);

CREATE TABLE IF NOT EXISTS user_actions (
    user_action_id VARCHAR(20) PRIMARY KEY,
    quantity INT NOT NULL,
    total_reduction DECIMAL(10,2) NOT NULL,
    completed_date DATE NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    action_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_user_action_user
        FOREIGN KEY (user_id) REFERENCES app_users(user_id),
    CONSTRAINT fk_user_action_climate_action
        FOREIGN KEY (action_id) REFERENCES climate_actions(climate_action_id)
);

CREATE TABLE IF NOT EXISTS goals (
    goal_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    target_reduction DECIMAL(10,2) NOT NULL,
    target_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_goal_user
        FOREIGN KEY (user_id) REFERENCES app_users(user_id)
);

