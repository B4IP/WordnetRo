package tests;

import org.junit.Test;
import translate.apis.Translation;
import translate.apis.WordReference;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by mihai on 5/15/2015.
 */
public class WordReferenceTest {
    @Test
    public void getCandidatesTest(){
        WordReference wordReference=new WordReference("en","ro");
        Set<String> translation=new HashSet<String>();
        translation.add("para");
        translation.add("par");

        Translation result = wordReference.getCandidates("pear");

        for(String s:result){
            assertTrue(translation.contains(s));
        }

    }

    @Test
    public void getOtherCanidatesTest(){
        WordReference wordReference=new WordReference("en","ro");
        Set<String> translation=new HashSet<String>();
        translation.add("portocaliu");
        translation.add("portocala");
        translation.add("de portocala");
        translation.add("de portocala");
        translation.add("portocaliu");
        translation.add("portocal");


        Translation result = wordReference.getCandidates("orange");

        for(String s:result){
            assertTrue(s, translation.contains(s));
        }


    }
}
