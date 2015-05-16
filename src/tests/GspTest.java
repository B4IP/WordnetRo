package tests;

import org.junit.Test;
import translate.apis.Gsp;
import translate.apis.Translation;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by mihai on 5/16/2015.
 */
public class GspTest {

    @Test
    public void getCandidatesTest(){
        Gsp gsp=new Gsp("en","ro");
        Translation translation=new Translation("pear");
        translation.add("pară");
        translation.add("păr");
        translation.add("compot de pere");
        translation.add("Pearce");
        translation.add("lui Pearce");
        translation.add("nestemată");
        translation.add("perlă");
        translation.add("sidef");
        translation.add("Pearl");
        translation.add("pescuitor de perle");
        translation.add("pescuitori de piele");
        translation.add("spălător de vase");
        translation.add("veselar");
        translation.add("loc unde se pescuiesc perle");
        translation.add("lui Pearl");
        translation.add("Pearla");
        translation.add("lui Pearla");
        translation.add("Pearle");
        translation.add("lui Pearle");
        translation.add("Pearline");



        Translation result = gsp.getCandidates("pear");

        Iterator<String> resultIterator = result.iterator();
        Iterator<String> expectedIterator = translation.iterator();

        while(resultIterator.hasNext() && expectedIterator.hasNext()) {
            assertEquals(expectedIterator.next(), resultIterator.next());
        }

    }
}
