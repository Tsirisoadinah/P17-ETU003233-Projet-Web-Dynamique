CREATE DATABASE ExamenWeb;
USE ExamenWeb;

CREATE TABLE web_utilisateur(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    motdepasse VARCHAR(255) NOT NULL
);

INSERT INTO web_utilisateur (nom, motdepasse) 
VALUES 
    ('Eleve1', 'mdp123');

CREATE TABLE web_prevision (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    montant INT NOT NULL
);

CREATE TABLE web_depense(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idprevision INT,
    montant INT NOT NULL,
    FOREIGN KEY (idprevision) REFERENCES web_prevision(id)
);

-- Ajout de données dans la table web_prevision
INSERT INTO web_prevision (libelle, montant) 
VALUES 
    ('Loyer', 500000),
    ('Electricite', 150000),
    ('Internet', 100000),
    ('Courses', 300000);

-- Ajout de données dans la table web_depense
INSERT INTO web_depense (idprevision, montant) 
VALUES 
    (1, 500000), 
    (2, 120000),  
    (3, 90000),  
    (4, 250000);  