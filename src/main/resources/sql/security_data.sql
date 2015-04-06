# USE db_ivis;
DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users
  (username, password, enabled)
VALUES
  ('admin', 'pass', 1),
  ('user1', '1111', 1),
  ('user2', '2222', 1),
  ('user3', '3333', 0);


INSERT INTO authorities (username, authority) VALUES
  ('admin', 'ROLE_ADMIN'),
  ('admin', 'ROLE_USER'),
  ('user1', 'ROLE_USER'),
  ('user2', 'ROLE_USER'),
  ('user3', 'ROLE_USER');
