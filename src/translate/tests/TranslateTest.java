package translate.tests;

import translate.apis.IWordTranslator;
import translate.apis.Translation;
import translate.factory.API;
import translate.factory.TranslateAPIFactory;

public class TranslateTest{
	public static void main(String args[]){
		IWordTranslator tr = null;
		
		tr = TranslateAPIFactory.getAPIInstance(API.Hallo, "en", "ro");
		System.out.println(tr.getCandidates("raw"));
		System.out.println(tr.getCandidates("pear"));
		System.out.println(tr.getCandidates("game"));
		System.out.println(tr.getCandidates("tie"));
		System.out.println(tr.getCandidates("fox"));
		/*
		System.out.printf("%s\n", tr.translate("Santa and tooth fairy aren't real"));
                
        if (tr instanceof GoogleTranslate2){
           List<Translation> partialTranslations =((GoogleTranslate2) tr)
                   .getPartialTranslations("Santa and tooth fairy aren't real"); 
           for (Translation t: partialTranslations) {
               System.out.println(t);
           }
        }
        */
    }
}
