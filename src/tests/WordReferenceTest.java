package tests;

import org.junit.Test;
import translate.apis.Translation;
import translate.apis.WordReference;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by mihai on 5/15/2015.
 */
public class WordReferenceTest {
    @Test
    public void getCandidatesTest(){
        WordReference wordReference=new WordReference("en","ro");
        Translation translation=new Translation("pear");
        translation.add("pară");
        translation.add("păr");
        translation.add("țuică de pere");
        translation.add("fruct de cactus comestibil");

        Translation result = wordReference.getCandidates("pear");

        Iterator<String> resultIterator = result.iterator();
        Iterator<String> expectedIterator = translation.iterator();

        while(resultIterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), resultIterator.next());
        }

    }
}
