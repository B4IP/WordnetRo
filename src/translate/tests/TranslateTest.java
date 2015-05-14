package translate.tests;

import translate.apis.TranslateAPI;
import translate.apis.GoogleTranslate;
import translate.apis.GoogleTranslate2;
import translate.apis.Translation;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new GoogleTranslate("en", "ro");
		Translation c = tr.getCandidates("raw");
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
