DROP TABLE IF EXISTS hiperonime;
DROP TABLE IF EXISTS meronime;
DROP TABLE IF EXISTS cuvinte_romana;
DROP TABLE IF EXISTS cuvinte_engleza;
DROP TABLE IF EXISTS utilizatori;

CREATE TABLE utilizatori (
userID DOUBLE PRIMARY KEY,
userName VARCHAR(200) NOT NULL UNIQUE,
userPassword VARCHAR(200) NOT NULL
);
INSERT INTO utilizatori VALUES (1, 'root', 'oP@rolaGre@');

CREATE TABLE cuvinte_engleza (
ID_cuvant_eng DOUBLE PRIMARY KEY,
cuvant_eng VARCHAR(200) NOT NULL
);

CREATE TABLE cuvinte_romana (
ID_cuvant_rom DOUBLE PRIMARY KEY,
cuvant_rom VARCHAR(2000) NOT NULL,
cuvant_eng VARCHAR(2000) NOT NULL,    
glossa VARCHAR(2000) NOT NULL,
ID_cuvant_eng_FK DOUBLE NOT NULL,
modificat DOUBLE DEFAULT 0,
glossa_tradusa VARCHAR(2000) DEFAULT 'glossa_lipsa',
POS VARCHAR(15) 
);

ALTER TABLE cuvinte_romana 
ADD CONSTRAINT cuv_rom_ID_cuv_eng_FK FOREIGN KEY ( ID_cuvant_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

CREATE TABLE hiperonime (
ID_cuvant_eng_FK DOUBLE NOT NULL,
ID_hiperonim_eng_FK DOUBLE NOT NULL
);

ALTER TABLE hiperonime 
ADD CONSTRAINT ID_hiperonim FOREIGN KEY ( ID_hiperonim_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

CREATE TABLE meronime (
ID_cuvant_eng_FK DOUBLE NOT NULL,
ID_meronim_eng_FK DOUBLE NOT NULL
);

ALTER TABLE meronime 
ADD CONSTRAINT ID_meronim FOREIGN KEY ( ID_meronim_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

COMMIT;
