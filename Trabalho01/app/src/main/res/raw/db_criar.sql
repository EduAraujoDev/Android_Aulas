CREATE TABLE android (
    id integer NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    url_imagem varchar(255) NOT NULL
);

CREATE TABLE celular (
    id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
    marca varchar(255) NOT NULL,
    modelo varchar(255) NOT NULL,
    android_id varchar(255) NOT NULL,
    FOREIGN KEY (android_id) REFERENCES android (id)
);