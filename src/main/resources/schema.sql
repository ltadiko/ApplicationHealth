CREATE TABLE IF NOT EXISTS WEBSITES
(
    id serial PRIMARY KEY,
    url  uri NOT NULL,
    name varchar(60) NOT NULL
);

INSERT INTO
    WEBSITES (url, name)
VALUES
    ('https://www.google.com','Google'),
    ('https://www.yahoo.com','Yahoo'),
    ('https://www.bing.com','Bing');

CREATE TABLE WEBSITE_HEALTH_LOGS (
	id serial PRIMARY KEY,
	website_id int NOT NULL,
	healthStatus VARCHAR ( 10 ) NOT NULL,
	created_on TIMESTAMP NOT NULL,
    FOREIGN KEY (website_id)  REFERENCES WEBSITES (id)
);

