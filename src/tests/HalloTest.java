package tests;
import org.junit.Test;
import translate.apis.Hallo;
import translate.apis.Translation;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by mihai on 5/16/2015.
 */
public class HalloTest {
    @Test
    public void getCandidatesTest(){
    Hallo hallo=new Hallo("en","ro");
    Translation translation=new Translation("pear");
    translation.add("păr (Pyrus communis)");
    translation.add("pară");
    translation.add("pară");
    translation.add("pară (la convertizor)");
    translation.add("scoruş de munte (Sorbus aucaparia)");
    translation.add("sorb (Sorbus aucaparia)");
    translation.add("lemn de păr");
    translation.add("para lui Poincare ");
    translation.add("întrerupător (în formă de) pară");
    translation.add("şină cu cap umflat");
    translation.add("şină cu profil bulb");
    translation.add("şină cu profil de pară");


    Translation result = hallo.getCandidates("pear");

    Iterator<String> resultIterator = result.iterator();
    Iterator<String> expectedIterator = translation.iterator();

    while(resultIterator.hasNext() && expectedIterator.hasNext()) {
        assertEquals(expectedIterator.next(), resultIterator.next());
    }

}

}
