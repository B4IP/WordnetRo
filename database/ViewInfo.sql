SET SERVEROUTPUT ON;

DECLARE 
    numar_cuvinte_engleza INTEGER;
    numar_cuvinte_romana INTEGER;
    numar_hiperonime INTEGER;
    numar_meronime INTEGER;
    numar_utilizatori INTEGER;
BEGIN 
    SELECT COUNT(*) INTO numar_cuvinte_engleza FROM cuvinte_engleza; 
    SELECT COUNT(*) INTO numar_cuvinte_romana FROM cuvinte_romana; 
    SELECT COUNT(*) INTO numar_hiperonime FROM hiperonime; 
    SELECT COUNT(*) INTO numar_meronime FROM meronime; 
    SELECT COUNT(*) INTO numar_utilizatori FROM utilizatori;
    DBMS_OUTPUT.PUT_LINE('cuvinte_engleza: ' || numar_cuvinte_engleza);
    DBMS_OUTPUT.PUT_LINE('cuvinte_romana: ' || numar_cuvinte_romana);
    DBMS_OUTPUT.PUT_LINE('hiperonime: ' || numar_hiperonime);
    DBMS_OUTPUT.PUT_LINE('meronime: ' || numar_meronime);
     DBMS_OUTPUT.PUT_LINE('utilizatori: ' || numar_utilizatori);
END;
/

BEGIN 
    DBMS_OUTPUT.PUT_LINE ( getContinueTranslatePosition() );
    DBMS_OUTPUT.PUT_LINE ( getEnglishWord(30) );
    DBMS_OUTPUT.PUT_LINE ( getEnglishGloss(30) );
    DBMS_OUTPUT.PUT_LINE ( getEnglishPOS(30) );
END;
/



