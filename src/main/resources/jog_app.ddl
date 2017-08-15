
CREATE DATABASE IF NOT EXISTS jog_app
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
  
USE jog_app;
CREATE TABLE IF NOT EXISTS jog_app.users(
  id        INT(32) NOT NULL AUTO_INCREMENT,
  password    CHAR(128)        NOT NULL COMMENT 'Password hashed of the user',
  username    VARCHAR(50)    NOT NULL COMMENT 'Username prefered by the user',
  email       VARCHAR(256)     NOT NULL COMMENT 'Email of the user',
  first_name  VARCHAR(128)     NOT NULL COMMENT 'First name of the user',
  last_name   VARCHAR(128)     NOT NULL COMMENT 'Last name of the user',
  enabled   TINYINT(1)       NOT NULL DEFAULT 0      COMMENT 'Boolean to know if the user is enabled to make request',
  role        VARCHAR(10)      NOT NULL DEFAULT 'USER' COMMENT 'Role of the user',
  units       VARCHAR(10)      NOT NULL DEFAULT 'KM'   COMMENT 'Units to use in distance for the user',
  create_date TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  
  CONSTRAINT users_pk PRIMARY KEY (id),
  INDEX ix_username (username)
);

USE jog_app;
CREATE TABLE IF NOT EXISTS jog_app.jogs(
  id      INT(32)       NOT NULL AUTO_INCREMENT,
  date      DATE          NOT NULL COMMENT 'Date of the jog session',
  distance    INT(32)    NOT NULL COMMENT 'Distance jogged in meters',
  duration    INT(32)    NOT NULL COMMENT 'Duration of jog session in seconds',
  users_id    INT(32)       NOT NULL COMMENT 'The id of the user that made this entry',
  create_date   TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date   TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  
  CONSTRAINT jogs_pk   PRIMARY KEY (id),
  CONSTRAINT users_id_fk FOREIGN KEY (users_id)
    REFERENCES users(id),
  INDEX ix_date (date),
  INDEX ix_users_id (users_id)  
);

INSERT INTO jog_app.users(password, username, email, first_name, last_name, enabled, role)
VALUES (
  'C7AD44CBAD762A5DA0A452F9E854FDC1E0E7A52A38015F23F3EAB1D80B931DD472634DFAC71CD34EBC35D16AB7FB8A90C81F975113D6C7538DC69DD8DE9077EC',
    'admin',
    'admin@admin.com',
    'Juan',
    'Doe',
    1,
    'ADMIN'    
);
INSERT INTO jog_app.users(password, username, email, first_name, last_name, enabled, role)
VALUES (
  '9EC62C20118FF506DAC139EC30A521D12B9883E55DA92B7D9ADEEFE09ED4E0BD152E2A099339871424263784F8103391F83B781C432F45ECCB03E18E28060D2F',
    'user1',
    'user1@user1.com',
    'Joe',
    'Doe',
    1,
    'USER'    
);
INSERT INTO jog_app.users(password, username, email, first_name, last_name, enabled, role)
VALUES (
  '92A881051A0D26BA0FE4A65CB1039C10E18718C68591EFB6AFBF883B672A328BC8BA8C13FDAA90EEDC018C280782CBBD2A842ACBD9A5F3B8965012A1BA489234',
    'manager1',
    'manager1@manager1.com',
    'Jane',
    'Doe',
    1,
    'MANAGER'    
);

UPDATE jog_app.users
set 
  password = 'C7AD44CBAD762A5DA0A452F9E854FDC1E0E7A52A38015F23F3EAB1D80B931DD472634DFAC71CD34EBC35D16AB7FB8A90C81F975113D6C7538DC69DD8DE9077EC',
  username = 'admin',
    email = 'admin@admin.com',
    first_name = 'Juan',
    role = 'ADMIN'
where jog_app.users.id = 1;
commit;
