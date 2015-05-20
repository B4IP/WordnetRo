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
        translation.add("păr");
        translation.add("pară");
        translation.add("pară");
        translation.add("pară");
        translation.add("scoruş de munte");
        translation.add("sorb");
        translation.add("lemn de păr");
        translation.add("para lui Poincare");
        translation.add("întrerupător pară");
        translation.add("şină cu cap umflat");
        translation.add("şină cu profil bulb");
        translation.add("şină cu profil de pară");


        Translation result = hallo.getCandidates("pear");

        for(String s:result){
            assertTrue(translation.contains(s));
        }
}

}
