package tests;

import org.junit.Test;
import translate.apis.Hallo;
import translate.apis.Translation;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class HalloTest {
    @Test
    public void getCandidatesTest(){
        Hallo hallo=new Hallo("en","ro");
        Set<String> translation=new HashSet<String>();
        translation.add("par");
        translation.add("para");
        translation.add("para");
        translation.add("para");


        Translation result = hallo.getCandidates("pear");

        for(String s:result){
            assertTrue(translation.contains(s));
        }
}

}
