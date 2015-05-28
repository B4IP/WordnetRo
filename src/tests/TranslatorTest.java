/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import translate.translator.Translator;
import net.sf.extjwnl.data.POS;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Bety
 */
public class TranslatorTest 
{
    public static void main(String args[])
    {
        TranslatorTest test = new TranslatorTest();
        
	test.TestTranslatorFromDefinition();
        
        test.TestTranslatorFromSentence();
    }
    
    /**
     * testing translate from definition
     */
    @Test
    public void TestTranslatorFromDefinition()
    {
        
        System.out.println("--- translate word from definition ---");
        
        String returnedWordFromDefinition ;
        
        Translator translator = new Translator();
        /*
	returnedWordFromDefinition = translator.translateFromDefinition("apple",POS.NOUN,"round red fruit");
        
	System.out.println(returnedWordFromDefinition);
        
        //checkIfContainsTranslation("măr",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("lamp",POS.NOUN,"a device for giving light");
        
	System.out.println(returnedWordFromDefinition);
        
        //checkIfContainsTranslation("lampă",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("beautiful",POS.ADJECTIVE,"pleasing the senses or mind aesthetically");
        
	System.out.println(returnedWordFromDefinition);
        
        //checkIfContainsTranslation("frumos",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("sun",POS.NOUN,"star");
        
	System.out.println(returnedWordFromDefinition);
        
        //checkIfContainsTranslation("soare",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("Sun",POS.NOUN,"star");
        
	System.out.println(returnedWordFromDefinition);
        
        //checkIfContainsTranslation("soare",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("illuminate",POS.VERB,"to light up");
        
	System.out.println(returnedWordFromDefinition);
        */
        //checkIfContainsTranslation("a lumina",returnedWordFromDefinition);
        
	returnedWordFromDefinition = translator.translateFromDefinition("SUN",POS.NOUN,"STAR");
        
	System.out.println(returnedWordFromDefinition);
        
        checkIfContainsTranslation("soare",returnedWordFromDefinition);
    }
    
    /**
     * testing translate from sentence
     */
    @Test
    public void TestTranslatorFromSentence()
    {
        System.out.println("--- translate word from sentence ---");
        
        Translator translator = new Translator();
        
        String returnedWordFromSentence ;
	returnedWordFromSentence = translator.translateFromSentence("raw", POS.ADJECTIVE ,"The meat is raw");
        
	System.out.println(returnedWordFromSentence);
        
        checkIfContainsTranslation("crud",returnedWordFromSentence);
        /*
	returnedWordFromSentence = translator.translateFromSentence("shadow",POS.NOUN,"I see this trees' shadow");
        
	System.out.println(returnedWordFromSentence);
        
        //checkIfContainsTranslation("umbră",returnedWordFromSentence);
        
	returnedWordFromSentence = translator.translateFromSentence("walk",POS.VERB,"I walk on my way home");
        
	System.out.println(returnedWordFromSentence);
        
        //checkIfContainsTranslation("a merge pe jos",returnedWordFromSentence);
        
	returnedWordFromSentence = translator.translateFromSentence("eat",POS.VERB,"I eat an apple");
        
	System.out.println(returnedWordFromSentence);
        
        //checkIfContainsTranslation("a mânca",returnedWordFromSentence);
        
	returnedWordFromSentence = translator.translateFromSentence("Shadow",POS.NOUN,"I see my Shadow at dawn");
        
	System.out.println(returnedWordFromSentence);
        */
        //checkIfContainsTranslation("umbră",returnedWordFromSentence);
    }
    
    
    private void checkIfContainsTranslation(String result, String expResult) {
        boolean found = false;
        
        if (result.equals(expResult))
        {
            found = true;
        }
        
        assertTrue(found);
    }
    
}
