package tests;

import org.junit.Test;
import translate.apis.Gsp;
import translate.apis.Translation;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by mihai on 5/16/2015.
 */
public class GspTest {

    @Test
    public void getCandidatesTest() {
        Gsp gsp = new Gsp("en", "ro");
        Set<String> translation = new HashSet<String>();
        translation.add("para");
        translation.add("par");


        Translation result = gsp.getCandidates("pear");

        for (String s : result) {
            assertTrue(translation.contains(s));
        }


    }
}
