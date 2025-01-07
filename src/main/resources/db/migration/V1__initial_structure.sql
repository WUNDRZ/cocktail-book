CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE ingredient_categories (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE ingredients (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(2048),
    photo VARCHAR(2048),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES ingredient_categories(id)
);

CREATE TABLE cocktails (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    recipe VARCHAR(2000) NOT NULL,
    calculated_rating DECIMAL(4,2) DEFAULT 0.00,
    view_count INT DEFAULT 0,
    owner_id BIGINT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE rating (
    user_id BIGINT NOT NULL,
    cocktail_id BIGINT NOT NULL,
    rating INT NOT NULL,
    PRIMARY KEY (user_id, cocktail_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (cocktail_id) REFERENCES cocktails(id)
);

CREATE TABLE tags (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE cocktails_tags (
    cocktail_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (cocktail_id, tag_id),
    FOREIGN KEY (cocktail_id) REFERENCES cocktails(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);
