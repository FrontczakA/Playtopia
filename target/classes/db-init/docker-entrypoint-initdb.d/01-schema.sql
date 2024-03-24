CREATE TABLE IF NOT EXISTS app_user (
                                        id SERIAL PRIMARY KEY,
                                        username VARCHAR(255) NOT NULL,
                                        password VARCHAR(255) NOT NULL,
                                        email VARCHAR(255) NOT NULL,
                                        name VARCHAR(255) NOT NULL,
                                        surname VARCHAR(255) NOT NULL,
                                        street VARCHAR(255),
                                        city VARCHAR(255),
                                        postal_code VARCHAR(255),
                                        country VARCHAR(255),
                                        role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS game (
                                    id SERIAL PRIMARY KEY,
                                    price DOUBLE PRECISION NOT NULL,
                                    title VARCHAR(255) NOT NULL,
                                    description TEXT NOT NULL,
                                    genre VARCHAR(255) NOT NULL,
                                    platform VARCHAR(255) NOT NULL,
                                    photo_url VARCHAR(255) NOT NULL,
                                    in_stock BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_order (
                                          id SERIAL PRIMARY KEY,
                                          price DOUBLE PRECISION,
                                          order_owner_id BIGINT,
                                          FOREIGN KEY (order_owner_id) REFERENCES app_user(id)
);

CREATE TABLE IF NOT EXISTS order_game (
                                          order_id BIGINT,
                                          game_id BIGINT,
                                          FOREIGN KEY (order_id) REFERENCES user_order(id),
                                          FOREIGN KEY (game_id) REFERENCES game(id)
);
