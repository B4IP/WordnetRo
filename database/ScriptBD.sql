SET SERVEROUTPUT ON;

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
cuvant_rom VARCHAR2(200) NOT NULL,
cuvant_eng VARCHAR2(200) NOT NULL,    
glossa VARCHAR2(2000) NOT NULL,
ID_cuvant_eng_FK NUMBER NOT NULL
);

--INSERT INTO cuvinte_romana VALUES (1, 'cuvant', 'word', 'sensul/glosa', 1);

ALTER TABLE cuvinte_romana
ADD modificat NUMBER DEFAULT 0;

ALTER TABLE cuvinte_romana
ADD glossa_tradusa VARCHAR2(2000) DEFAULT 'glossa_lipsa';



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

DECLARE 
    numar_cuvinte_engleza INTEGER;
    numar_cuvinte_romana INTEGER;
    numar_hiperonime INTEGER;
    numar_meronime INTEGER;
BEGIN 
    SELECT COUNT(*) INTO numar_cuvinte_engleza FROM cuvinte_engleza; 
    SELECT COUNT(*) INTO numar_cuvinte_romana FROM cuvinte_romana; 
    SELECT COUNT(*) INTO numar_hiperonime FROM hiperonime; 
    SELECT COUNT(*) INTO numar_meronime FROM meronime; 
    DBMS_OUTPUT.PUT_LINE('cuvinte_engleza: ' || numar_cuvinte_engleza);
    DBMS_OUTPUT.PUT_LINE('cuvinte_romana: ' || numar_cuvinte_romana);
    DBMS_OUTPUT.PUT_LINE('hiperonime: ' || numar_hiperonime);
    DBMS_OUTPUT.PUT_LINE('meronime: ' || numar_meronime);
END;

