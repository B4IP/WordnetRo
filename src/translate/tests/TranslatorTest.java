/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate.tests;

import translate.translator.Translator;
import net.sf.extjwnl.data.POS;

/**
 *
 * @author Bety
 */
public class TranslatorTest 
{
    public static void main(String args[])
    {
	System.out.println("--- translate word from definition ---");
        
        String returnedWordFromDefinition ;
	returnedWordFromDefinition = Translator.translateFromDefinition("apple",POS.NOUN,"fruit");
        
	System.out.println(returnedWordFromDefinition);
        
	returnedWordFromDefinition = Translator.translateFromDefinition("beautiful",POS.ADJECTIVE,"adjective");
        
	System.out.println(returnedWordFromDefinition);
        
        
	System.out.println("--- translate word from sentence ---");
        
        String returnedWordFromSentence ;
	returnedWordFromSentence = Translator.translateFromSentence("raw","The meat is raw");
        
	System.out.println(returnedWordFromSentence);
        
        
	returnedWordFromSentence = Translator.translateFromSentence("shadow","I see this trees' shadow");
        
	System.out.println(returnedWordFromSentence);
        
        
	returnedWordFromSentence = Translator.translateFromSentence("shadow","I see my shadow at dawn");
        
	System.out.println(returnedWordFromSentence);
    }
}
