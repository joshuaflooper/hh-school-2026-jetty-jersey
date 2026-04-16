CREATE TABLE messages (
    id serial PRIMARY KEY,
    sender_ip text NOT NULL,
    content text NOT NULL,
    send_on timestamp NOT NULL
);