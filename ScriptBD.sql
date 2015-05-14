DROP TABLE cuvinte_romana;
DROP TABLE relatii;
DROP TABLE cuvinte_engleza;


CREATE TABLE cuvinte_engleza (
ID_cuvant_eng NUMBER PRIMARY KEY,
cuvant_eng VARCHAR2(200) NOT NULL
);

CREATE TABLE relatii (
ID_glossa NUMBER PRIMARY KEY,
glossa VARCHAR(1000) NOT NULL,
ID_cuvant_eng_FK NUMBER NOT NULL
);


CREATE TABLE cuvinte_romana (
ID_cuvant_rom NUMBER PRIMARY KEY,
cuvant_rom VARCHAR2(200) NOT NULL,
ID_cuvant_eng_FK NUMBER NOT NULL,
ID_glossa_FK NUMBER NOT NULL
);

ALTER TABLE relatii 
ADD CONSTRAINT rel_ID_cuv_eng_FK FOREIGN KEY ( ID_cuvant_eng_FK ) REFERENCES cuvinte_engleza ( ID_cuvant_eng );

ALTER TABLE cuvinte_romana 
ADD CONSTRAINT cuv_rom_ID_cuv_eng_FK FOREIGN KEY ( ID_cuvant_eng_FK ) REFERENCES cuvinte_engleza ( ID_cuvant_eng );

ALTER TABLE cuvinte_romana 
ADD CONSTRAINT cuv_rom_ID_glossa_FK FOREIGN KEY ( ID_glossa_FK ) REFERENCES relatii ( ID_glossa );

/*
fly – fly (a zbura – muscă)
Mary doesn’t want to fly there. She’ll drive.
That fly is driving me crazy!
*/
