package tests;

import org.junit.Test;
import translate.apis.Translation;
import translate.factory.API;
import translate.factory.TranslateAPIFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mihai on 5/14/2015.
 */
public class TranslateAPIFactoryTest {

    @Test
    public void atomicTranslateTest(){
        String str="chair";
        Translation result=TranslateAPIFactory.atomicTranslate(str, API.Google,"en","ro");
        String expResult="scaun";
        checkIfContainsTranslation(result, expResult);

        Translation result2=TranslateAPIFactory.atomicTranslate(str, API.Dictio,"en","ro");
        checkIfContainsTranslation(result2, expResult);

        Translation result1=TranslateAPIFactory.atomicTranslate(str, API.Gsp,"en","ro");
        checkIfContainsTranslation(result1, expResult);
        Translation result3=TranslateAPIFactory.atomicTranslate(str, API.Hallo,"en","ro");
        checkIfContainsTranslation(result3,expResult);

        Translation result4=TranslateAPIFactory.atomicTranslate(str, API.WordReference,"en","ro");
        checkIfContainsTranslation(result4, expResult);

    }

    private void checkIfContainsTranslation(Translation result, String expResult) {
        boolean found = false;
        for(String translation:result){
            if (translation.equals(expResult)){
                found = true;
                break;
        }
        }
        assertTrue(expResult,found);
    }

    private void checkIfNotContainsTranslation(Translation result, String expResult) {
        boolean found = false;
        for(String translation:result){
            if (translation.equals(expResult)){
                found = true;
                break;
            }
        }
        assertFalse(expResult, found);
    }

    @Test
    public void atomicTranslateTestExpr(){
        String str="I want a book";
        Translation result=TranslateAPIFactory.atomicTranslate(str, API.Google,"en","ro");
        String expResult="Vreau o carte";
        checkIfContainsTranslation(result, expResult);

        Translation result2=TranslateAPIFactory.atomicTranslate(str, API.Dictio,"en","ro");
        checkIfNotContainsTranslation(result2,expResult);

        Translation result1=TranslateAPIFactory.atomicTranslate(str, API.Gsp,"en","ro");
        checkIfNotContainsTranslation(result1, expResult);
        Translation result3=TranslateAPIFactory.atomicTranslate(str, API.Hallo,"en","ro");
        checkIfNotContainsTranslation(result3,expResult);

        Translation result4=TranslateAPIFactory.atomicTranslate(str, API.WordReference,"en","ro");
        checkIfNotContainsTranslation(result4, expResult);

    }
}
