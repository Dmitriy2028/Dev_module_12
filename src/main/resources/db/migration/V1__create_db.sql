CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    name VARCHAR CHECK (length(name) >= 3 AND length(name) <= 200) NOT NULL
);

ALTER SEQUENCE client_id_seq RESTART WITH 1;

CREATE TABLE planet (
    id VARCHAR primary key  CHECK (id ~ '^[A-Z0-9]+$'),
    name VARCHAR CHECK (length(name) >= 1 and length(name) <= 500) NOT NULL
);

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    client_id INT,
    foreign key (client_id) references client(id) ON DELETE CASCADE,
    from_planet_id VARCHAR,
    to_planet_id VARCHAR,
    foreign key (from_planet_id) references planet(id) ON DELETE CASCADE,
    foreign key (to_planet_id) references planet(id) ON DELETE CASCADE
);