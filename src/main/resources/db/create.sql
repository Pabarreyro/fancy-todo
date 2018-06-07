SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS categories (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    taskCount int
);

CREATE TABLE IF NOT EXISTS tasks (
    id int PRIMARY KEY auto_increment,
    description VARCHAR,
    completed BOOLEAN,
    categoryId INTEGER
);

