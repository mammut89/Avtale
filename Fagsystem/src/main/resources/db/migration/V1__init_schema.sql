CREATE TABLE kunde (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        fnr VARCHAR(50) NOT NULL
);

CREATE TABLE avtale (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        kunde_id BIGINT NOT NULL,
                        avtale_status VARCHAR NOT NULL,
                        FOREIGN KEY (kunde_id) REFERENCES kunde(id)
);