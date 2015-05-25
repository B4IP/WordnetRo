DROP TABLE hiperonime;
DROP TABLE meronime;
DROP TABLE cuvinte_romana;
DROP TABLE cuvinte_engleza;
DROP TABLE utilizatori;


CREATE TABLE utilizatori (
userID NUMBER PRIMARY KEY,
userName VARCHAR2(200) NOT NULL UNIQUE,
userPassword VARCHAR2(200) NOT NULL
);

INSERT INTO utilizatori VALUES (1, 'root', 'oP@rolaGre@');

CREATE TABLE cuvinte_engleza (
ID_cuvant_eng NUMBER PRIMARY KEY,
cuvant_eng VARCHAR2(200) NOT NULL
);

--INSERT INTO cuvinte_engleza VALUES (1, 'word');

CREATE TABLE cuvinte_romana (
ID_cuvant_rom NUMBER PRIMARY KEY,
cuvant_rom VARCHAR2(2000) NOT NULL,
cuvant_eng VARCHAR2(2000) NOT NULL,    
glossa VARCHAR2(2000) NOT NULL,
ID_cuvant_eng_FK NUMBER NOT NULL,
modificat NUMBER DEFAULT 0,
glossa_tradusa VARCHAR2(2000) DEFAULT 'glossa_lipsa',
POS VARCHAR2(15) 
);

--INSERT INTO cuvinte_romana VALUES (1, 'cuvant', 'word', 'english gloss', 1, 11, 'glossa tradusa', 'noun');

ALTER TABLE cuvinte_romana 
ADD CONSTRAINT cuv_rom_ID_cuv_eng_FK FOREIGN KEY ( ID_cuvant_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

CREATE TABLE hiperonime (
ID_cuvant_eng_FK NUMBER NOT NULL,
ID_hiperonim_eng_FK NUMBER NOT NULL
);

ALTER TABLE hiperonime 
ADD CONSTRAINT ID_hiperonim FOREIGN KEY ( ID_hiperonim_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

CREATE TABLE meronime (
ID_cuvant_eng_FK NUMBER NOT NULL,
ID_meronim_eng_FK NUMBER NOT NULL
);

ALTER TABLE meronime 
ADD CONSTRAINT ID_meronim FOREIGN KEY ( ID_meronim_eng_FK ) 
REFERENCES cuvinte_engleza ( ID_cuvant_eng );

COMMIT;
/

CREATE OR REPLACE FUNCTION getContinueTranslatePosition 
RETURN NUMBER 
IS
CURSOR ultimul_tradus IS 
SELECT modificat, ID_cuvant_rom
FROM cuvinte_romana;
ID_romanian_contiune NUMBER :=1 ;
BEGIN 

    FOR linie IN ultimul_tradus LOOP
        IF linie.modificat = 1 THEN 
            ID_romanian_contiune := linie.ID_cuvant_rom;
        END IF;
    END LOOP;
    RETURN ID_romanian_contiune;
END;
/

CREATE OR REPLACE FUNCTION getStopPosition 
RETURN NUMBER 
IS
numberOfRows NUMBER :=1 ;
BEGIN 
    SELECT COUNT(ID_cuvant_rom) INTO numberOfRows
    FROM cuvinte_romana;
    RETURN numberOfRows;
END;
/

CREATE OR REPLACE FUNCTION getEnglishWord (p_idCuvantRomana NUMBER) 
RETURN VARCHAR2
IS
englishWord VARCHAR2(2000);
BEGIN
   SELECT cuvant_eng INTO englishWord 
   FROM cuvinte_romana 
   WHERE ID_cuvant_rom = p_idCuvantRomana;
   return englishWord;
END;
/


CREATE OR REPLACE FUNCTION getEnglishGloss (p_idCuvantRomana NUMBER) 
RETURN VARCHAR2
IS
englishGloss VARCHAR2(2000);
BEGIN
   SELECT glossa INTO englishGloss 
   FROM cuvinte_romana 
   WHERE ID_cuvant_rom = p_idCuvantRomana;
   return englishGloss;
END;
/

CREATE OR REPLACE FUNCTION getEnglishPOS (p_idCuvantRomana NUMBER)
RETURN VARCHAR2
IS
englishPOS VARCHAR2(2000);
BEGIN 
    SELECT POS INTO englishPOS 
    FROM cuvinte_romana 
    WHERE ID_cuvant_rom = p_idCuvantRomana;
    RETURN englishPOS;
END;
/


