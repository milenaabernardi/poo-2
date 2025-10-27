DROP TABLE IF EXISTS jogo;
DROP TABLE IF EXISTS estudio;


CREATE TABLE estudio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    pais_origem VARCHAR(50)
);

CREATE TABLE jogo (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    genero VARCHAR(50),
    ano_lancamento INT,
    estudio_id INT,
    
    CONSTRAINT fk_estudio_jogo 
        FOREIGN KEY(estudio_id) 
        REFERENCES estudio(id)
        ON DELETE SET NULL,
        
    CONSTRAINT uq_jogo_titulo_ano UNIQUE (titulo, ano_lancamento)
);


INSERT INTO estudio (nome, pais_origem) VALUES 
('CD Projekt Red', 'Polônia'),
('Valve', 'EUA'),
('FromSoftware', 'Japão'),
('Sony', 'Japão'),
('Ubisoft', 'França'),
('Electronic Arts', 'EUA'),
('Square Enix', 'Japão'),
('Bethesda', 'EUA'),
('Nintendo', 'Japão'),
('Rockstar Games', 'EUA')
ON CONFLICT (nome) DO NOTHING;


INSERT INTO jogo (titulo, genero, ano_lancamento, estudio_id) VALUES 
('Cyberpunk 2077', 'RPG', 2020, (SELECT id FROM estudio WHERE nome = 'CD Projekt Red')),
('The Witcher 3', 'RPG', 2015, (SELECT id FROM estudio WHERE nome = 'CD Projekt Red')),
('Half-Life: Alyx', 'VR', 2020, (SELECT id FROM estudio WHERE nome = 'Valve')),
('Elden Ring', 'Action RPG', 2022, (SELECT id FROM estudio WHERE nome = 'FromSoftware'))
ON CONFLICT (titulo, ano_lancamento) DO NOTHING;

SELECT 'Script executado com sucesso! Tabelas recriadas e dados inseridos.' AS status;