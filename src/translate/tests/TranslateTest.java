package translate.tests;

import translate.apis.TranslateAPI;
import translate.apis.Translation;
import translate.factory.API;
import translate.factory.TranslateAPIFactory;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = TranslateAPIFactory.getAPIInstance(API.GoogleTranslate, "en", "ro");
		Translation candidates = tr.getCandidates("raw");
		for (String candidate : candidates){
			System.out.println(candidate);
		}
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
