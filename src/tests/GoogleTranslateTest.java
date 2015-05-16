package tests;

import org.junit.Test;
import translate.apis.GoogleTranslate;
import translate.apis.Translation;

import static org.junit.Assert.assertTrue;

/**
 * Created by mihai on 5/14/2015.
 */

public class GoogleTranslateTest {

        @Test
        public void testTranslate() {

            String str = "forehead";
            GoogleTranslate instance = new GoogleTranslate("en", "ro");
            String expResult = "frunte";
            Translation result = instance.getCandidates(str);
            checkIfContainsTranslation(result,expResult);

            String expr = "I like you";
            GoogleTranslate expr_instance = new GoogleTranslate("en", "ro");
            String Result1 = "Imi placi";
            Translation result_expr = instance.getCandidates(expr);
            checkIfContainsTranslation(result_expr,Result1);
        }

    private void checkIfContainsTranslation(Translation result, String expResult) {
        boolean found = false;
        for(String translation:result){
            if (translation.equals(expResult)){
                found = true;
                break;
            }
        }
        assertTrue(found);
    }



        }


