DROP TABLE hiperonime;
DROP TABLE meronime;
DROP TABLE cuvinte_romana;
DROP TABLE cuvinte_engleza;

CREATE TABLE cuvinte_engleza (
ID_cuvant_eng NUMBER PRIMARY KEY,
cuvant_eng VARCHAR2(200) NOT NULL
);

CREATE TABLE cuvinte_romana (
ID_cuvant_rom NUMBER PRIMARY KEY,
cuvant_rom VARCHAR2(200) NOT NULL,
cuvant_eng VARCHAR2(200) NOT NULL,    -- posibil sa fie scos
glossa VARCHAR2(2000) NOT NULL,
ID_cuvant_eng_FK NUMBER NOT NULL
);

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

